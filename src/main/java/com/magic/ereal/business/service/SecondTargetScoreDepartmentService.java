package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.entity.SecondTargetScoreDepartment;
import com.magic.ereal.business.mapper.ISecondTargetScoreDepartmentMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/6 0006.
 */
@Service
public class SecondTargetScoreDepartmentService {

    @Resource
    private ISecondTargetScoreDepartmentMapper secondTargetScoreDepartmentMapper;



    public void updateScoreDepartment (SecondTargetScoreDepartment scoreDepartment){
        secondTargetScoreDepartmentMapper.updateSecondTargetScoreDepartment(scoreDepartment);
    }

    public PageList<SecondTargetScoreDepartment> queryByItems(Date time_, Integer timeType, Integer departmentId,
                                                              PageArgs pageArgs)
    throws Exception{
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

        if(time_ == null){
            startTime = null;
            endTime = null;
        }

        List<SecondTargetScoreDepartment> scoreDepartments = secondTargetScoreDepartmentMapper.queryScoreDepartmentByItems(departmentId, startTime, endTime, pageArgs.getPage(),
                pageArgs.getPageSize());

        Integer count = secondTargetScoreDepartmentMapper.countScoreDepartmentByItems(departmentId, startTime, endTime);
        return new PageList<>(scoreDepartments,count);
    }


}
