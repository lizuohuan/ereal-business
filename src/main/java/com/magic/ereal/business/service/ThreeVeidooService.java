package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.mapper.IDepartmentMapper;
import com.magic.ereal.business.mapper.IThreeVeidooMapper;
import com.magic.ereal.business.mapper.IThreeVeidooScoreMapper;
import com.magic.ereal.business.mapper.IUserMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * 第三维 统计
 * Created by Eric Xie on 2017/5/22 0022.
 */
@Service
public class ThreeVeidooService {

    @Resource
    private IThreeVeidooMapper threeVeidooMapper;
    @Resource
    private IThreeVeidooScoreMapper threeVeidooScoreMapper;
    @Resource
    private IDepartmentMapper departmentMapper;
    @Resource
    private IUserMapper userMapper;



    /**
     * 添加第三维配置
     * @param threeVeidoo
     */
    public void insert (ThreeVeidoo threeVeidoo){
        threeVeidooMapper.addThreeVeidoo(threeVeidoo);
    }

    /**
     * 修改第三维配置
     * @param threeVeidoo
     */
    public void update (ThreeVeidoo threeVeidoo) {
        threeVeidooMapper.updateThreeVeidoo(threeVeidoo);
    }

    /**
     * 查询第三维配置
     * @return
     */
    public List<ThreeVeidoo> queryAllThreeVeidoo (){
        return threeVeidooMapper.queryAllThreeVeidoo();
    }

    /**
     * 查询排除一和二配置
     * @return
     */
    public List<ThreeVeidoo> queryExcludeOneOrTwo (){
        return threeVeidooMapper.queryExcludeOneOrTwo();
    }




    /**
     *  第三维 统计
     * @param time 时间
     * @param timeType 0：周   1： 月
     * @param type 0：个人   1： 部门
//     * @param userId 用户ID
//     * @param departmentId 部门
//     * @param  companyId 分公司ID  用于统计 全体员工的日均值 必传
     * @return 文化工程完成率
     */
    public PageList<ThreeVeidooTemp> threeListForWeb(Long time, Integer type, Integer timeType, User user ,PageArgs pageArgs ,Integer groupType) throws ParseException {

        // 获取 第三维配置
//        List<ThreeVeidoo> threeVeidoos = threeVeidooMapper.queryAllThreeVeidoo();
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate =Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
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
            //timeType 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        List<ThreeVeidooTemp> threeVeidooTemps = threeVeidooMapper.statisticsThreeVeidoo(startTime, endTime,user.getCompanyId(),
                pageArgs.getPageStart(), pageArgs.getPageSize());
        if(null != threeVeidooTemps){
            for (ThreeVeidooTemp threeVeidooTemp : threeVeidooTemps) {

                // 设置TS 如果为负数 设置为 0
                if(null != threeVeidooTemp.getTz() && threeVeidooTemp.getTz() < 0){
                    threeVeidooTemp.setTz(0.0);
                }

                //计算个人K文化
                threeVeidooTemp.setPersonKCulture((threeVeidooTemp.getHy() + threeVeidooTemp.getKg() + threeVeidooTemp.getTz()
                + threeVeidooTemp.getWx() + threeVeidooTemp.getYd() + threeVeidooTemp.getZb()) * 0.6 + threeVeidooTemp.getPersonKCulture() * 0.4);
            }
        }
        List<Object> count = threeVeidooMapper.countStatisticsThreeVeidoo(user.getCompanyId(),startTime, endTime);
        return  new PageList<ThreeVeidooTemp>(threeVeidooTemps,count.size());
    }


    /**
     *  第三维 统计
     * @param time 时间
     * @param timeType 0：周   1： 月
     * @param type 0：个人   1： 部门
//     * @param userId 用户ID
//     * @param departmentId 部门
//     * @param  companyId 分公司ID  用于统计 全体员工的日均值 必传
     * @return 文化工程完成率
     */
    public List<ThreeVeidooTemp> threeListForWebNoPage(Long time, Integer type, Integer timeType, User user ,Integer groupType) throws ParseException {

        // 获取 第三维配置
//        List<ThreeVeidoo> threeVeidoos = threeVeidooMapper.queryAllThreeVeidoo();
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate =Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
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
            //timeType 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
        }
        List<ThreeVeidooTemp> threeVeidooTemps = threeVeidooMapper.statisticsThreeVeidoo(startTime, endTime,user.getCompanyId(),
                null, null);
        if(null != threeVeidooTemps){
            for (ThreeVeidooTemp threeVeidooTemp : threeVeidooTemps) {
                // 设置TS 如果为负数 设置为 0
                if(null != threeVeidooTemp.getTz() && threeVeidooTemp.getTz() < 0){
                    threeVeidooTemp.setTz(0.0);
                }
                //计算个人K文化
                threeVeidooTemp.setPersonKCulture((threeVeidooTemp.getHy() + threeVeidooTemp.getKg() + threeVeidooTemp.getTz()
                        + threeVeidooTemp.getWx() + threeVeidooTemp.getYd() + threeVeidooTemp.getZb()) * 0.6 + threeVeidooTemp.getPersonKCulture() * 0.4);
            }
        }
        return  threeVeidooTemps;
    }

    /**
     * 计算 个人K文化
     * @param threeVeidooTemp
     */
    private void teamKCultureFinishRate(ThreeVeidooTemp threeVeidooTemp){


    }

























}
