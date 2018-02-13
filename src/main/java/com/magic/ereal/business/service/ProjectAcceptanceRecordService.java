package com.magic.ereal.business.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.enums.RoleEnum;
import com.magic.ereal.business.enums.SystemInfoEnum;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.*;
import com.magic.ereal.business.push.PushMessageUtil;
import com.magic.ereal.business.util.StatusConstant;
//import net.sf.json.JSONObject;
import com.magic.ereal.business.util.TextMessage;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * 外部项目 结项记录 -- 业务
 * @author lzh
 * @create 2017/5/3 15:43
 */
@Service
public class ProjectAcceptanceRecordService {

    @Resource
    private IProjectAcceptanceRecordMapper projectAcceptanceRecordMapper;

    @Resource
    private IProjectWeekAcceptanceMapper projectWeekAcceptanceMapper;
    @Resource
    private IProjectMapper projectMapper;
    @Resource
    private IProjectKMapper projectKMapper;
    @Resource
    private AllConfigService allConfigService;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private ISystemInfoMapper systemInfoMapper;

    /**
     * 结项记录集合
     * @param projectId 外部项目id
     * @param type 验收类型 0：破题  1：内部  2：外部
     * @return
     */
    public List<ProjectAcceptanceRecord> list(Integer projectId , Integer type){
        if (null == projectId || null == type) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        }
        return projectAcceptanceRecordMapper.list(projectId, type);
    }



    /**
     * 项目进展状态显示
     * @param projectId 外部项目id
     * @return
     */
    public List<Acceptance> acceptanceList(Integer projectId) {
        if (null == projectId) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        }
        Project project = projectMapper.queryBaseProjectById(projectId);
        if(null == project){
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"项目不存在");
        }
        AllConfig config = allConfigService.getConfig();
        List<Acceptance> list = new ArrayList<>();
        //type 验收类型 0：破题  1：内部  2：外部
        //外部
        List<ProjectAcceptanceRecord> wList = projectAcceptanceRecordMapper.list(projectId, 2);
        if (null != wList && wList.size() > 0) {
            list.add(encapsulationAcceptance(wList,"外部项目结项"));
        }
        //内部
        List<ProjectAcceptanceRecord> nList = projectAcceptanceRecordMapper.list(projectId, 1);
        if (null != nList && nList.size() > 0) {
            list.add(encapsulationAcceptance(nList,"内部项目结项"));
        }
        //周验收
        List<ProjectWeekAcceptance> pwList = projectWeekAcceptanceMapper.queryProjectWeekAcceptanceByProject(projectId);
        if (null != pwList && pwList.size() > 0) {
            Acceptance ac = new Acceptance();
            ac.setAcceptanceName("周验收");
            ac.setType(3);
            ac.setStatus(pwList.get(0).getStatus());
            ac.setObjectId(pwList.get(0).getId());
            if (pwList.get(0).getStatus() == 0) {
                if (pwList.size() == 1) {
                    ac.setScore(0.0);
                } else {

                    ProjectWeekAcceptance weekAcceptance = pwList.get(1);
                    double v = weekAcceptance.getTotalK() / (project.getInitWorkload() * config.getDayHour() / config.getkHour());
                    ac.setScore(v);
                    for (ProjectWeekAcceptance p : pwList) {
                        if (null != p.getSectionDetail()) {
                            List<ProjectWeekJson> projectWeekJson = JSONArray.parseArray(p.getSectionDetail(),ProjectWeekJson.class);
                            //临时记录  记录钱三阶段是否到100% 是否可以进行内部结项
                            pwList.get(0).getTotalK();
                            Double isCanNBJXchedule = 0.0;
                            for (ProjectWeekJson weekJson : projectWeekJson) {
                                if (weekJson.getSectionNum() != 4) {
                                    isCanNBJXchedule += weekJson.getSchedule();
                                }
                            }
                            //是否可以内部结项  0：不能  1：可以
                            ac.setIsCanNBJX(0);
                            if (isCanNBJXchedule >= 300) {
                                ac.setIsCanNBJX(1);
                            }
                            break;
                        }
                    }
                }
            } else {
                double v = pwList.get(0).getTotalK() / (project.getInitWorkload() * config.getDayHour() / config.getkHour());
                ac.setScore(v);
                for (ProjectWeekAcceptance p : pwList) {
                    if (null != p.getSectionDetail()) {
                        List<ProjectWeekJson> projectWeekJson = JSONArray.parseArray(p.getSectionDetail(),ProjectWeekJson.class);
                        //临时记录  记录钱三阶段是否到100% 是否可以进行内部结项
                        Double isCanNBJXchedule = 0.0;
                        for (ProjectWeekJson weekJson : projectWeekJson) {
                            if (weekJson.getSectionNum() != 4) {
                                isCanNBJXchedule += weekJson.getSchedule();
                            }

                        }
                        //是否可以内部结项  0：不能  1：可以
                        ac.setIsCanNBJX(0);
                        if (isCanNBJXchedule >= 300) {
                            ac.setIsCanNBJX(1);
                        }
                        break;
                    }

                }
            }

            list.add(ac);
        }
        else{
            list.add(new Acceptance());
        }
        return list;
    }

    /**
     * 进行记录
     * @param projectAcceptanceRecord
     * @param ptStatus 破题状态 5000 ：未破  5002：半破  5004：全破
     * @throws Exception
     */
    @Transactional
    public void save(ProjectAcceptanceRecord projectAcceptanceRecord ,User user,Integer ptStatus) throws Exception {

        SystemInfo info = new SystemInfo();
        info.setType(SystemInfoEnum.PROJECT_INFO.ordinal());

        Map<String,String> params = new HashMap<>(); // 扩展参数
        params.put("type",info.getType().toString());

        User waitPush = null; //等待推送的用户
        List<User> waitPushs = null; //等待推送的用户

        List<SystemInfo> infos = new ArrayList<>();

        //获取项目详情
        Project project = projectMapper.queryBaseProjectById(projectAcceptanceRecord.getProjectId());
        if (null == project) {
            throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,"数据异常，没有此项目");
        }
        Project project1 = new Project();
        /** type 验收类型 0：破题  1：内部  2：外部 */
        if (projectAcceptanceRecord.getType().equals(0)) {
            /** status 验收状态 0：等待验收  1：通过  2：验收失败 */

            //破题
            if (project.getStatus().equals(StatusConstant.PO_ALL)) {
                throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                        "当前项目处于全破状态，请不要再进行破题的相关操作");
            }
            //申请破题验收
            if (projectAcceptanceRecord.getStatus().equals(0)) {
                //A导师 / 分配给某部门的 部门经理  申请破题验收
//                if (!user.getRoleId().equals(RoleEnum.PROJECT_MANAGER.ordinal()) ||
//                        !user.getId().equals(project.getaTeacher())) {
//                    throw new InterfaceCommonException(StatusConstant.NOT_AGREE,
//                            "你没有权限进行申请破题验收操作");
//                } else {
                    if (project.getStatus().equals(StatusConstant.PO_NONE)) {
                        //设置破题状态
                        project1.setStatus(StatusConstant.PO_HALF_ING);
                    } else if (project.getStatus().equals(StatusConstant.PO_HALF)) {
                        //设置破题状态
                        project1.setStatus(StatusConstant.PO_ALL_ING);
                    }

//                }

                // 申请破题验收 推送给C导师
                ArrayList<Integer> ids = new ArrayList<>();
                ids.add(project.getcTeacherId());
                waitPush = userMapper.queryUserDeviceTypeAndToken(ids).get(0);
                info.setTitle(TextMessage.PROJECT_APPLICATION_PO_TITLE);
                info.setTitle(MessageFormat.format(TextMessage.PROJECT_APPLICATION_PO_CONTENT,project.getProjectNameShort()));
                info.setUserId(waitPush.getId());


            } else if(projectAcceptanceRecord.getStatus().equals(1)
                    || projectAcceptanceRecord.getStatus().equals(2)) {
                //c导师进行破题验收
//                if (!user.getRoleId().equals(RoleEnum.C_TEACHER.ordinal()) ||
//                        !user.getId().equals(project.getcTeacherId())) {
//                    throw new InterfaceCommonException(StatusConstant.NOT_AGREE,
//                            "你没有权限进行破题验收操作");
//                } else {
                    if (!project.getStatus().equals(StatusConstant.PO_HALF_ING) &&
                            !project.getStatus().equals(StatusConstant.PO_ALL_ING)) {
                        throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                                "破题状态异常，当前未申请破题验收");
                    } else {
                        //破题状态
                        if (null == ptStatus) {
                            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,
                                    "字段不能为空");
                        }
                        if (!ptStatus.equals(StatusConstant.PO_NONE) &&
                                !ptStatus.equals(StatusConstant.PO_HALF) && !ptStatus.equals(StatusConstant.PO_ALL)) {
                            throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                                    "破题状态异常");
                        }
                        //设置破题状态
                        project1.setStatus(ptStatus);
                        //半破时间
                        if (ptStatus.equals(StatusConstant.PO_HALF)) {
                            project1.setPoHalfTime(new Date());
                        }
                        //全破时间
                        if (ptStatus.equals(StatusConstant.PO_ALL)) {
                            project1.setPoAllTime(new Date());
                        }
                    }
                    List<Integer> ids = new ArrayList<>();
                    ids.add(project.getaTeacher());
                    waitPush = userMapper.queryUserDeviceTypeAndToken(ids).get(0);
                    info.setTitle(TextMessage.PROJECT_APPROVED_PO_TITLE);
                    info.setTitle(MessageFormat.format(TextMessage.PROJECT_APPROVED_PO_CONTENT,project.getProjectNameShort()));
                    info.setUserId(waitPush.getId());
