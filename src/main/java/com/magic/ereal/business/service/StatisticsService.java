package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.KDCStatistics;
import com.magic.ereal.business.entity.KStatistics;
import com.magic.ereal.business.entity.OneStatistics;
import com.magic.ereal.business.entity.User;
import com.magic.ereal.business.mapper.IKStatisticsMapper;
import com.magic.ereal.business.mapper.IStatisticsMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 三维 统计 业务层
 * Created by Eric Xie on 2017/5/18 0018.
 */
@Service
public class StatisticsService {

    @Resource
    private IStatisticsMapper statisticsMapper;
    @Resource
    private IKStatisticsMapper kStatisticsMapper;

    /**
     * APP 主页 上月 K临时、K常规、K内、K外 统计
     */
    public Map<String,KStatistics> statisticsHomeK(User user, Date date) throws ParseException {

        Long time = date.getTime();
        Date bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        Date startTime = DateTimeHelper.getMonthByDate(bDate,"first");
        Date endTime = DateTimeHelper.getMonthByDate(eDate,"last");

        Date lastStartTime = DateTimeHelper.getMonthByDate(bDate,"first",1);
        //获取这个时间的这月月末的日期 结束时间
        Date lastEndTime = DateTimeHelper.getMonthByDate(eDate,"last",1);


        // 获取公司的统计数据
        List<OneStatistics> statisticsList = kStatisticsMapper.homeStatistics(startTime, endTime, null, null, user.getCompanyId(), 2, 1, null);
        // 组装数据
        KStatistics companyData = new  KStatistics();
        KStatistics departmentData = new  KStatistics();
        if(null != statisticsList && statisticsList.size() > 0){
            for (OneStatistics statistics : statisticsList) {
                if(statistics.getCompanyId().equals(user.getCompanyId())){
                    getDate(companyData, statistics);
                }
                if(statistics.getDepartmentId().equals(user.getDepartmentId())){
                    getDate(departmentData, statistics);
                }
            }
        }


//        KStatistics kStatistics = statisticsMapper.statisticsHomeK(user.getCompanyId(), startTime, endTime);
//        if(null == kStatistics){
//            kStatistics = statisticsMapper.statisticsHomeK(user.getCompanyId(), lastStartTime, lastEndTime);
//        }
//        KStatistics kStatistics1 = statisticsMapper.statisticsHomeKForDepartment(user.getDepartmentId(), startTime, endTime);
//        if(null == kStatistics1){
//            kStatistics1 = statisticsMapper.statisticsHomeKForDepartment(user.getDepartmentId(), lastStartTime, lastEndTime);
//        }
        Map<String,KStatistics> data = new HashMap<>();
        data.put("departmentData",departmentData);
        data.put("companyData",companyData);
        return data;
    }

    private void getDate(KStatistics departmentData, OneStatistics statistics) {
        departmentData.setkGeneral(getDouble(departmentData.getkGeneral()) + getDouble(statistics.getKc()));
        departmentData.setkFinishRate(getDouble(departmentData.getkFinishRate()) + getDouble(statistics.getKSchedule()));
        departmentData.setkExterior(getDouble(departmentData.getkExterior())+ getDouble(statistics.getKw()));
        departmentData.setkInterior(getDouble(departmentData.getkInterior()) + getDouble(statistics.getKn()));
        departmentData.setkTemp(getDouble(departmentData.getkTemp()) + getDouble(statistics.getKl()));
    }

    private double getDouble(Double aDouble) {

        if(null == aDouble){
            return 0.0;
        }
        return aDouble;
    }


}
