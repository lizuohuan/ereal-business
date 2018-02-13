package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.entity.ThreeVeidooKg;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IThreeVeidooKgMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.StatusConstant;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 第三维  职能部门个人KG打分记录 / 个人K团队得分记录 Service
 * Created by Eric Xie on 2017/6/2 0002.
 */
@Service
public class ThreeVeidooKgService {

    @Resource
    private IThreeVeidooKgMapper threeVeidooKgMapper;


    /**
     * 获取 打分数据
     * @param pageArgs
     * @param departmentId
     * @param type
     * @return
     */
    public PageList<ThreeVeidooKg> queryThreeVeidooKgByItems(PageArgs pageArgs, Integer departmentId, Integer type,
                                                             Integer dateType,Date time,Integer companyId) throws Exception{
        Date startTime = new Date(), endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(time == null ? new Date() : time,"yyyy-MM-dd")), "yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        if(dateType == 0){
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }
        else if(dateType == 1){
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        List<ThreeVeidooKg> threeVeidooKgs = threeVeidooKgMapper.queryThreeVeidooKgByItem(departmentId, pageArgs.getPage(), pageArgs.getPageSize(),
                type, startTime, endTime,companyId);
        Integer count = threeVeidooKgMapper.countThreeVeidooKgByItem(departmentId,type,startTime,endTime,companyId);
        return new PageList<>(threeVeidooKgs,count);
    }


    /**
     *
     */
    public void addThreeVeidooKg(ThreeVeidooKg threeVeidooKg,Long date) throws Exception{
        Date dateTime = null == date  ? new Date() : new Date(date);
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(dateTime,"yyyy-MM-dd")), "yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        if(threeVeidooKg.getDateType() == 0){
            threeVeidooKg.setStartTime(DateTimeHelper.getWeekByDate(bDate,1));
            threeVeidooKg.setEndTime(DateTimeHelper.getWeekByDate(eDate,7));
        }
        else if(threeVeidooKg.getDateType() == 1){
            threeVeidooKg.setStartTime(DateTimeHelper.getMonthByDate(bDate,"first"));
            threeVeidooKg.setEndTime(DateTimeHelper.getMonthByDate(eDate,"last"));
        }
        // 验证用户在该时间段是否添加过
        ThreeVeidooKg temp = threeVeidooKgMapper.queryByItems(threeVeidooKg.getUserId(), threeVeidooKg.getStartTime(),
                threeVeidooKg.getEndTime(), threeVeidooKg.getType());
        if(null != temp){
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"已经添加过.");
        }
        threeVeidooKgMapper.addThreeVeidooKg(threeVeidooKg);
    }

    public void addThreeVeidooKg(List<ThreeVeidooKg> threeVeidooKgs,Long date,Integer dateType) throws Exception{

        Date dateTime = null == date  ? new Date() : new Date(date);
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(dateTime,"yyyy-MM-dd")), "yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        Date startTime = null;
        Date endTime = null;
        if(dateType == 0){
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }
        else if(dateType == 1){
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        if (null != threeVeidooKgs && threeVeidooKgs.size() > 0) {
            for (ThreeVeidooKg threeVeidooKg : threeVeidooKgs) {
                threeVeidooKg.setStartTime(startTime);
                threeVeidooKg.setEndTime(endTime);
            }
            // 验证是否添加过
            Integer count = threeVeidooKgMapper.batchQueryByItems(threeVeidooKgs);
            if(count > 0){
                throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"已经添加过.");
            }
            threeVeidooKgMapper.batchAddThreeVeidooKg(threeVeidooKgs);
        }
    }


    public void updateThreeVeidooKg(ThreeVeidooKg threeVeidooKg){
        threeVeidooKgMapper.updateThreeVeidooKg(threeVeidooKg);
    }









}