//                }
            } else {
                throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                        "破题验收状态异常");
            }
        } else if (projectAcceptanceRecord.getType().equals(1)) {
            //内部结项
            if (project.getStatus() < StatusConstant.PO_ALL) {
                throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                        "当前项目处于未全破状态，不能进行内部结项");
            }
            //获取周验收集合
            List<ProjectWeekAcceptance> list =
                    projectWeekAcceptanceMapper.queryProjectWeekAcceptanceByProject(projectAcceptanceRecord.getProjectId());
            //是否可以进行内部结项
            boolean flag = false;
            for (ProjectWeekAcceptance acceptance : list) {
                if (null == acceptance.getSectionDetail()) {
                    throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                            "当前项目还有周验收未进行验收，不能进行内部结项");
                }
                if (null == acceptance.getProjectWeekKAllocations() || acceptance.getProjectWeekKAllocations().size() == 0) {
                    throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                            "当前项目还有周验收成果未分配，不能进行内部结项");
                }
                List<ProjectWeekJson> projectWeekJsons = JSONArray.parseArray(acceptance.getSectionDetail(),ProjectWeekJson.class);
                //总进度
                Double total = 0.0;
                for (ProjectWeekJson json : projectWeekJsons) {
                    if (json.getSectionNum() != 4) {
                        total += json.getSchedule();
                    }
                    if (total >= 300) {
                        flag = true;
                        break;
                    }
                }
            }
