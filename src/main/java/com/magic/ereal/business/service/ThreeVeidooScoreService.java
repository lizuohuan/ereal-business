package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.entity.ThreeVeidooScore;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IThreeVeidooScoreMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.StatusConstant;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 第三维 评分
 */
@Service
public class ThreeVeidooScoreService {

    @Resource
    private IThreeVeidooScoreMapper threeVeidooScoreMapper;

    /**
     * 新增
     *  每一个用户 每一个月 只能评分一次
     * @param type type 0:周打分  1:月打分
     * @return
     */
    public void insert (ThreeVeidooScore score,Integer type) throws ParseException {

        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")), "yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        if (null != score.getMonthTime()) {
            bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(score.getMonthTime(),"yyyy-MM-dd")), "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        }
        if(type == 0){
            score.setStartTime(DateTimeHelper.getWeekByDate(bDate,1));
            score.setEndTime(DateTimeHelper.getWeekByDate(eDate,7));
        }
        else if(type == 1){
            score.setStartTime(DateTimeHelper.getMonthByDate(bDate,"first"));
            score.setEndTime(DateTimeHelper.getMonthByDate(eDate,"last"));
        }
        //验证重复

        ThreeVeidooScore score1 = threeVeidooScoreMapper.queryThreeVeidooScoreByUserWeb(score.getUserId(), score.getStartTime(), score.getEndTime(),
                score.getThreeVerdooId());
        if(null != score1){
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"已经存在打分，请勿重复添加");
        }

        threeVeidooScoreMapper.addThreeVeidooScore(score);
    }

    /**
     * 修改
     * @param score
     * @return
     */
    public void update (ThreeVeidooScore score) {
        threeVeidooScoreMapper.updateThreeVeidooScore(score);
    }

    /**
     * 分页查询
     * @param pageArgs
     * @param departmentId
     * @param date
     * @param type  0:周   1：月
     * @return
     */
    public PageList<ThreeVeidooScore> list (PageArgs pageArgs, Integer departmentId, Integer userId, Date date,Integer type) throws ParseException {
        PageList<ThreeVeidooScore> pageList = new PageList<>();

        //开始时间
        Date startTime;
        //结束时间
        Date endTime;
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")), "yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        if (null != date) {
            bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(date,"yyyy-MM-dd")), "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        }
        if(type == 0){
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7);
        }else {
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        List<ThreeVeidooScore> total = threeVeidooScoreMapper.queryThreeVeidooScore(departmentId, userId, startTime,endTime, pageArgs.getPageStart(), pageArgs.getPageSize());
        Integer count = threeVeidooScoreMapper.countThreeVeidooScore(departmentId, userId, startTime,endTime);
        pageList.setList(total);
        pageList.setTotalSize(count);
        return pageList;
    }
}
