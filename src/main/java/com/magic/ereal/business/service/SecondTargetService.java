package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.mapper.ISecondTargetMapper;
import com.magic.ereal.business.mapper.ISecondTargetScoreDepartmentMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/5/22 0022.
 */
@Service
public class SecondTargetService {

    @Resource
    private ISecondTargetMapper secondTargetMapper;
    @Resource
    private ISecondTargetScoreDepartmentMapper secondTargetScoreDepartmentMapper;



    public PageList<SecondTarget> querySecondTarget(PageArgs pageArgs, Integer departmentId, Date date,Integer timeType,
                                                    Integer isScore, Integer isProjectDepartment)
    throws Exception{
        PageList<SecondTarget> pageList = new PageList<>();

        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        Long time = date == null ? null : date.getTime();
        if (null != time){
            bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        }
        //处理时间
        if(timeType == 0){
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }
        else if(timeType == 1){
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        startTime = date == null ? null : startTime;
        endTime = date == null ? null : endTime;
        //总条数
        List<SecondTarget> totalSecondTargets = secondTargetMapper.querySecondTarget(departmentId, startTime,endTime, pageArgs.getPageStart(), pageArgs.getPageSize(),
                 isScore, isProjectDepartment);
        //总条数
        Integer count = secondTargetMapper.countQuerySecondTarget(departmentId,startTime,endTime,
                 isScore, isProjectDepartment);
        pageList.setList(totalSecondTargets);
        pageList.setTotalSize(count);
        return pageList;
    }


    public void addSecondTarget(SecondTarget secondTarget,Integer timeType) throws Exception{
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        Long time = secondTarget.getTargetTime() == null ? null : secondTarget.getTargetTime().getTime();
        if (null != time){
            bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        }
        //处理时间
        if(timeType == 0){
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }
        else if(timeType == 1){
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        // 查看此条数据是否存在 如存在 则更新即可， 不存在新增数据
//        SecondTarget temp = secondTargetMapper.querySecondTargetByItem(secondTarget.getDepartmentId(), startTime, endTime);
        SecondTarget temp = null;
        if(null == temp){
            secondTarget.setStartTime(startTime);
            secondTarget.setEndTime(endTime);
            secondTargetMapper.addSecondTarget(secondTarget);
        }else{
            secondTarget.setId(temp.getId());
            secondTargetMapper.updateSecondTarget(secondTarget);
        }

    }


    public void addSecondTarget(Date time_,Integer timeType,Double dutyGrade, Double managerGrade,
                                Integer departmentId,Integer currentUserId) throws ParseException {
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        Long time = time_ == null ? null : time_.getTime();
        if (null != time){
            bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        }
        //处理时间
        if(timeType == 0){
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }
        else if(timeType == 1){
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        // 查看此条数据是否存在 如存在 则更新即可， 不存在新增数据
        SecondTargetScoreDepartment secondTarget = null;
        if(null != dutyGrade || null != managerGrade){
            secondTarget =  secondTargetScoreDepartmentMapper.queryScoreDepartment(departmentId, startTime, endTime);
        }
        if(null == secondTarget){
            SecondTargetScoreDepartment temp = new SecondTargetScoreDepartment();
            temp.setStartTime(startTime);
            temp.setEndTime(endTime);
            temp.setDutyGrade(dutyGrade);
            temp.setManagerGrade(managerGrade);
            temp.setDepartmentId(departmentId);
            if(null != dutyGrade){
                temp.setDutyGradeTime(new Date());
            }
            if(null != managerGrade){
                temp.setManagerGradeTime(new Date());
            }
            secondTargetScoreDepartmentMapper.addSecondTargetScoreDepartment(temp);
        }else{
            secondTarget.setDutyGrade(dutyGrade);
            secondTarget.setManagerGrade(managerGrade);
            if(null != dutyGrade){
                secondTarget.setDutyGradeTime(new Date());
            }
            if(null != managerGrade){
                secondTarget.setManagerGradeTime(new Date());
            }
            secondTargetScoreDepartmentMapper.updateSecondTargetScoreDepartment(secondTarget);
        }
    }

    public void updateSecondTarget(SecondTarget secondTarget){
        if (null != secondTarget.getIsApproved() && secondTarget.getIsApproved() == 1){
            secondTarget.setApprovedTime(new Date());
        }
        if(null != secondTarget.getScore() ){
            secondTarget.setScoreTime(new Date());
        }
        secondTargetMapper.updateSecondTarget(secondTarget);
    }


    public void delSecondTarget(Integer id){
        secondTargetMapper.delSecondTarget(id);
    }




}
