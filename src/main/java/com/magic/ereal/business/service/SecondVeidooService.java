package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.KDCStatistics;
import com.magic.ereal.business.entity.KPersonageStatistics;
import com.magic.ereal.business.entity.SecondTarget;
import com.magic.ereal.business.entity.SecondVeidoo;
import com.magic.ereal.business.mapper.IKStatisticsMapper;
import com.magic.ereal.business.mapper.ISecondTargetMapper;
import com.magic.ereal.business.mapper.ISecondVeidooMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 二维统计 配置 以及 二维统计
 * Created by Eric Xie on 2017/5/22 0022.
 */
@Service
public class SecondVeidooService {


    @Resource
    private ISecondVeidooMapper secondVeidooMapper;
    @Resource
    private IKStatisticsMapper kStatisticsMapper;
    @Resource
    private ISecondTargetMapper secondTargetMapper;


    /**
     * 部门(个人)二维统计
     * @param departmentId 部门ID
     * @param date 月度时间  SQL已做日期格式化
     * @param type 查询类型 1:部门  2 ：用户
     * @param timeType 查询时间 类型  1：周  2：月
     * @param userId 用户id
     * @param departmentType 部门类型  1：项目部  2：职能部门
     * @return 统计数据
     */
    public Double statisticsSecondVeidoo(Integer departmentId, Date date,Integer departmentType,
                                         Integer timeType ,Long time ,Integer type ,Integer userId) throws ParseException {
        SecondVeidoo secondVeidoo = secondVeidooMapper.querySecondVeidooByType(departmentType);
        Double data = null;
        if(departmentType == 1){
            // 如果部门是项目部
            if(secondVeidoo.getMethod() == 2){
                // 用第二套公式计算  （（K内+K外）/（1.7*0.7））/目标结项数*100%
                // 计算K内   K外
                //开始时间
                Date startTime = new Date() ;
                //结束时间
                Date endTime = new Date();
                Date bDate = new Date();
                Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");

                if (null != time){
                    bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
                    eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
                }
                if (timeType == 1) {
                    //获取这个时间的这周星期一的日期 开始时间
                    startTime = DateTimeHelper.getWeekByDate(bDate,1);
                    //获取这个时间的这周星期日的日期 结束时间
                    endTime = DateTimeHelper.getWeekByDate(eDate,7);
                }
                if (timeType == 2) {
                    //type 月初 first  月末 last
                    //获取这个时间的这月月初的日期 开始时间
                    startTime = DateTimeHelper.getMonthByDate(bDate,"first");
                    //获取这个时间的这月月末的日期 结束时间
                    endTime = DateTimeHelper.getMonthByDate(eDate,"last");
                }
                List<SecondTarget> secondTargets = secondTargetMapper.querySecondTarget(departmentId, startTime,endTime, 0, 1,null, null);


                if (type == 1) {
                    KPersonageStatistics k = kStatisticsMapper.getByTimePersonage(startTime,endTime,userId,timeType);
                    if(null == secondTargets || secondTargets.size() == 0){
                        data = (k.getKn() + k.getKw()) / (1.7 * 0.7) / 1;
                    }else {
                        data = (k.getKn() + k.getKw()) / (1.7 * 0.7) / (secondTargets.get(0).getTargetNum() == null ? 1 : secondTargets.get(0).getTargetNum());
                    }
                } else {
                    KDCStatistics k = kStatisticsMapper.getByTimeDeCom(startTime, endTime ,departmentId ,null,1,3);
                    if(null == secondTargets || secondTargets.size() == 0){
                        data = (k.getKn() + k.getKw()) / (1.7 * 0.7) / 1;
                    }else {
                        data = (k.getKn() + k.getKw()) / (1.7 * 0.7) / (secondTargets.get(0).getTargetNum() == null ? 1 : secondTargets.get(0).getTargetNum());
                    }

                }

            }
        }
        if(null == data){
            data = secondVeidooMapper.statisticsSecondVeidoo(departmentId,date,departmentType,secondVeidoo.getMethod());
        }
        return data;

    }




    public void updateSecondVeidoo(SecondVeidoo secondVeidoo){
        secondVeidooMapper.updateSecondVeidoo(secondVeidoo);
    }


    public SecondVeidoo querySecondVeidoo(Integer type){
        return  secondVeidooMapper.querySecondVeidooByType(type);
    }







}
