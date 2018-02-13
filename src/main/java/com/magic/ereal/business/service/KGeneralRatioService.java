package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.KGeneralRatio;
import com.magic.ereal.business.entity.KGeneralUser;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IKGeneralRatioMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.StatusConstant;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * K常规 比例分配
 * Created by Eric Xie on 2017/6/30 0030.
 */
@Service
public class KGeneralRatioService {



    @Resource
    private IKGeneralRatioMapper kGeneralRatioMapper;


    /**
     * 根据条件获取 K常规 分配数据
     * @param timeType
     * @param time
     * @param userId
     * @param jobTypeId
     * @param departmentId
     * @param pageNO
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageList<KGeneralRatio> queryKGeneralRatioByItems(Integer timeType,Long time,Integer userId,
                                                              Integer jobTypeId,Integer departmentId,
                                                             Integer pageNO,Integer pageSize) throws Exception{
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date() ;
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        if (null != time){
            bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        }
        if (timeType == 0) {
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }
        if (timeType == 1) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        List<KGeneralRatio> kGeneralRatios = kGeneralRatioMapper.queryKGeneralRatioByItems(startTime, endTime, userId, jobTypeId, departmentId,
                (pageNO - 1) * pageSize, pageSize);
        Integer count = kGeneralRatioMapper.countKGeneralRatioByItems(startTime, endTime, departmentId, userId, jobTypeId);
        return new PageList<>(kGeneralRatios,count);
    }


    public KGeneralRatio queryKGeneralRatio( Date startTime,  Date endTime,
                                             Integer userId, Integer jobTypeId){
        return kGeneralRatioMapper.queryKGeneralRatio(startTime,endTime,userId,jobTypeId);
    }


    /**
     * 新增分配
     * @param kGeneralRatio
     * @param timeType
     * @param time
     * @throws Exception
     */
    public void addKGeneralRatio(KGeneralRatio kGeneralRatio,Integer timeType,Long time) throws Exception{
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date() ;
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        if (null != time){
            bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        }
        if (timeType == 0) {
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }
        if (timeType == 1) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        KGeneralRatio temp = kGeneralRatioMapper.queryKGeneralRatio(startTime, endTime, kGeneralRatio.getUserId(), kGeneralRatio.getJobTypeId());
        if(null != temp){
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"已经分配过");
        }
        kGeneralRatio.setStartTime(startTime);
        kGeneralRatio.setEndTime(endTime);
        kGeneralRatioMapper.addKGeneralRatio(kGeneralRatio);
    }

    public void updateKGeneralRatio(KGeneralRatio kGeneralRatio) throws Exception{
        kGeneralRatioMapper.updateKGeneralRatio(kGeneralRatio);
    }


    public List<KGeneralUser> queryKGeneralUserByDepartment(Integer departmentId,Integer jobTypeId,
                                                            Integer timeType,Long time) throws Exception{
        Map<String, Date> date = DateTimeHelper.getDateByTimeType(timeType, time);
        return kGeneralRatioMapper.queryKGeneralUserByDepartment(departmentId,date.get("startTime"),date.get("endTime"),
                jobTypeId);
    }



}