//            if (!flag) {
//                throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
//                        "当前项目前三阶段进度没有达到100%，不能进行内部结项");
//            }

            //验收是否存在内部结项处于未分配状态
            List<ProjectAcceptanceRecord> projectAcceptanceRecords =
                    projectAcceptanceRecordMapper.list(projectAcceptanceRecord.getProjectId(),1);

            if (projectAcceptanceRecords.size() > 0) {
                if (projectAcceptanceRecords.get(0).getStatus().equals(12) ||
                        projectAcceptanceRecords.get(0).getStatus().equals(13)) {
                    List<ProjectK> projectKs = projectKMapper.queryProjectKByProjectRecordId(projectAcceptanceRecords.get(0).getId());
                    if (null == projectKs || projectKs.size() < 1) {
                        throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                                "当前内部结项还未分配成果，不能再次进行内部结项");
                    }
                }
            }


            /** status 10：等待验收（a导师申请结项）
                       11：等待c导师审核/抽查/打分（根据b导师打分显示）（b导师打分）
                       12：通过
                       13：不通过（打回） */

            if (projectAcceptanceRecord.getStatus().equals(10)) {
                //申请内部结项
//                if (!user.getRoleId().equals(RoleEnum.PROJECT_MANAGER.ordinal()) ||
//                        !user.getId().equals(project.getaTeacher())) {
//                    throw new InterfaceCommonException(StatusConstant.NOT_AGREE,
//                            "你没有权限进行申请内部验收操作");
//                } else {
                    //申请内部结项状态
                    project1.setStatus(StatusConstant.INTERIOR_OVER_ING);


                    List<Integer> ids = new ArrayList<>();
                    ids.add(project.getbTeacherId());
                    waitPush = userMapper.queryUserDeviceTypeAndToken(ids).get(0);
                    info.setTitle(TextMessage.PROJECT_APPLICATION_NB_TITLE);
                    info.setTitle(MessageFormat.format(TextMessage.PROJECT_APPLICATION_NB_CONTENT,project.getProjectNameShort()));
                    info.setUserId(waitPush.getId());

//                }
            } else if(projectAcceptanceRecord.getStatus().equals(11)) {
                //B导师打分
//                if (!user.getRoleId().equals(RoleEnum.PROJECT_MANAGER.ordinal()) ||
//                        !user.getId().equals(project.getbTeacherId())) {
//                    throw new InterfaceCommonException(StatusConstant.NOT_AGREE,
//                            "你没有权限进行内部验收打分的操作");
//                } else {
//                    if (!project.getStatus().equals(StatusConstant.INTERIOR_OVER_ING)) {
//                        throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
//                                "内部验收状态异常，当前还未申请内部验收操作");
//                    } else {
                        //申请内部结项状态
                        project1.setStatus(StatusConstant.INTERIOR_OVER_ING);

                        List<Integer> ids = new ArrayList<>();
                        ids.add(project.getcTeacherId());
                        waitPush = userMapper.queryUserDeviceTypeAndToken(ids).get(0);
                        info.setTitle(TextMessage.PROJECT_APPLICATION_NB_C_TITLE);
                        info.setTitle(MessageFormat.format(TextMessage.PROJECT_APPLICATION_NB_C_CONTENT,project.getProjectNameShort()));
                        info.setUserId(waitPush.getId());
//                    }
//                }
            } else {
                //C导师打分
//                if (!user.getRoleId().equals(RoleEnum.C_TEACHER.ordinal()) ||
//                        !user.getId().equals(project.getcTeacherId())) {
//                    throw new InterfaceCommonException(StatusConstant.NOT_AGREE,
//                            "你没有权限进行内部验收打分的操作");
//                } else {

                    //内部
                    List<ProjectAcceptanceRecord> nList = projectAcceptanceRecordMapper.list(project.getId(), 1);
                    if (!nList.get(0).getCreateUserId().equals(project.getbTeacherId()) ||
                            !project.getStatus().equals(StatusConstant.INTERIOR_OVER_ING)) {
                        throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                                "内部验收状态异常，当前还未申请内部验收操作或B导师未进行打分操作");
                    }
                    //打分判断是否通过
                    if (!projectAcceptanceRecord.getStatus().equals(12)) {
                        if (null == projectAcceptanceRecord.getScore()) {
                            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,
                                    "请进行打分操作");
                        } else {
                            if (projectAcceptanceRecord.getScore() >= 70) {
                                project1.setStatus(StatusConstant.INTERIOR_OVER);
                                project1.setOverTime(new Date());
                            } else {
                                //内部结项失败 返回为全破状态
                                project1.setStatus(StatusConstant.PO_ALL);


                                // 打分小于70  推送给 绩效专员和 项目处执委
                                waitPushs = userMapper.queryUserDevice(project.getDepartmentId());

                                for (User push : waitPushs) {
                                    SystemInfo temp = new SystemInfo();
                                    temp.setType(SystemInfoEnum.PROJECT_INFO.ordinal());
                                    temp.setUserId(push.getId());
                                    temp.setTitle(TextMessage.PROJECT_APPLICATION_NB_ALL_TITLE);
                                    temp.setContent(MessageFormat.format(TextMessage.PROJECT_APPLICATION_NB_ALL_CONTENT,
                                            project.getProjectNumber(),projectAcceptanceRecord.getScore()));
                                    infos.add(temp);
                                }

                            }
                        }
                    } else {
                        if (null == projectAcceptanceRecord.getScore() || projectAcceptanceRecord.getScore() == 0) {
                            //c导师直接通过 获取B导师打分 进行记录
                            projectAcceptanceRecord.setScore(nList.get(0).getScore());
                            //审核通过
                            project1.setStatus(StatusConstant.INTERIOR_OVER);
                            project1.setOverTime(new Date());

                        } else {
                            if (projectAcceptanceRecord.getScore() >= 70) {
                                //审核通过
                                project1.setStatus(StatusConstant.INTERIOR_OVER);
                                project1.setOverTime(new Date());
                            } else {
                                //内部结项失败 返回为全破状态
                                project1.setStatus(StatusConstant.PO_ALL);

                                // 打分小于70  推送给 绩效专员和 项目处执委
                                waitPushs = userMapper.queryUserDevice(project.getDepartmentId());

                                for (User push : waitPushs) {
                                    SystemInfo temp = new SystemInfo();
                                    temp.setType(SystemInfoEnum.PROJECT_INFO.ordinal());
                                    temp.setUserId(push.getId());
                                    temp.setTitle(TextMessage.PROJECT_APPLICATION_NB_ALL_TITLE);
                                    temp.setContent(MessageFormat.format(TextMessage.PROJECT_APPLICATION_NB_ALL_CONTENT,
                                            project.getProjectNumber(),projectAcceptanceRecord.getScore()));
                                    infos.add(temp);
                                }
                            }
                        }
                    }
