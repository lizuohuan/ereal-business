package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.enums.RoleEnum;
import com.magic.ereal.business.enums.SystemInfoEnum;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.*;
import com.magic.ereal.business.push.PushMessageUtil;
import com.magic.ereal.business.util.*;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSInput;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 传递卡/工作日志  业务层
 * Created by Eric Xie on 2017/4/21 0021.
 */
@Service
public class WorkDiaryService {

    @Resource
    private IWorkDiaryMapper workDiaryMapper;
    @Resource
    private IWorkDiaryStatusDetailMapper workDiaryStatusDetailMapper;
    @Resource
    private IWorkDiarySubMapper workDiarySubMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private WorkDiaryCcService workDiaryCcService;
    @Resource
    private ISystemInfoMapper systemInfoMapper;
    @Resource
    private ITransactionTypeMapper transactionTypeMapper;
    @Resource
    private IDepartmentMapper departmentMapper;


    /**
     * 后台统计
     * @param companyId
     * @param departmentId
     * @param time
     * @return
     * @throws Exception
     */
    public List<WorkDiary> queryWorkDiaryByTime(Integer companyId,Integer departmentId,Date time) throws Exception{
        time = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(time,"yyyy-MM-dd")),"yyyy-MM-dd");
        List<WorkDiary> workDiaries = workDiaryMapper.queryWorkDiaryByTime(departmentId, companyId, time, null, null);

        // 计算 个人工作学时间 以及 运动时间
        for (WorkDiary workDiary : workDiaries) {
            // 工作学习时间 (小时)
            double studyTime = 0.0;
            // 运动时间  (小时)
            double sportTime = 0.0;


            if(null != workDiary.getWorkDiarySubs() && workDiary.getWorkDiarySubs().size() > 0){
                for (WorkDiarySub workDiarySub : workDiary.getWorkDiarySubs()) {
                    if(!CommonUtil.isEmpty(workDiarySub.getJobContent())){
                        workDiarySub.setJobContent(workDiarySub.getJobContent().trim());
                    }
                    workDiarySub.setStartTimeStr(DateTimeHelper.formatDateTimetoString(workDiarySub.getStartTime(),"HH:mm"));
                    workDiarySub.setEndTimeStr(DateTimeHelper.formatDateTimetoString(workDiarySub.getEndTime(),"HH:mm"));

                    if(workDiarySub.getTransactionSubId() == 3 || workDiarySub.getTransactionSubId() == 4
                            || workDiarySub.getTransactionTypeId() == 1 || workDiarySub.getTransactionTypeId() == 2){

                        studyTime +=
                                ((double) ((workDiarySub.getEndTime().getTime() - workDiarySub.getStartTime().getTime()) / 1000))
                                / 3600;
                        continue;
                    }
                    if(workDiarySub.getTransactionTypeId() == 3){
                        sportTime +=
                                ((double) ((workDiarySub.getEndTime().getTime() - workDiarySub.getStartTime().getTime()) / 1000))
                                        / 3600;
                    }
                }
            }
            workDiary.setStudyTime(studyTime);
            workDiary.setSportTime(sportTime);
        }
        return workDiaries;
    }

    /**
     * 查询 待审核 的日志列表
     *
     * @param user
     * @return
     */
    public List<WorkDiary> queryCheckpending(User user) {
        Integer isManager = null;
        if (RoleEnum.COMPREHENSIVE_MANAGER.ordinal() == user.getRoleId()) {
            // 如果是综合部经理
            isManager = 2;
        } else {
            isManager = user.getIsManager();
        }
        return workDiaryMapper.queryCheckpending(user.getId(), isManager, user.getDepartmentId());
    }


    public WorkDiary queryWorkDiaryByDate(Date date, Integer userId) {
        return workDiaryMapper.queryWorkDiaryByDate(date, userId);
    }

    /**
     * 获取用户最近 最新的一条 日志记录
     * 如没有 返回null
     *
     * @param userId 用户ID
     * @return
     */
    public WorkDiary queryNearWorkDiaryByUser(Integer userId) {
        return workDiaryMapper.queryNearWorkDiaryByUser(userId);
    }

    /**
     * 更新传递卡 状态 更新第一次  如果更新前的状态为 拒绝后 再次更新的， 则不用此业务方法
     *
     * @param workDiary 对象中 需包含 审核人的ID 和 用户ID 用于创建订单进度
     *                  更新时间字段
     * @param user      创建者 用户 当状态为 待审核时，必传
     * @param notes     备注字段 可为空
     * @param synthesizeManagerId   综合部经理id
     */
    @Transactional
    public void updateWorkDiaryStatus(WorkDiary workDiary, String notes, User user, String userIds,Integer synthesizeManagerId) throws Exception {
        Map<String, String> extend = new HashMap<>();
        extend.put("infoType", SystemInfoEnum.WORK_DIARY_INFO.ordinal() + "");
        List<WorkDiaryStatusDetail> details = new ArrayList<>();
        String title = "";
        String content = "";
        User waitPush = null;
        if (StatusConstant.WORKDIARY_STATUS_REJECT.equals(workDiary.getStatus())) {
            // 如果状态是 经理拒绝
            // 直接打给 创建者 并推送给创建者
            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_REJECT,
                    workDiary.getUpdateTime(), notes, user.getId()));
            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SECOND_SUBMIT,
                    workDiary.getUpdateTime(), notes, workDiary.getUserId()));
            title = TextMessage.WORKDIARY_NON_APPROVED_TITLE;
            content = TextMessage.WORKDIARY_NON_APPROVED_TITLE;
            waitPush = userMapper.queryBaseInfo(workDiary.getUserId());
        } else if (StatusConstant.WORKDIARY_SYNTHESIZE_TATUS_REJECT.equals(workDiary.getStatus())) {
            List<SystemInfo> infos = new ArrayList<>();
            List<User> users = new ArrayList<>();
            // 创建者
            User user1 = userMapper.queryBaseInfo(workDiary.getUserId());
            // 创建者部门经理
            User temp = userMapper.queryManagerByDepartment(user1.getDepartmentId());

            // 如果是 综合部经理 拒绝通过
            // 打回给 创建者  和  创建者的 经理
            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SYNTHESIZE_REJECT,
                    workDiary.getUpdateTime(), notes, user.getId()));
            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SECOND_SUBMIT,
                    workDiary.getUpdateTime(), notes, user1.getId()));

            if (null != userIds && !userIds.isEmpty()) {
                //添加抄送人
                workDiaryCcService.save(userIds, workDiary.getId());
            }
            // 推送给 创建者 和 创建者经理
            // ...
            //创建者是经理级别
            if (temp.getId().equals(user1.getId())) {
                workDiary.setVerifier(synthesizeManagerId);
            }
            users.add(temp);
            users.add(user1);
            infos.add(createSystemInfo(workDiary.getUserId(), TextMessage.WORKDIARY_NON_APPROVED_TITLE, TextMessage.WORKDIARY_NON_APPROVED_CONTENT,
                    SystemInfoEnum.WORK_DIARY_INFO.ordinal()));
            infos.add(createSystemInfo(temp.getId(), TextMessage.WORKDIARY_NON_APPROVED_TITLE, TextMessage.WORKDIARY_NON_APPROVED_CONTENT,
                    SystemInfoEnum.WORK_DIARY_INFO.ordinal()));
            if (infos.size() > 0) {
                systemInfoMapper.batchAddSystemInfo(infos);
            }
            if (users.size() > 0) {
                for (User temp1 : users) {
                    PushMessageUtil.pushMessages(temp1, TextMessage.WORKDIARY_NON_APPROVED_TITLE, extend);
                }
            }
        } else if (StatusConstant.WORKDIARY_STATUS_APPROVED.equals(workDiary.getStatus())) {
            // 经理审核通过
            // 需要 推送给综合部经理 来审核
            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_APPROVED,
                    workDiary.getUpdateTime(), notes, user.getId()));

            // 推送给综合部经理 审核
            User user1 = userMapper.queryComprehensiveManager(user.getDepartmentId());
            if (null == user1) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"没有综合部经理");
            }
            title = TextMessage.WORKDIARY_NEW_TITLE;
            content = TextMessage.WORKDIARY_NEW_CONTENT;
            waitPush = user1;
        } else if (StatusConstant.WORKDIARY_SYNTHESIZE_STATUS_APPROVED.equals(workDiary.getStatus())) {

            // 创建者
            User user1 = userMapper.queryBaseInfo(workDiary.getUserId());
            // 创建者部门经理
            User temp = userMapper.queryManagerByDepartment(user1.getDepartmentId());

            //创建者是经理级别
            if (temp.getId().equals(user1.getId())) {
                workDiary.setVerifier(synthesizeManagerId);
            }
            // 如果综合部经理审核通过 直接修改状态
            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SYNTHESIZE_APPROVED,
                    workDiary.getUpdateTime(), notes, user.getId()));
            // 无推送
            if (null != userIds && !userIds.isEmpty()) {
                //添加抄送人
                workDiaryCcService.save(userIds, workDiary.getId());
            }
        } else if (StatusConstant.WORKDIARY_STATUS_PENDING.equals(workDiary.getStatus())) {
            workDiary.setUpdateTime(new Date());


            // 分配 审核人
            // ...
            WorkDiary diary = workDiaryMapper.queryWorkDiaryById(workDiary.getId());
            if (null == diary) {
                 throw new InterfaceCommonException(StatusConstant.Fail_CODE,"日志不存在");
            }
            if (user.getRoleId() != RoleEnum.COMPREHENSIVE_MANAGER.ordinal()) {


                details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SUBMIT,
                        new Date(), notes, user.getId()));

                // 如果创建人不是 综合部经理
                if (diary.getUser().getRoleId() != RoleEnum.COMPREHENSIVE_MANAGER.ordinal()) {

                    if (StatusConstant.WORKDIARY_SYNTHESIZE_TATUS_REJECT.equals(diary.getStatus())) {
                        // 如果是普通员工 并且 上次的状态是 综合部经理 拒绝审核
                        // 推送 给 综合部经理
                        // ...
                        User user1 = userMapper.queryComprehensiveManager(user.getDepartmentId());
                        if (null == user1) {
                            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"没有综合部经理");
                        }
                        title = TextMessage.WORKDIARY_NEW_TITLE;
                        content = TextMessage.WORKDIARY_NEW_CONTENT;
                        waitPush = user1;
                        details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_PENGDING,
                                new Date(), notes, user1.getId()));
                        workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_APPROVED);

                    } else {
                        // 查询 创建日志人的 经理
                        User manager = userMapper.queryManager(diary.getUserId());

                        if (RoleEnum.COMPREHENSIVE_MANAGER.ordinal() == manager.getRoleId()) {
                            // 如果 该日志是综合部的员工创建的，则直接设置状态
                            workDiary.setVerifier(manager.getId());
                            workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_APPROVED);

                            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_PENGDING,
                                    new Date(), notes, manager.getId()));
                            title = TextMessage.WORKDIARY_PENGDING_TITLE;
                            content = TextMessage.WORKDIARY_PENGDING_CONTENT;
                            waitPush = manager;
                        } else if (manager.getIsManager() == 1 && RoleEnum.COMPREHENSIVE_MANAGER.ordinal() != manager.getRoleId()
                                && manager.getId().equals(user.getId())) {

                            if (workDiary.getUserId().equals(manager.getId())) {
                                // 如果是自己的日志 交给 综合部来审核
                                User user1 = userMapper.queryComprehensiveManager(user.getDepartmentId());
                                if (null == user1) {
                                    throw new InterfaceCommonException(StatusConstant.Fail_CODE,"当前没有综合部经理");
                                }
                                details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_PENGDING,
                                        new Date(), notes, user1.getId()));
                                workDiary.setVerifier(user1.getId());
                                workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_APPROVED);
                                title = TextMessage.WORKDIARY_NEW_TITLE;
                                content = TextMessage.WORKDIARY_NEW_CONTENT;
                                waitPush = user1;
                            } else {
                                // 如果不是自己的日志 提交修改 推送给综合部
                                details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_APPROVED,
                                        workDiary.getUpdateTime(), notes, user.getId()));
                                workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_APPROVED);
                            }
                        } else {
                            // 如果是普通员工 推送给部门经理
                            User temp = userMapper.queryManagerByDepartment(user.getDepartmentId());
                            if (null == temp) {
                                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"当前部门没有部门经理");
                            }
                            details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_PENGDING,
                                    new Date(), notes, temp.getId()));
                            workDiary.setVerifier(temp.getId());
                            title = TextMessage.WORKDIARY_PENGDING_TITLE;
                            content = TextMessage.WORKDIARY_PENGDING_CONTENT;
                            waitPush = temp;
                        }
                    }
                } else {
                    // 其他管理层  全部交给 综合部经理审核
                    User user1 = userMapper.queryComprehensiveManager(user.getDepartmentId());
                    if (null == user1) {
                        throw new InterfaceCommonException(StatusConstant.Fail_CODE,"没有综合部经理");
                    }
                    details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_PENGDING,
                            new Date(), notes, user1.getId()));
                    workDiary.setVerifier(user1.getId());
                    title = TextMessage.WORKDIARY_NEW_TITLE;
                    content = TextMessage.WORKDIARY_NEW_CONTENT;
                    waitPush = user1;
                }
            } else {


                if (workDiary.getUserId().equals(user.getId())) {


                    details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SUBMIT,
                            new Date(), notes, user.getId()));

                    // 如果是 综合部经理的日志
                    // 自己审核自己  并且 直接通过审核
                    workDiary.setVerifier(user.getId());
                    workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_APPROVED);
                    details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_PENGDING,
                            new Date(), notes, user.getId()));
                } else {
                    // 获取日志的经理

                    User temp = userMapper.queryManagerByDepartment(userMapper.queryBaseInfo(workDiary.getUserId()).getDepartmentId());
                    if(temp.getId().equals(user.getId())){
                        details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SYNTHESIZE_APPROVED,
                                workDiary.getUpdateTime(), notes, user.getId()));
                        workDiary.setStatus(StatusConstant.WORKDIARY_SYNTHESIZE_STATUS_APPROVED);
                    }
                    else{
                        details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_APPROVED,
                                workDiary.getUpdateTime(), notes, user.getId()));
                        workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_APPROVED);
                    }
                }
            }
        }
        workDiaryMapper.updateWorkDiary(workDiary);
        if (details.size() > 0) {
            workDiaryStatusDetailMapper.batchAddWorkDiaryStatusDetail(details);
        }
        // 发送推送
        if (null != waitPush && !title.isEmpty() && !content.isEmpty()) {
            SystemInfo info = new SystemInfo();
            info.setUserId(waitPush.getId());
            info.setTitle(title);
            info.setContent(content);
            info.setType(SystemInfoEnum.WORK_DIARY_INFO.ordinal());
            systemInfoMapper.addSystemInfo(info);
            PushMessageUtil.pushMessages(waitPush, title, extend);
        }
    }

    private SystemInfo createSystemInfo(Integer userId, String title, String content, Integer type) {
        SystemInfo info = new SystemInfo();
        info.setType(type);
        info.setUserId(userId);
        info.setContent(content);
        info.setTitle(title);
        return info;
    }

    /**
     * 新增传递卡 临时保存为 草稿(api)
     * 返回 日志ID
     *
     * @param workDiary
     */
    @Transactional
    public Map<String, Integer> addWorkDiary(WorkDiary workDiary, WorkDiarySub sub) throws Exception {
        List<WorkDiarySub> list = workDiarySubMapper.isHave(sub.getStartTime(), sub.getEndTime(), workDiary.getUserId(), null, workDiary.getWorkTime());
        if (list.size() > 0) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST, "此时间段已有草稿存在");
        }
        // 传递卡 每天只能提交一次验重
        WorkDiary temp = workDiaryMapper.queryWorkDiaryByUser(workDiary.getUserId(), workDiary.getWorkTime());
        if(null != temp){
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"当天的日志已经存在");
        }
        workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_DRAFT);
        workDiaryMapper.addWorkDiary(workDiary);
        sub.setWorkDiaryId(workDiary.getId());
        workDiarySubMapper.addWorkDiarySub(sub);
        Integer subId = sub.getId();
        Integer workSubId = workDiary.getId();
        Map<String, Integer> data = new HashMap<>();
        data.put("subId", subId);
        data.put("workId", workSubId);
        return data;
    }

    /**
     * 新增传递卡 临时保存为 草稿(web)
     * 返回 日志ID
     *
     * @param workDiary
     */
    @Transactional
    public void addWorkDiary(WorkDiary workDiary) throws Exception {
        workDiary.setStatus(StatusConstant.WORKDIARY_STATUS_DRAFT);
        workDiaryMapper.addWorkDiary(workDiary);
    }


    /**
     * 创建 传递卡 进度明细
     *
     * @param workDiary
     * @param statusDescribe
     * @param date
     * @param notes
     * @return
     */
    private WorkDiaryStatusDetail createWorkDiaryStatusDetail(WorkDiary workDiary, String statusDescribe, Date date, String notes, Integer userId) {
        WorkDiaryStatusDetail detail = new WorkDiaryStatusDetail();
        detail.setUserId(userId);
        detail.setCreateTime(date);
        detail.setStatusDescribe(statusDescribe);
        detail.setWorkDiaryId(workDiary.getId());
        detail.setNotes(notes);
        return detail;
    }

    /**
     * 根据条件筛选 传递卡/工作日志 集合
     *
     * @param userId       用户/创建人ID
     * @param status       传递卡/工作日志 状态
     * @param companyId    分公司ID
     * @param departmentId 部门ID
     * @param jobTypeName  工作类型名称 (模糊查询)
     * @param jobContent   工作内容 (模糊查询)
     * @param limit        分页起始
     * @param limitSize    分页截至
     * @return WorkDiary 集合
     */
    public PageList<WorkDiary> queryWorkDiaryByItem(Integer userId, Integer status, Integer companyId,
                                                    Integer departmentId, String jobTypeName,
                                                    String jobContent, Integer limit, Integer limitSize) throws Exception {

        PageList<WorkDiary> pageList = new PageList<>();
        //获取总数
        List<WorkDiary> totalWorkDiary = workDiaryMapper.queryWorkDiaryByItem(userId, status, companyId, departmentId, jobTypeName, jobContent, null, null, null, 0);
        //总条数
        int count = 0;
        if (null != totalWorkDiary) {
            count = totalWorkDiary.size();
            pageList.setList(workDiaryMapper.queryWorkDiaryByItem(userId, status, companyId, departmentId, jobTypeName, jobContent, limit, limitSize, null, 0));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 根据 用户ID 和 时间 查询 当天提交的日志
     *
     * @param userId
     * @param workTime
     * @return 如果为null，则用户当天没有创建 否则已经创建，只能新增其 子表数据
     */
    public WorkDiary queryWorkDiaryByUser(Integer userId, Date workTime) {
        return workDiaryMapper.queryWorkDiaryByUser(userId, workTime);
    }


    /**
     * 根据条件筛选 传递卡/工作日志 集合 web端
     *
     * @param workDiary ：传递卡/工作日志 entity(
     *                  高级查询{
     *                  verifierName ：审核人名字
     *                  createTime ：创建时间
     *                  userName ：创建人名字
     *                  workTime ：工作日期
     *                  status ： 状态
     *                  } )
     * @param pageArgs  ： 分页 entity
     * @return
     */
    public PageList<WorkDiary> listForWeb(WorkDiary workDiary, PageArgs pageArgs, Integer type, Integer departmentId,
                                          Integer companyId) throws Exception {
        PageList<WorkDiary> pageList = new PageList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("workDiary", workDiary);
        map.put("type", type);
        map.put("departmentId", departmentId);
        map.put("companyId", companyId);
        //获取总数
        Integer listForWebCount = workDiaryMapper.listForWebCount(map);
        if (null != listForWebCount && listForWebCount > 0) {
            map.put("pageArgs", pageArgs);
            pageList.setList(workDiaryMapper.listForWeb(map));
        }
        pageList.setTotalSize(listForWebCount);
        return pageList;
    }

    /**
     * 通过ID 查询所有信息
     *
     * @param id
     * @return
     */
    public WorkDiary queryWorkDiaryById(Integer id, User currentUser) {
        WorkDiary workDiary = workDiaryMapper.queryWorkDiaryById(id);
        if (null != currentUser) {
            // 设置审核权限
            if (currentUser.getIsManager() == 1) {
                // 如果当前 用户是 经理级别的 可以审核 同部门的
                if (workDiary.getUser().getDepartmentId().equals(currentUser.getDepartmentId()) && !(workDiary.getUserId().equals(currentUser.getId()))) {
                    workDiary.setIsApproved(true);
                }
                if (RoleEnum.COMPREHENSIVE_MANAGER.ordinal() == currentUser.getRoleId()
                        && workDiary.getUser().getIsManager() == 1 && !(workDiary.getUserId().equals(currentUser.getId()))) {
                    // 如果当前用户是 综合部经理 并且当前 日志是 部门经理创建的、不是综合部经理自己创建的
                    workDiary.setIsApproved(true);
                }
            }
        }
        return workDiary;
    }


    /**
     * 根据条件筛选 传递卡/工作日志 集合
     *
     * @param userId       用户/创建人ID
     * @param status       传递卡/工作日志 状态
     * @param companyId    分公司ID
     * @param departmentId 部门ID
     * @param jobTypeName  工作类型名称 (模糊查询)
     * @param jobContent   工作内容 (模糊查询)
     * @param pageNO       分页起始
     * @param pageSize     分页截至
     * @param currentUser  当前请求用户
     * @return WorkDiary 集合
     */
    public List<WorkDiary> queryWorkDiaryByItemForAPI(Integer userId, Integer status, Integer companyId,
                                                      Integer departmentId, String jobTypeName,
                                                      String jobContent, Integer pageNO, Integer pageSize,
                                                      User currentUser, Integer orderBy) {
        List<WorkDiary> workDiaries = workDiaryMapper.queryWorkDiaryByItemForAPI(userId, status, companyId,
                departmentId, jobTypeName, jobContent, (pageNO - 1) * pageSize, pageSize, currentUser.getId(), orderBy);
        return workDiaries;
    }


    /***
     * 更新工作日志
     *
     * @param workDiary
     * @throws Exception
     */
    public void update(WorkDiary workDiary) throws Exception {
        workDiaryMapper.updateWorkDiary(workDiary);
    }

    /**
     * 统计日志 工作时长等
     * userId、companyId、departmentId 不能同时存在 只能单选
     *
     * @param userId       用户ID
     * @param companyId    分公司ID
     * @param departmentId 部门ID
     * @param time         时间 年月日 / 年月 当 flag:0 时，统计年月日 flag:1 统计 年月 不能为空
     * @param flag         当 flag:0 时，统计年月日 flag:1 统计 年月
     * @return
     */
    public Map<String, Object> countWorkDiary(Integer userId, Integer companyId, Integer departmentId,
                                              Integer flag, Date time) throws Exception {

        List<TimeTypeEntity> timeTypeEntities = workDiaryMapper.countWorkDiaryDetail(userId, companyId, departmentId, time, flag);
        List<TransactionType> types = transactionTypeMapper.list();
        TransactionType transaction = new TransactionType();

        // 拼装数据
        List<String> dateStr = new ArrayList<>();
        List<Double> studys = new ArrayList<>();
        List<Double> works = new ArrayList<>();
        List<Double> sports = new ArrayList<>();
        List<Double> totals = new ArrayList<>();
        List<String> userNames = new ArrayList<>();
        // 移动端数据
        List<TypeEntity> entities = new ArrayList<>();
        transaction.setId(0);
        types.add(transaction);
        // 当月的每一天数据
        List<Date> dateList = Util.getDateList(time);
        if (null == dateList || dateList.size() == 0) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (flag == 0) {

            if (departmentId == null) {
                // 不是柱状图
                for (TransactionType transactionType : types) {
                    // 设置每一种 一个月的数据
                    List<JobTime> jobTimes = new ArrayList<>();
                    for (Date date : dateList) {
                        // 遍历每一天
                        Double workTime = 0.0;
                        for (TimeTypeEntity timeTypeEntity : timeTypeEntities) {
                            if (transactionType.getId() == 0 && sdf.format(date).equals(sdf.format(timeTypeEntity.getWorkTime()))) {
                                // 统计每一天总的
                                workTime += timeTypeEntity.getJobTime();
                                break;
                            }
                            if (sdf.format(date).equals(sdf.format(timeTypeEntity.getWorkTime())) && transactionType.getId().equals(timeTypeEntity.getTimeTypeId())) {
                                // 如果时间相等
                                workTime = timeTypeEntity.getJobTime();
                                break;
                            }
                        }
                        JobTime jobTime = new JobTime();
                        jobTime.setJobTime(workTime);
                        jobTime.setDate(date);
                        jobTime.setJobTimeStr(Util.dateFortimestamp(date.getTime(), "yyyy-MM-dd"));
                        jobTimes.add(jobTime);
                    }
                    transactionType.setJobTimes(jobTimes);
                }
            }
        }
        // 按月统计
        List<Date> dateListMonth = Util.getMonthDate(time);
        if (null == dateListMonth || dateListMonth.size() == 0) {
            return null;
        }
        if (flag == 1) {
            if (departmentId == null) {
                // 不是柱状图
                for (TransactionType transactionType : types) {
                    // 设置每一种 一个月的数据
                    List<JobTime> jobTimes = new ArrayList<>();
                    for (Date date : dateListMonth) {
                        Double workTime = 0.0;
                        for (TimeTypeEntity timeTypeEntity : timeTypeEntities) {
                            Date timestamp = Util.dateFortimestamp(timeTypeEntity.getWorkTime().getTime(), "yyyy-MM", null);
                            if (transactionType.getId() == 0 && date.equals(timestamp)) {
                                // 统计每月总的
                                workTime += timeTypeEntity.getJobTime();
                                break;
                            }
                            if (date.equals(timestamp) && transactionType.getId().equals(timeTypeEntity.getTimeTypeId())) {
                                // 如果时间相等
                                workTime += timeTypeEntity.getJobTime();
                                break;
                            }
                        }
                        JobTime jobTime = new JobTime();
                        jobTime.setJobTime(workTime);
                        jobTime.setDate(date);
                        jobTime.setJobTimeStr(Util.dateFortimestamp(date.getTime(), "yyyy-MM"));
                        jobTimes.add(jobTime);
                    }
                    transactionType.setJobTimes(jobTimes);
                }
            }


        }
        if(departmentId != null){
            // 拼装柱状图
            Department department = departmentMapper.queryDepartmentById(departmentId,100,0);
            for (User user : department.getUsers()) {
                userNames.add(user.getName());
                Double total = 0.0;
                // 设置每个员工
                for (TransactionType type : types) {
                    Double jobTime = 0.0;
                    for (TimeTypeEntity timeTypeEntity : timeTypeEntities) {
                        if (type.getId() == 1 && timeTypeEntity.getTimeTypeId().equals(type.getId())
                                && user.getId().equals(timeTypeEntity.getUserId())) {
                            String format = "yyyy-MM";
                            if(flag == 0){
                                format = "yyyy-MM-dd";
                            }
                            Date timestamp = Util.dateFortimestamp(timeTypeEntity.getWorkTime().getTime(), format, null);
                            Date tempTime = Util.dateFortimestamp(time.getTime(), format, null);
                            if(timestamp.equals(tempTime)){
                                jobTime += timeTypeEntity.getJobTime();
                            }

                        } else if (type.getId() == 2 && timeTypeEntity.getTimeTypeId().equals(type.getId())
                                && user.getId().equals(timeTypeEntity.getUserId())) {
                            String format = "yyyy-MM";
                            if(flag == 0){
                                format = "yyyy-MM-dd";
                            }
                            Date timestamp = Util.dateFortimestamp(timeTypeEntity.getWorkTime().getTime(), format, null);
                            Date tempTime = Util.dateFortimestamp(time.getTime(), format, null);
                            if(timestamp.equals(tempTime)){
                                jobTime += timeTypeEntity.getJobTime();
                            }

                        } else if (type.getId() == 3 && timeTypeEntity.getTimeTypeId().equals(type.getId())
                                && user.getId().equals(timeTypeEntity.getUserId())) {
                            String format = "yyyy-MM";
                            if(flag == 0){
                                format = "yyyy-MM-dd";
                            }
                            Date timestamp = Util.dateFortimestamp(timeTypeEntity.getWorkTime().getTime(), format, null);
                            Date tempTime = Util.dateFortimestamp(time.getTime(), format, null);
                            if(timestamp.equals(tempTime)){
                                jobTime += timeTypeEntity.getJobTime();
                            }

                        }
                    }
                    if (type.getId() == 1) {
                        works.add(jobTime);
                    } else if (type.getId() == 2) {
                        studys.add(jobTime);
                    } else if (type.getId() == 3) {
                        sports.add(jobTime);
                    }
                    total += jobTime;
                }
                totals.add(total);
            }
        }

        // 移动端数据

        for (TransactionType transactionType : types) {
            TypeEntity entity = new TypeEntity();
            entity.setTypeId(transactionType.getId());
            entity.setTypeName(transactionType.getTransactionName());
            Double workTime = 0.0;
            for (TimeTypeEntity timeTypeEntity : timeTypeEntities) {
                if (timeTypeEntity.getTimeTypeId().equals(transactionType.getId())) {
                    if(flag == 0  && sdf.format(time).equals(sdf.format(timeTypeEntity.getWorkTime()))){
                        workTime += timeTypeEntity.getJobTime();
                    }
                    if (flag == 1
                            && DateStringUtil.dateFormt(time,"yyyy-MM").equals(DateStringUtil.dateFormt(timeTypeEntity.getWorkTime(),"yyyy-MM"))){
                        workTime += timeTypeEntity.getJobTime();
                    }

                }
            }
            entity.setTime(workTime);
            entities.add(entity);
        }


        if (departmentId == null) {
            for (TransactionType transactionType : types) {
                for (JobTime jobTime : transactionType.getJobTimes()) {
                    if (flag == 0 ? dateStr.size() < dateList.size() : dateStr.size() < dateListMonth.size()) {
                        dateStr.add(Util.dateFortimestamp(jobTime.getDate().getTime(),
                                flag == 0 ? "yyyy年MM月dd日" : "yyyy年MM月"));
                    }
                    if (transactionType.getId() == 1) {
                        // 工作
                        works.add(jobTime.getJobTime());
                    } else if (transactionType.getId() == 2) {
                        //学习
                        studys.add(jobTime.getJobTime());
                    } else if (transactionType.getId() == 3) {
                        //运动
                        sports.add(jobTime.getJobTime());
                    } else if (transactionType.getId() == 0) {
                        // 总的
                        totals.add(jobTime.getJobTime());
                    }
                }

            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("dateStr", dateStr);
        data.put("studys", studys);
        data.put("works", works);
        data.put("sports", sports);
        data.put("types", types);
        data.put("totals", totals);
        data.put("entities", entities);
        data.put("userNames", userNames);
        return data;
    }

    private TimeTypeEntity createTransactionType(Integer userId, Date date, TransactionType type) {
        TimeTypeEntity entity = new TimeTypeEntity();
        entity.setUserId(userId);
        entity.setWorkTime(date);
        entity.setJobTime(0.0);
        entity.setTimeTypeId(type.getId());
        entity.setTimeName(type.getTransactionName());
        return entity;
    }

    public WorkDiary queryWorkDiaryById(Integer id) {
        return workDiaryMapper.queryWorkDiaryById(id);
    }

    /**
     * 查询用户 关联的日志
     *
     * @param userId 用户ID
     * @return 日志集合
     */
    public List<WorkDiary> queryWorkDiaryCCByUser(Integer userId,Integer pageNO,Integer pageSize) {
        return workDiaryMapper.queryWorkDiaryCCByUser(userId,(pageNO - 1) * pageSize,pageSize);
    }

    /**
     * 批量更新传递卡状态 web
     *
     * @param ids          要更新的传递卡id 集合 可以为空
     * @param type         1：ids不能为空 更新传回来的ids集合  2:更新我的团队 3：综合部经理更新全部
     * @param roleId       角色id
     * @param userId       用户id
     * @param departmentId 部门id
     */
    @Transactional
    public void updateListStatus(String ids, Integer type, Integer roleId, Integer departmentId, Integer userId) {

        List<WorkDiary> list = workDiaryMapper.isHaveAuditWork(type, departmentId);
        if (list.size() == 0) {
            throw new InterfaceCommonException(StatusConstant.NO_DATA, "对不起，没有需要审核的传递卡");
        }
        Integer status = StatusConstant.WORKDIARY_STATUS_APPROVED;
        if (type == 3 && !roleId.equals(RoleEnum.COMPREHENSIVE_MANAGER.ordinal())) {
            throw new InterfaceCommonException(StatusConstant.NOT_AGREE, "对不起，你不是综合部经理，没有权限通过全部传递卡");
        }
        //综合部经理
        if (roleId.equals(RoleEnum.COMPREHENSIVE_MANAGER.ordinal())) {
            status = StatusConstant.WORKDIARY_SYNTHESIZE_STATUS_APPROVED;
        }
        Integer[] idInts = null;
        if (null != ids && !"".equals(ids.trim())) {
            String[] idStrs = ids.split(",");
            idInts = new Integer[idStrs.length];
            for (int i = 0; i < idStrs.length; i++) {
                idInts[i] = Integer.parseInt(idStrs[i]);
            }
        }
        List<WorkDiaryStatusDetail> details = new ArrayList<>();
        User waitPush = null;
        String title = null, content = null;
        if (null != idInts) {
            for (int i = 0; i < idInts.length; i++) {
                WorkDiary workDiary = new WorkDiary();
                workDiary.setId(idInts[i]);
                // 创建记录
                if (RoleEnum.COMPREHENSIVE_MANAGER.ordinal() == roleId) {
                    // 如果是综合部 经理 审核
                    details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SYNTHESIZE_APPROVED,
                            new Date(), null, userId));
                } else {
                    // 如果是其他人审核
                    details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_APPROVED,
                            new Date(), null, userId));
                    // 通知 综合部经理
                    // 推送给综合部经理 审核
                    if (null == waitPush) {
                        User user1 = userMapper.queryComprehensiveManager(departmentId);
                        if (null == user1) {
                            throw new InterfaceCommonException(StatusConstant.USER_DOES_NOT_EXIST, "没有综合部经理");
                        }
                        title = TextMessage.WORKDIARY_NEW_TITLE;
                        content = TextMessage.WORKDIARY_NEW_CONTENT;
                        waitPush = user1;
                    }
                }
            }
        }

        workDiaryMapper.updateListStatus(idInts, type, roleId, status, departmentId);
        // 新增
        if (details.size() > 0) {
            workDiaryStatusDetailMapper.batchAddWorkDiaryStatusDetail(details);
        }
        // 发送推送
        if (null != waitPush && !title.isEmpty() && !content.isEmpty()) {
            SystemInfo info = new SystemInfo();
            info.setUserId(waitPush.getId());
            info.setTitle(title);
            info.setContent(content);
            info.setType(SystemInfoEnum.WORK_DIARY_INFO.ordinal());
            systemInfoMapper.addSystemInfo(info);
            Map<String, String> extend = new HashMap<>();
            extend.put("infoType", SystemInfoEnum.WORK_DIARY_INFO.ordinal() + "");
            PushMessageUtil.pushMessages(waitPush, title, extend);
        }
    }


    /**
     * 批量更新传递卡状态
     */
    @Transactional
    public void updateListStatus(List<WorkDiary> workDiaries, User user) throws Exception {

        List<WorkDiaryStatusDetail> details = new ArrayList<>();
        User waitPush = null;
        String title = null, content = null;
        for (WorkDiary workDiary : workDiaries) {
            // 创建记录
            if (RoleEnum.COMPREHENSIVE_MANAGER.ordinal() == user.getRoleId()) {
                // 如果是综合部 经理 审核
                details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_SYNTHESIZE_APPROVED,
                        workDiary.getUpdateTime(), null, user.getId()));
            } else {
                // 如果是其他人审核
                details.add(createWorkDiaryStatusDetail(workDiary, StatusConstant.WORKDIARY_DETAIL_APPROVED,
                        workDiary.getUpdateTime(), null, user.getId()));
                // 通知 综合部经理
                // 推送给综合部经理 审核
                if (null == waitPush) {
                    User user1 = userMapper.queryComprehensiveManager(user.getDepartmentId());
                    if (null == user1) {
                        throw new Exception("没有综合部经理");
                    }
                    title = TextMessage.WORKDIARY_NEW_TITLE;
                    content = TextMessage.WORKDIARY_NEW_CONTENT;
                    waitPush = user1;
                }
            }
        }
        workDiaryMapper.batchUpdateWorkDiaryStatus(workDiaries);
        // 新增
        if (details.size() > 0) {
            workDiaryStatusDetailMapper.batchAddWorkDiaryStatusDetail(details);
        }
        // 发送推送
        if (null != waitPush && !title.isEmpty() && !content.isEmpty()) {
            SystemInfo info = new SystemInfo();
            info.setUserId(waitPush.getId());
            info.setTitle(title);
            info.setContent(content);
            info.setType(SystemInfoEnum.WORK_DIARY_INFO.ordinal());
            systemInfoMapper.addSystemInfo(info);
            Map<String, String> extend = new HashMap<>();
            extend.put("infoType", SystemInfoEnum.WORK_DIARY_INFO.ordinal() + "");
            PushMessageUtil.pushMessages(waitPush, title, extend);
        }

    }

}
