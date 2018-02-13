package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IWorkDiaryMapper;
import com.magic.ereal.business.mapper.IWorkDiaryStatusDetailMapper;
import com.magic.ereal.business.mapper.IWorkDiarySubMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.StatusConstant;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 传递卡 详情列 -- 业务
 * @author lzh
 * @create 2017/4/24 14:07
 */
@Service
public class WorkDiarySubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IWorkDiarySubMapper workDiarySubMapper;

    @Resource
    private IWorkDiaryMapper workDiaryMapper;
    @Resource
    private IWorkDiaryStatusDetailMapper workDiaryStatusDetailMapper;



    public WorkDiarySub queryNewSub(Integer workDiaryId){
        return workDiarySubMapper.queryNewSub(workDiaryId);
    }
    /**
     *  导出 传递卡
     * @param companyId
     * @param departmentId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ExcelOfWorkDiary> excelOfWorkDiary(Integer companyId,Integer departmentId,
                                                   Date startTime,Date endTime){
        return workDiarySubMapper.excelOfWorkDiary(companyId,departmentId,startTime,endTime);
    }

    /**
     *  通过 传递卡 查询 该传递卡下的详情数据
     * @param workDiaryId
     * @return
     */
    public List<WorkDiarySub> queryWorkDiarySubByWorkDiary(Integer workDiaryId) throws Exception {
        return workDiarySubMapper.queryWorkDiarySubByWorkDiary(workDiaryId);
    }

    /**
     * 新增单个 工作日志 详情
     * @param workDiarySub
     */
    public Integer addWorkDiarySub(WorkDiarySub workDiarySub,Date workTime,Integer userId) throws Exception{
        List<WorkDiarySub> list = workDiarySubMapper.isHave(workDiarySub.getStartTime(),workDiarySub.getEndTime(),userId,null,workTime);
        if (list.size() > 0) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"此时间段已有草稿存在");
        }

        //格式化时间
        workDiarySub.setStartTime(DateTimeHelper.dateFortimestamp(workDiarySub.getStartTime().getTime(),"yyyy-MM-dd HH:mm"));
        workDiarySub.setEndTime(DateTimeHelper.dateFortimestamp(workDiarySub.getEndTime().getTime(),"yyyy-MM-dd HH:mm"));
        workDiarySubMapper.addWorkDiarySub(workDiarySub);
        return workDiarySub.getId();
    }


    /**
     *  查询 当天 开始时间 到 结束时间 是否添加过
     * @param startTime
     * @param endTime
     * @return
     */
    public WorkDiarySub queryByDateAndWorkDiary(Integer wordId, Date startTime,Date endTime){
        return workDiarySubMapper.queryByDateAndWorkDiary(startTime,endTime,wordId);
    }


    /**
     * 更新单个 工作日志 详情
     * @param workDiarySub
     */
    public void batchUpdateWorkDiarySub(WorkDiarySub workDiarySub, Date workTime, WorkDiary workDiary,User user) throws Exception{
        List<WorkDiarySub> data = new ArrayList<>();
        List<WorkDiarySub> list = workDiarySubMapper.isHave(workDiarySub.getStartTime(),workDiarySub.getEndTime(),workDiary.getUserId(),workDiarySub.getId(),workTime);
        if (list.size() > 0) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"此时间段已有草稿存在");
        }
        if(!StatusConstant.WORKDIARY_STATUS_DRAFT.equals(workDiary.getStatus())){
            // 如果状态不是 草稿
            // 记录修改记录
            WorkDiaryStatusDetail statusDetail = createWorkDiaryStatusDetail(workDiary.getId(),"修改工作日志",new Date(),
                    null,user.getId());
            workDiaryStatusDetailMapper.addWorkDiaryStatusDetail(statusDetail);
        }
        data.add(workDiarySub);
        workDiarySubMapper.batchUpdateWorkDiarySub(data);
    }

    private WorkDiaryStatusDetail createWorkDiaryStatusDetail(Integer workDiaryId,String statusDescribe,Date date,String notes,Integer userId) {
        WorkDiaryStatusDetail detail = new WorkDiaryStatusDetail();
        detail.setUserId(userId);
        detail.setCreateTime(date);
        detail.setStatusDescribe(statusDescribe);
        detail.setWorkDiaryId(workDiaryId);
        detail.setNotes(notes);
        return detail;
    }

    /**
     * 根据ID 删除
     * @param id
     * @return
     */
    @Transactional
    public void delWorkDiarySub(Integer id,Integer workDiaryId) throws Exception{
        List<WorkDiarySub> workDiarySubs = workDiarySubMapper.queryWorkDiarySubByWorkDiary(workDiaryId);
        if(workDiarySubs.size() <= 1){
            workDiaryMapper.delWorkDiary(workDiaryId);
        }
        workDiarySubMapper.delWorkDiarySub(id);
    }

    /**
     * 根据id查看详情
     * @param id
     * @return
     */
    public WorkDiarySub info(Integer id) throws Exception{
        return workDiarySubMapper.info(id);
    }

    /**
     *  查询 工作日志记录 除 当前工作日志记录
     *   用户查询判断 当前更新的时间 是否和其他工作日志 有交集
     * @param workId 工作ID
     * @param subId 子类ID
     * @return
     */
    public List<WorkDiarySub> queryWorkDiarySubByWork(Integer workId,Integer subId){
        return workDiarySubMapper.queryWorkDiarySubByWork(workId,subId);
    }

    /**
     * 是否此时间段已存在
     * @param startTime
     * @param endTime
     * @param userId
     * @return
     */
    public List<WorkDiarySub> isHave(Long startTime, Long endTime,Integer userId,Integer id,Date workTime){
        return workDiarySubMapper.isHave(new Date(startTime),new Date(endTime),userId,id,workTime);
    }

}