//                }
                // TODO: 2017/5/10 计算内部结项k值 初始工作量 * 8.5 / 135 * 进度分 / 100
                {
                    //当前总k值
                    projectAcceptanceRecord.setSumK(project.getInitWorkload() * 8.5 / 135 * projectAcceptanceRecord.getScore() / 100);
                    //k差值 默认当前总k值
                    Double badSumK = projectAcceptanceRecord.getSumK();
                    //是否上升 isAdd  0:否  1：是 默认上升
                    projectAcceptanceRecord.setIsAdd(1);
                    //获取上次打分数据
                    List<ProjectAcceptanceRecord> ps = projectAcceptanceRecordMapper.getKList(project.getId(),1,13);
                    if (ps.size() > 0) {
                        //k差值
                        badSumK = projectAcceptanceRecord.getSumK() - ps.get(0).getSumK();
                        //是否上升 isAdd  0:否  1：是
                        if (badSumK < 0) {
                            projectAcceptanceRecord.setIsAdd(0);
                        }
                    }else{
                        //如果上次打分数据不存在，则用最后一周的K值计算
                        // 查询上一周的K值 对比 设置是K值  增减
                        ProjectWeekAcceptance weekAcceptance = projectWeekAcceptanceMapper.queryProjectPreWeek(project.getId());
                        badSumK = projectAcceptanceRecord.getSumK() - weekAcceptance.getTotalK();
                        //是否上升 isAdd  0:否  1：是
                        if (badSumK < 0) {
                            projectAcceptanceRecord.setIsAdd(0);
                        }
                    }
                    projectAcceptanceRecord.setBadSumK(badSumK);
                }



            }
        } else if(projectAcceptanceRecord.getType().equals(2)) {
            //外部结项
            if (project.getStatus() < StatusConstant.INTERIOR_OVER) {
                throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                        "当前项目处于未内部结项状态，不能进行外部结项");
            } else {
                if (projectAcceptanceRecord.getStatus().equals(0)) {
                    //A导师 / 分配给某部门的 部门经理  申请外部验收
//                    if (!user.getRoleId().equals(RoleEnum.PROJECT_MANAGER.ordinal()) ||
//                            !user.getId().equals(project.getaTeacher())) {
//                        throw new InterfaceCommonException(StatusConstant.NOT_AGREE,
//                                "你没有权限进行申请外部验收操作");
//                    } else {
                        //内部
                        List<ProjectAcceptanceRecord> list = projectAcceptanceRecordMapper.list(projectAcceptanceRecord.getProjectId(), 1);
                        if (null != list && list.size() > 0) {
                            if ((list.get(0).getType() == 2 && (list.get(0).getStatus() == 1 ||  list.get(0).getStatus() == 2))
                                    || (list.get(0).getType() == 1 && (list.get(0).getStatus() == 12 || list.get(0).getStatus() == 13 ))) {
                                List<ProjectK> projectKs = projectKMapper.queryProjectKByProjectRecordId(list.get(0).getId());
                                if (projectKs.size() == 0 ) {
                                    throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                                            "当前项目处于结项成果未分配状态，不能进行外部结项");
                                }
                            }
                        } else {
                            throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,
                                    "当前项目处于未内部结项状态，不能进行外部结项");
                        }


                        project1.setStatus(StatusConstant.EXTERIOR_OVER_ING);
//                    }
                } else {
                    /** status 验收状态 0：等待验收  1：通过  2：验收失败 */
//                    if (!user.getRoleId().equals(RoleEnum.PERFORMANCE_EMPLOYEE.ordinal())) {
//                        throw new InterfaceCommonException(StatusConstant.NOT_AGREE,
//                                "你不是绩效专员，你没有权限进行破题验收操作");
//                    } else {
                        if (null != projectAcceptanceRecord.getStatus() && projectAcceptanceRecord.getStatus().equals(1)) {
                            //外部结项完成
                            project1.setStatus(StatusConstant.EXTERIOR_OVER);
                            project1.setExteriorOverTime(new Date());
                            project1.setPerformanceUserId(user.getId());
                        } else {
                            //外部结项中
                            project1.setStatus(StatusConstant.EXTERIOR_OVER_ING);
                            project1.setPerformanceUserId(user.getId());
                        }

                        // 推送
                        if(null != projectAcceptanceRecord.getStatus() && projectAcceptanceRecord.getStatus().equals(0)){

                            // 申请外部结项  推送给 绩效专员
                            waitPushs = userMapper.queryPerformanceEmployee(project.getDepartmentId());
                            for (User push : waitPushs) {
                                SystemInfo temp = new SystemInfo();
                                temp.setType(SystemInfoEnum.PROJECT_INFO.ordinal());
                                temp.setUserId(push.getId());
                                temp.setTitle(TextMessage.PROJECT_APPLICATION_WB_TITLE);
                                temp.setContent(MessageFormat.format(TextMessage.PROJECT_APPLICATION_WBL_CONTENT,
                                        project.getProjectNameShort()));
                                infos.add(temp);
                            }
                        }
                        else{
                            // 外部结项 结束
                            waitPush = userMapper.queryBaseInfo(project.getaTeacher());
                            info.setUserId(waitPush.getId());
                            info.setTitle(TextMessage.PROJECT_APPROVED_WB_TITLE);
                            info.setContent(MessageFormat.format(TextMessage.PROJECT_APPROVED_WB_CONTENT,
                                    project.getProjectNameShort()));
                        }
                        // TODO: 2017/5/10 计算外部结项k值 初始工作量 * 8.5 / 135 * 进度分 / 100
                        {
                            //当前总k值
                            projectAcceptanceRecord.setSumK(project.getInitWorkload() * 8.5 / 135 * projectAcceptanceRecord.getScore() / 100);
                            //k差值 默认当前总k值
                            Double badSumK = projectAcceptanceRecord.getSumK();
                            //是否上升 isAdd  0:否  1：是 默认上升
                            projectAcceptanceRecord.setIsAdd(1);
                            // 记录 绩效专员 ID
                            projectAcceptanceRecord.setPerformanceUserId(user.getId());
                            //获取上次打分数据
//                            List<ProjectAcceptanceRecord> ps = projectAcceptanceRecordMapper.getKList(project.getId(),2,2);
                            List<ProjectAcceptanceRecord> ps = projectAcceptanceRecordMapper.getKListTwo(project.getId(),2,2);
                            if (ps.size() > 0) {
                                //k差值
                                badSumK = projectAcceptanceRecord.getSumK() - ps.get(0).getSumK();
                                //是否上升 isAdd  0:否  1：是
                                if (badSumK < 0) {
                                    projectAcceptanceRecord.setIsAdd(0);
                                }
                            }else{
                                //获取上次打分数据
                                List<ProjectAcceptanceRecord> pss = projectAcceptanceRecordMapper.getKList(project.getId(),1,13);
                                badSumK = projectAcceptanceRecord.getSumK() - pss.get(0).getSumK();
                                //是否上升 isAdd  0:否  1：是
                                if (badSumK < 0) {
                                    projectAcceptanceRecord.setIsAdd(0);
                                }
                            }
                            projectAcceptanceRecord.setBadSumK(badSumK);
                        }
//                    }
                }
            }
        }
        //更新项目状态
        project1.setId(project.getId());
        projectMapper.updateProject(project1);
        //记录创建者id
        projectAcceptanceRecord.setCreateUserId(user.getId());
        projectAcceptanceRecordMapper.save(projectAcceptanceRecord);
        // 推送


        if(null != waitPush && null != info.getTitle()){
            systemInfoMapper.addSystemInfo(info);
            PushMessageUtil.pushMessages(waitPush,info.getTitle(),params);
        }

        if(null != waitPushs && waitPushs.size() > 0 && infos.size() > 0){
            systemInfoMapper.batchAddSystemInfo(infos);
            for (User push : waitPushs) {
                for (SystemInfo systemInfo : infos) {
                    if(push.getId().equals(systemInfo.getUserId())){
                        PushMessageUtil.pushMessages(push,systemInfo.getTitle(),params);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 封装 项目进展状态显示
     * @param list
     * @param name
     * @return
     */
    public Acceptance encapsulationAcceptance(List<ProjectAcceptanceRecord> list,String name) {
        Acceptance acceptance = new Acceptance();
        if (null != list && list.size() > 0) {
            acceptance.setObjectId(list.get(0).getId());
            acceptance.setStatus(list.get(0).getStatus());
            acceptance.setAcceptanceName(name);
            acceptance.setType(list.get(0).getType());
            if ((list.get(0).getType() == 2 && list.get(0).getStatus() == 0)
                    || (list.get(0).getType() == 1 && list.get(0).getStatus() == 10 )) {
                if (list.size() == 1) {
                    acceptance.setScore(0.0);
                } else {
                    for (ProjectAcceptanceRecord projectAcceptanceRecord : list) {
                        if (null != projectAcceptanceRecord.getScore() && 0 != projectAcceptanceRecord.getScore()) {
                            acceptance.setScore(projectAcceptanceRecord.getScore());
                            break;
                        }
                    }
                }
            } else {
                acceptance.setScore(list.get(0).getScore());

                if ((list.get(0).getType() == 2 && (list.get(0).getStatus() == 1 ||  list.get(0).getStatus() == 2))
                        || (list.get(0).getType() == 1 && (list.get(0).getStatus() == 12 || list.get(0).getStatus() == 13 ))) {
                    List<ProjectK> projectKs = projectKMapper.queryProjectKByProjectRecordId(list.get(0).getId());
                    if (projectKs.size() > 0 ) {
                        //status 0：等待验收  1：通过  2：验收失败 3:已分配比例
                        acceptance.setStatus(3);
                    }
                }

            }
        }
        return acceptance;
    }


}
