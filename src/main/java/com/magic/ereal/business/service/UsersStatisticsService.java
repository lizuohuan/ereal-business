package com.magic.ereal.business.service;

import com.alibaba.fastjson.JSONArray;
import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mail.Config;
import com.magic.ereal.business.mapper.*;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.StatusConstant;
import com.magic.ereal.business.util.Timestamp;
import com.magic.ereal.business.util.UtilComparator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.*;

/**
 * 用户的统计数据
 * Created by Eric Xie on 2017/6/2 0002.
 */
@Service
public class UsersStatisticsService {

    @Resource
    private IUsersStatisticsMapper usersStatisticsMapper;

    @Resource
    private IUsersStatisticsThreeMapper usersStatisticsThreeMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private IThreeVeidooMapper threeVeidooMapper;
    @Resource
    private IDepartmentMapper departmentMapper;

    /**
     * 获取三维统计
     * @param userId 用户id
     * @param departmentId 部门id
     * @param time 时间
     * @param type 类型 1:员工  2:部门 3：公司
     */
    public  Map<String,Object> getStatistics(Integer userId ,Integer departmentId ,Integer companyId,
                              Long time ,Integer type ,Integer timeType) throws ParseException {

        Map<String,Object> map = new HashMap<>();

        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        //上次开始时间
        Date lastStartTime = new Date() ;
        //上次结束时间
        Date lastEndTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");

        if (null != time){
            bDate = Timestamp.parseDate(String.valueOf(time / 1000) , "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate,"yyyy-MM-dd HH:mm:ss") + 24 * 3600 -1),"yyyy-MM-dd HH:mm:ss");
        }
        if (timeType == 1) {
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7); //获取这个时间的这周星期一的日期 开始时间
            lastStartTime = DateTimeHelper.getWeekByDate(bDate,1,1);
            //获取这个时间的这周星期日的日期 结束时间
            lastEndTime = DateTimeHelper.getWeekByDate(eDate,7,1);
        }
        if (timeType == 2) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
            lastStartTime = DateTimeHelper.getMonthByDate(bDate,"first",1);
            //获取这个时间的这月月末的日期 结束时间
            lastEndTime = DateTimeHelper.getMonthByDate(eDate,"last",1);
        }
        if(type == 1){
            lastStartTime = DateTimeHelper.getMonthByDate(bDate,"first",1);
            //获取这个时间的这月月末的日期 结束时间
            lastEndTime = DateTimeHelper.getMonthByDate(eDate,"last",1);
            // 如果是个人
            VeidooStatistics data = usersStatisticsMapper.statisticsVeidooByUserForAPI(lastStartTime,lastEndTime,startTime, endTime, userId);
            data = null == data  ? new VeidooStatistics() : data;



            if(timeType == 1){
                //获取这个时间的这月月初的日期 开始时间
                startTime = DateTimeHelper.getMonthByDate(bDate,"first");
                //获取这个时间的这月月末的日期 结束时间
                endTime = DateTimeHelper.getMonthByDate(eDate,"last");
                lastStartTime = DateTimeHelper.getMonthByDate(bDate,"first",1);
                //获取这个时间的这月月末的日期 结束时间
                lastEndTime = DateTimeHelper.getMonthByDate(eDate,"last",1);

            }

            List<ThreeVeidooTemp> threeVeidooTemps = threeVeidooMapper.statisticsThreeVeidooForUser(startTime, endTime,
                                            lastStartTime,lastEndTime, userId,null,companyId,null);

            List<ThreeVeidooTemp> lastThreeVeidooTemps = threeVeidooMapper.statisticsThreeVeidooForUser(lastStartTime, lastEndTime,
                                            lastStartTime,lastEndTime, userId,null,companyId,null);

            if( null == threeVeidooTemps){
                return  map;
            }
            ThreeVeidooTemp threeVeidooTemp = threeVeidooTemps.get(0);

            data.setThreeList(threeVeidooTemp.getUsersStatisticsThrees());
            data.setLastThreeList(threeVeidooTemp.getLastUsersStatisticsThrees());

            if(null != data.getThreeList() && data.getThreeList().size() > 1){
                for (UsersStatisticsThree statisticsThree : data.getThreeList()) {
                    if(statisticsThree.getThreeVeidooId() == 1){
                        statisticsThree.setThreeVeidooScore(threeVeidooTemp.getKg());
                        continue;
                    }
                    if(statisticsThree.getThreeVeidooId() == 2){
                        if(null == threeVeidooTemp.getTz() || threeVeidooTemp.getTz() < 0){
                            statisticsThree.setThreeVeidooScore(0.0);
                            threeVeidooTemp.setTz(0.0);
                        }else{
                            statisticsThree.setThreeVeidooScore( threeVeidooTemp.getTz() > 20 ? 20 : threeVeidooTemp.getTz());
                            threeVeidooTemp.setTz(threeVeidooTemp.getTz() > 20 ? 20 : threeVeidooTemp.getTz());
                        }
                        continue;
                    }
                    if(statisticsThree.getThreeVeidooId() == 6){
                        statisticsThree.setThreeVeidooScore(threeVeidooTemp.getYd());
                    }
                }
            }

            if(null != lastThreeVeidooTemps){
                ThreeVeidooTemp lastThreeVeidooTemp = lastThreeVeidooTemps.get(0);
                if(null != data.getLastThreeList() && data.getLastThreeList().size() > 1){
                    for (UsersStatisticsThree statisticsThree : data.getLastThreeList()) {
                        if(statisticsThree.getThreeVeidooId() == 1){
                            statisticsThree.setThreeVeidooScore(lastThreeVeidooTemp.getKg());
                            continue;
                        }
                        if(statisticsThree.getThreeVeidooId() == 2){

                            if(null == lastThreeVeidooTemp.getTz() || threeVeidooTemp.getTz() < 0){
                                statisticsThree.setThreeVeidooScore(0.0);
                                lastThreeVeidooTemp.setTz(0.0);
                            }else{
                                statisticsThree.setThreeVeidooScore( lastThreeVeidooTemp.getTz() > 20 ? 20 : lastThreeVeidooTemp.getTz());
                                lastThreeVeidooTemp.setTz(threeVeidooTemp.getTz() > 20 ? 20 : threeVeidooTemp.getTz());
                            }

                            continue;
                        }
                        if(statisticsThree.getThreeVeidooId() == 6){
                            statisticsThree.setThreeVeidooScore(lastThreeVeidooTemp.getYd());
                        }
                    }
                }
            }
            if(timeType == 1){
                // 周统计时 设置个人K文化
                data.setWwcl(threeVeidooTemp.getHy() + threeVeidooTemp.getZb() + threeVeidooTemp.getYd()
                        + threeVeidooTemp.getWx() + threeVeidooTemp.getTz() + threeVeidooTemp.getKg());
            }

            if(timeType == 2) {
                // 如果是月统计
                // 需要统计排名
                List<VeidooStatistics> veidooStatisticses1 = usersStatisticsMapper.statisticsVeidooRankingForAPIByCompanyId(startTime, endTime, companyId);
                List<VeidooDepartment> veidooDepartments = usersStatisticsMapper.statisticsVeidooForAPIRankingForAPI(startTime, endTime, companyId);
                if (null != veidooDepartments && veidooDepartments.size() > 0) {
                    // 设置三维绩效得分和排名

                    // // 团队文化工程完成率 = 团队得分/全公司团队平均分
                    // 全公司团队平均分= 全公司团队得分总和/团队个数
                    double total = 0.0;
                    for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
                        total += veidooStatistics.getWwcl();
                    }
                    double v = total / veidooStatisticses1.size();

                    // 计算 三维完成率
                    for (VeidooDepartment veidooDepartment : veidooDepartments) {
                        for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
                            if(veidooStatistics.getDepartmentId().equals(veidooDepartment.getDepartmentId())){
                                // 计算三维
                                veidooDepartment.setTotalScore(
                                        veidooDepartment.getTotalScore() + veidooStatistics.getWwcl() / v * 0.2);
                                break;
                            }
                        }
                    }

//                    for (int i = 0; i < veidooDepartments.size(); i++) {
//                        for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
//                            // 计算 三维得分
//                            veidooStatistics.setWwcl(null == veidooStatistics.getWwcl() ? 0 : veidooStatistics.getWwcl());
//                            if(0 != veidooStatistics.getWwcl()){
//                                if(veidooStatistics.getDepartmentId().equals(veidooDepartments.get(i).getDepartmentId())){
//                                    veidooDepartments.get(i).setTotalScore(veidooDepartments.get(i).getTotalScore() + (veidooStatistics.getWwcl()
//                                            / (veidooStatistics.getWwcl() / veidooStatistics.getPersons())) * 0.2);
//                                    break;
//                                }
//                            }
//                        }
//                    }
                    //排序
                    Collections.sort(veidooDepartments);
                    User user = userMapper.queryBaseInfo(userId);
                    // 设置排名
                    for (int i = 0; i < veidooDepartments.size(); i++) {
                        if(veidooDepartments.get(i).getDepartmentId().equals(user.getDepartmentId())){
                            data.setTotalScore(veidooDepartments.get(i).getTotalScore());
                            if(data.getTotalScore() == 0){
                                data.setRanking( 0);
                            }else{
                                data.setRanking( i + 1);
                            }
                            break;
                        }
                    }
                }

                // K可比排名
                List<User> users = usersStatisticsMapper.statisticsVeidooKKBRanking(startTime, endTime, companyId);
                if(null != users && users.size() > 0){
                    for (int i = 0; i < users.size(); i++) {
                        if(users.get(i).getId().equals(userId)){
                            if(users.get(i).getKkb() == 0){
                                data.setKkbRanking(0);
                            }else{
                                data.setKkbRanking( i + 1);
                            }
                            break;
                        }
                    }
                }
                // 公司文化考核得分
                data.setCompanyWwcl(threeVeidooTemp.getHy() + threeVeidooTemp.getZb() + threeVeidooTemp.getYd()
                        + threeVeidooTemp.getWx() + threeVeidooTemp.getTz() + threeVeidooTemp.getKg());
                // 部门文化考核得分
                data.setDepartmentWwcl(threeVeidooTemp.getPersonKCulture());
                data.setWwcl(data.getCompanyWwcl() * 0.6 + data.getDepartmentWwcl() * 0.4);
            }
            map.put("data",data);
        }
        else if(type == 3 ){
            // 如果是公司统计
            map.put("data",usersStatisticsMapper.statisticsVeidooForAPI(startTime, endTime, companyId, null));
            List<VeidooDepartment> veidooDepartments = usersStatisticsMapper.statisticsVeidooForAPIRankingForAPI(startTime, endTime, companyId);
            if(timeType == 2){
                // 如果是月 团队K文化得分
                List<VeidooStatistics> veidooStatisticses1 = usersStatisticsMapper.statisticsVeidooRankingForAPIByCompanyId(startTime, endTime, companyId);
                // // 团队文化工程完成率 = 团队得分/全公司团队平均分
                // 全公司团队平均分= 全公司团队得分总和/团队个数
                double total = 0.0;
                for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
                    total += veidooStatistics.getWwcl();
                }
                double v = total / veidooStatisticses1.size();

                // 计算 三维完成率
                for (VeidooDepartment veidooDepartment : veidooDepartments) {
                    for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
                        if(veidooStatistics.getDepartmentId().equals(veidooDepartment.getDepartmentId())){
                            // 计算三维
                            veidooDepartment.setTotalScore(
                                    veidooDepartment.getTotalScore() + veidooStatistics.getWwcl() / v * 0.2);
                            break;
                        }
                    }
                }
                //排序
                Collections.sort(veidooDepartments);
            }
            map.put("departmentList", veidooDepartments);
        }
        else if(type == 2){
            // 统计 部门
            VeidooStatistics data = usersStatisticsMapper.statisticsVeidooByDepartmentForAPI(startTime, endTime, departmentId);
            List<ThreeVeidooTemp> threeVeidooTemps = threeVeidooMapper.statisticsThreeVeidooForUser(startTime, endTime,
                    lastStartTime,lastEndTime, null,departmentId,companyId,null);

            data = null == data  ? new VeidooStatistics() : data;
            // 计算团队K文化得分  公式：团队K文化=Σ团队个人K文化/团队人数
            double totalKw = 0.0;
            for (ThreeVeidooTemp  threeVeidooTemp : threeVeidooTemps) {
                totalKw += (threeVeidooTemp.getHy() + threeVeidooTemp.getZb() + threeVeidooTemp.getYd()
                        + threeVeidooTemp.getWx() + threeVeidooTemp.getTz() + threeVeidooTemp.getKg()) * 0.6
                        + threeVeidooTemp.getPersonKCulture() * 0.4;
            }
            data.setWwcl(totalKw / threeVeidooTemps.size());
            if(timeType == 2){
                // 按公司 统计 排名 以及 团队K文化
                List<VeidooStatistics> veidooStatisticses1 = usersStatisticsMapper.statisticsVeidooRankingForAPIByCompanyId(startTime, endTime, companyId);
                // 如果是月统计
                // 需要统计排名
                List<VeidooDepartment> veidooDepartments = usersStatisticsMapper.statisticsVeidooForAPIRankingForAPI(startTime, endTime, companyId);
                if(null != veidooDepartments && veidooDepartments.size() > 0){
                    // 设置三维绩效得分和排名
                    // // 团队文化工程完成率 = 团队得分/全公司团队平均分
                    // 全公司团队平均分= 全公司团队得分总和/团队个数
                    double total = 0.0;
                    for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
                        total += veidooStatistics.getWwcl();
                    }
                    double v = total / veidooStatisticses1.size();

                    // 计算 三维完成率
                    for (VeidooDepartment veidooDepartment : veidooDepartments) {
                        for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
                            if(veidooStatistics.getDepartmentId().equals(veidooDepartment.getDepartmentId())){
                                // 计算三维
                                veidooDepartment.setTotalScore(
                                        veidooDepartment.getTotalScore() + veidooStatistics.getWwcl() / v * 0.2);
                                break;
                            }
                        }
                    }
//
//                    for (int i = 0; i < veidooDepartments.size(); i++) {
//                        for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
//                            // 计算 三维得分
//                            if(veidooStatistics.getDepartmentId().equals(veidooDepartments.get(i).getDepartmentId())){
//                                if(0 != veidooStatistics.getWwcl()){
//                                    veidooDepartments.get(i).setTotalScore(veidooDepartments.get(i).getTotalScore() + (veidooStatistics.getWwcl()
//                                            / (veidooStatistics.getWwcl() / veidooStatistics.getPersons())) * 0.2);
//                                }
//                                break;
//                            }
//                        }
//                    }
                    //排序
                    Collections.sort(veidooDepartments);
                    // 设置排名
                    for (int i = 0; i < veidooDepartments.size(); i++) {
                        if(veidooDepartments.get(i).getDepartmentId().equals(data.getDepartmentId())){
                            data.setTotalScore(veidooDepartments.get(i).getTotalScore());
                            if(data.getTotalScore() == 0){
                                data.setRanking(0);
                            }else{
                                data.setRanking( i + 1);
                            }
                            break;
                        }
                    }
                }
                // 统计设置 1、2、3 维 排名数据
                List<VeidooStatistics> veidooStatisticses = usersStatisticsMapper.statisticsVeidooRankingForAPI(startTime, endTime, departmentId);
                if(null != veidooStatisticses && veidooStatisticses.size() > 0){
                    // 设置第一维排名
                    // 排序
                    Collections.sort(veidooStatisticses, new Comparator<VeidooStatistics>() {
                        @Override
                        public int compare(VeidooStatistics o1, VeidooStatistics o2) {
                            return o2.getKwcl().compareTo(o1.getKwcl());
                        }
                    });
                    for (int i = 0; i < veidooStatisticses.size(); i++) {
                        if(veidooStatisticses.get(i).getDepartmentId().equals(departmentId)){
                            if(veidooStatisticses.get(i).getKwcl() == 0){
                                data.setOneRanking(0);
                            }
                            else{
                                data.setOneRanking(i + 1);
                            }

                            break;
                        }
                    }
                    // 设置第二维排名
                    // 排序
                    Collections.sort(veidooStatisticses, new Comparator<VeidooStatistics>() {
                        @Override
                        public int compare(VeidooStatistics o1, VeidooStatistics o2) {
                            return o2.getRwcl().compareTo(o1.getRwcl());
                        }
                    });
                    for (int i = 0; i < veidooStatisticses.size(); i++) {
                        if(veidooStatisticses.get(i).getDepartmentId().equals(departmentId)){
                            if(veidooStatisticses.get(i).getRwcl() == 0){
                                data.setTwoRanking(0);
                            }
                            else{
                                data.setTwoRanking(i + 1);
                            }
                            break;
                        }
                    }
                    // 设置第三维排名
                    // 排序
                    // 第三维 需要单独查询排名
                    for (int i = 0; i < veidooStatisticses1.size(); i++) {
                        if(veidooStatisticses1.get(i).getDepartmentId().equals(departmentId)){
                            if(veidooStatisticses.get(i).getWwcl() == 0){
                                data.setThreeRanking(0);
                            }
                            else{
                                data.setThreeRanking(i + 1);
                            }
                            break;
                        }
                    }
                }
            }
            map.put("data", data);
            map.put("userList",userMapper.queryUserByDepartmentId(departmentId));
        }
        return map;
    }


    /**
     *  获取已确认，待审核的数据
     * @param companyId 公司ID
     * @param time 时间
     * @param timeType 时间类型  1: 周数据  2：月数据  3：年数据
     * @param veidooType 维度 类型 1：一维数据 2：二维数据 3：三维数据
     */
    public PageList<UsersStatistics> queryPendingData(Integer companyId,Long time,Integer timeType,
                                                    Integer veidooType,Integer pageNO,Integer pageSize) throws Exception{
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
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
        List<UsersStatistics> statisticses = usersStatisticsMapper.queryPendingData(companyId, veidooType, (pageNO - 1) * pageSize, pageSize, startTime, endTime);
        Integer count = usersStatisticsMapper.countPendingData(companyId, veidooType, startTime, endTime);
        return new PageList<>(statisticses,count);
    }


    /**
     *  获取已确认，待审核的数据
     * @param companyId 公司ID
     * @param time 时间
     * @param timeType 时间类型  1: 周数据  2：月数据  3：年数据
     * @param veidooType 维度 类型 1：一维数据 2：二维数据 3：三维数据
     */
    public List<UsersStatistics> queryPendingDataNoPage(Integer companyId,Long time,Integer timeType,
                                                    Integer veidooType) throws Exception{
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
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
        return  usersStatisticsMapper.queryPendingData(companyId, veidooType,null,null, startTime, endTime);
    }


    /**
     * 导出一维数据
     * @param data
     * @param response
     */
    public void downLoadVeidooData(List<UsersStatistics> data, HttpServletResponse response){

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet();// 创建工作簿

        HSSFRow row = sheet.createRow(0); // 第一行

        row.createCell(0).setCellValue("公司");
        row.createCell(1).setCellValue("部门");
        row.createCell(2).setCellValue("员工姓名");
        row.createCell(3).setCellValue("k外");
        row.createCell(4).setCellValue("k内");
        row.createCell(5).setCellValue("k临时");
        row.createCell(6).setCellValue("k常规");
        row.createCell(7).setCellValue("k目标");
        row.createCell(8).setCellValue("k可比");
        row.createCell(9).setCellValue("k值完成率");
        row.createCell(10).setCellValue("k项目完成率");
        int i = 1;
        for (UsersStatistics d : data) {
            HSSFRow r = sheet.createRow(i);
            r.createCell(0).setCellValue(d.getCompanyName());
            r.createCell(1).setCellValue(d.getDepartmentName());
            r.createCell(2).setCellValue(d.getUserName());
            r.createCell(3).setCellValue(getString(d.getKw()));
            r.createCell(4).setCellValue(getString(d.getKn()));
            r.createCell(5).setCellValue(getString(d.getKl()));
            r.createCell(6).setCellValue(getString(d.getKc()));
            r.createCell(7).setCellValue(getString(d.getKmb()));
            r.createCell(8).setCellValue(getString(d.getKkb()));
            r.createCell(9).setCellValue(getString(d.getKwcl()));
            r.createCell(10).setCellValue(getString(d.getkProjectSchedule()));
            i++;
        }



        OutputStream out = null;
        try {
            String name = "detail.xls";
            out = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename="+name+"");
            response.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String getString(Object d) {
        if(null == d){
            return 0+"";
        }
        return d.toString();
    }


    /**
     * 发布 一维 二维 三维 分开发布 单条 发布
     * @param usersStatistics 实体
     * @param time 时间
     * @param timeType 时间类型 1：周  2：月
     * @param tieUpType 发布类型 1：一维 2：二维 3：三维
     * @param usersStatisticsThree 发布第三维时  不能为空 UsersStatisticsThree json
     * @throws ParseException
     */
    @Transactional
    public void save(UsersStatistics usersStatistics,Long time ,Integer timeType ,Integer tieUpType ,String usersStatisticsThree) throws ParseException {
        //开始时间
        Date startTime = new Date() ;
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
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
        //查询是否已存在当前时间段的数据
        UsersStatistics usersStatistics1 = usersStatisticsMapper.getStatistics(
                usersStatistics.getUserId(),usersStatistics.getDepartmentId(),startTime,endTime,timeType);

        UsersStatistics u = new UsersStatistics();
        u.setStartTime(startTime);
        u.setEndTime(endTime);
        u.setDepartmentId(usersStatistics.getDepartmentId());
        u.setUserId(usersStatistics.getUserId());
        u.setType(usersStatistics.getType());


        usersStatistics.setStartTime(startTime);
        usersStatistics.setEndTime(endTime);
        //如果不存在 新增
        if (null == usersStatistics1) {
            usersStatisticsMapper.save(usersStatistics);
            if (tieUpType == 3) {
                List<UsersStatisticsThree> usersStatisticsThrees = JSONArray.parseArray(usersStatisticsThree,UsersStatisticsThree.class);
                if (null == usersStatisticsThree || usersStatisticsThrees.size() < 1) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
                } else {
                    if (null == usersStatisticsThrees.get(0).getUserStatisticsId()) {
                        throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
                    }
                    //1.先进行删除
                    usersStatisticsThreeMapper.deleteByUserStatisticsId(usersStatistics.getId());
                }
                //2.批量添加
                for (UsersStatisticsThree three : usersStatisticsThrees) {
                    three.setUserStatisticsId(usersStatistics.getId());
                }
                usersStatisticsThreeMapper.saveList(usersStatisticsThrees);
            }
        } else {
            //更新
            usersStatisticsMapper.update(usersStatistics);
            if (tieUpType == 3) {
                List<UsersStatisticsThree> usersStatisticsThrees = JSONArray.parseArray(usersStatisticsThree,UsersStatisticsThree.class);
                if (null == usersStatisticsThree || usersStatisticsThrees.size() < 1) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
                } else {
                    if (null == usersStatisticsThrees.get(0).getUserStatisticsId()) {
                        throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
                    }
                    //1.先进行删除
                    usersStatisticsThreeMapper.deleteByUserStatisticsId(usersStatistics1.getId());
                }
                //2.批量添加
                for (UsersStatisticsThree three : usersStatisticsThrees) {
                    three.setUserStatisticsId(usersStatistics1.getId());
                }
                usersStatisticsThreeMapper.saveList(usersStatisticsThrees);
            }
        }

    }









    /**
     * 发布 一维 二维 三维 分开发布 多条 发布
     * @param usersStatistics 实体
     * @param time 时间
     * @param companyId 公司id
     * @param timeType 时间类型 1：周  2：月
     * @param tieUpType 发布类型 1：一维 2：二维 3：三维
     * @param usersStatisticsThree 发布第三维时  不能为空 UsersStatisticsThree json
     * @throws ParseException
     */
    @Transactional
    public void saveList(String usersStatistics,Long time ,Integer timeType ,Integer tieUpType ,
                         String usersStatisticsThree ,Integer companyId) throws ParseException {


        //开始时间
        Date startTime = new Date();
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");

        if (null != time) {
            bDate = Timestamp.parseDate(String.valueOf(time / 1000), "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        }
        if (timeType == 1) {
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate, 1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate, 7);
        }
        if (timeType == 2) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate, "first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate, "last");
        }

        List<UsersStatistics> updateList = new ArrayList<>();
        List<UsersStatistics> saveList = new ArrayList<>();

        List<UsersStatistics> usersStatisticses = JSONArray.parseArray(usersStatistics, UsersStatistics.class);
        for (UsersStatistics statistics : usersStatisticses) {
            if(tieUpType == 1){
                statistics.setOneStatus(1);
            }
            if(tieUpType == 2){
                statistics.setTwoStatus(1);
            }
        }
        //查询是否已存在当前时间段的数据
        List<UsersStatistics> usersStatistics1 = usersStatisticsMapper.getStatisticsCompanyList(startTime, endTime,companyId);

        if (tieUpType == 3) {
            List<UsersStatisticsThree> usersStatisticsThrees = JSONArray.parseArray(usersStatisticsThree, UsersStatisticsThree.class);
            if (null == usersStatisticsThree || usersStatisticses.size() * 4 != usersStatisticsThrees.size()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL, "第三维没有配置项");
            } else {
                if (null == usersStatisticsThrees.get(0).getUserStatisticsId()) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
                }
                List<Integer> userStatisticsIds = new ArrayList<>();
                for (UsersStatisticsThree three : usersStatisticsThrees) {
                    userStatisticsIds.add(three.getUserStatisticsId());
                }
                //1.先进行删除
                usersStatisticsThreeMapper.deleteByUserStatisticsIdList(userStatisticsIds);
            }
            //2.批量添加
            out:
            for (UsersStatisticsThree three : usersStatisticsThrees) {
                for (UsersStatistics statistics : saveList) {
                    if (statistics.getType() == 1) {
                        if (statistics.getUserId().equals(three.getUserId())) {
                            three.setUserStatisticsId(statistics.getId());
                            continue out;
                        }
                    }
                }
            }
            if (usersStatisticsThrees.size() > 0) {
                usersStatisticsThreeMapper.saveList(usersStatisticsThrees);
            }
        }
        for (UsersStatistics statistics : usersStatisticses) {
            statistics.setStartTime(startTime);
            statistics.setEndTime(endTime);
            if (!usersStatistics1.contains(statistics)) {
                saveList.add(statistics);
            } else {
                updateList.add(statistics);
            }
        }
        if (saveList.size() > 0) {
            usersStatisticsMapper.saveList(saveList);
        }
        if (updateList.size() > 0) {
            usersStatisticsMapper.updateList(updateList);
        }

    }


    /**
     * 统计月度 团队奖
     * @param date
     * @return
     */
    public List<GoodTeam> statisticsGoodTeam(Date date,Integer companyId) throws ParseException {
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        if (null != date) {
            bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(date,"yyyy-MM-dd")),"yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        }
        Date startTime = DateTimeHelper.getMonthByDate(bDate, "first");
        //获取这个时间的这月月末的日期 结束时间
        Date endTime = DateTimeHelper.getMonthByDate(eDate, "last");
        // 如果是公司统计
        List<VeidooDepartment> veidooDepartments = usersStatisticsMapper.statisticsVeidooForAPIRankingForAPI(startTime, endTime, companyId);
        // 如果是月 团队K文化
        List<VeidooStatistics> veidooStatisticses1 = usersStatisticsMapper.statisticsVeidooRankingForAPIByCompanyId(startTime, endTime, companyId);
        // 计算 三维完成率
        for (VeidooDepartment veidooDepartment : veidooDepartments) {
            for (VeidooStatistics veidooStatistics : veidooStatisticses1) {
                if(veidooStatistics.getDepartmentId().equals(veidooDepartment.getDepartmentId())){
                    // 计算三维
                    if(0 != veidooStatistics.getWwcl()){
                        veidooDepartment.setTotalScore(
                                veidooDepartment.getTotalScore() + (veidooStatistics.getWwcl() / veidooStatistics.getPersons() * 20)
                        );
                    }
                    break;
                }
            }
        }
        //排序
        Collections.sort(veidooDepartments);
        List<GoodTeam> goodTeams = new ArrayList<>();
        for (int i = 0; i < veidooDepartments.size(); i++) {
            // 设置数据
            if(veidooDepartments.get(i).getTotalScore() >= 100){
                GoodTeam team = new GoodTeam();
                team.setScore(veidooDepartments.get(i).getTotalScore());
                team.setDepartmentId(veidooDepartments.get(i).getDepartmentId());
                team.setDepartmentName(veidooDepartments.get(i).getDepartmentName());
                team.setRanking(i + 1);
                team.setMonth(startTime);
                goodTeams.add(team);
            }
        }
        return goodTeams;
    }



    /**
     * 数据公示 三维绩效考核得分 统计
     * @param time 时间
     * @param timeType 1：周 2：月
     * @param companyId 公司id
     * @return
     */
    public Map<String ,Object> getCompanySta(Long time ,Integer timeType ,Integer companyId) throws ParseException {

        //部门名
        String[] departmentNames;
        //三维绩效分
        Double[] score;

        //开始时间
        Date startTime = new Date();
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");

        if (null != time) {
            bDate = Timestamp.parseDate(String.valueOf(time / 1000), "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        }
        if (timeType == 1) {
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate, 1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate, 7);
        }
        if (timeType == 2) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate, "first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate, "last");
        }

        // 获取已经发布的第一维 第二维数据
        List<UsersStatistics> userDataList = usersStatisticsMapper.getCompanyStaOfUser(startTime, endTime, companyId);
        // 个人文化工程得分
        List<ThreeVeidooTemp> threeVeidooTemps = threeVeidooMapper.statisticsThreeVeidooForUser(startTime, endTime,
                startTime,endTime, null,null,companyId,companyId);
        userDataList = null == userDataList ? new ArrayList<>() : userDataList;
        if(null != threeVeidooTemps){
            for (ThreeVeidooTemp threeVeidooTemp : threeVeidooTemps) {
                boolean exist = false;
                if( userDataList.size() > 0){
                    for (UsersStatistics userData : userDataList) {
                        if(userData.getUserId().equals(threeVeidooTemp.getUserId())){
                            exist = true;
                            if(null == threeVeidooTemp.getTz()){
                                threeVeidooTemp.setTz(0.0);
                            }else{
                                threeVeidooTemp.setTz(threeVeidooTemp.getTz() >= 20 ? 20 : threeVeidooTemp.getTz());
                            }
                            double pK = threeVeidooTemp.getHy() + threeVeidooTemp.getTz() + threeVeidooTemp.getKg()
                                    + threeVeidooTemp.getWx() + threeVeidooTemp.getZb() + threeVeidooTemp.getYd();
                            double tK = threeVeidooTemp.getPersonKCulture();
                            userData.setWwcl(pK * 0.6 + tK * 0.4);
                            userData.setUserName(threeVeidooTemp.getUserName());
                            break;
                        }
                    }
                }
                if(!exist){
                    UsersStatistics userData = new UsersStatistics();
                    userData.setRwcl(0.0);
                    userData.setKwcl(0.0);
                    userData.setUserName(threeVeidooTemp.getUserName());
                    userData.setUserId(threeVeidooTemp.getUserId());
                    if(null == threeVeidooTemp.getTz()){
                        threeVeidooTemp.setTz(0.0);
                    }else{
                        threeVeidooTemp.setTz(threeVeidooTemp.getTz() >= 20 ? 20 : threeVeidooTemp.getTz());
                    }
                    double pK = threeVeidooTemp.getHy() + threeVeidooTemp.getTz() + threeVeidooTemp.getKg()
                            + threeVeidooTemp.getWx() + threeVeidooTemp.getZb() + threeVeidooTemp.getYd();
                    double tK = threeVeidooTemp.getPersonKCulture();
                    userData.setWwcl(pK * 0.6 + tK * 0.4);
                    userDataList.add(userData);
                }
            }
        }
        departmentNames = new String[userDataList.size()];
        score = new Double[userDataList.size()];
        int i = 0;
        for (UsersStatistics u : userDataList) {
            departmentNames[i] = u.getUserName();
            score[i] = (null == u.getKwcl() ? 0 : u.getKwcl()) * 0.5 +
                    (null == u.getRwcl() ? 0 : u.getRwcl()) * 0.3 +
                    (null == u.getWwcl() ? 0 : u.getWwcl())  * 0.2;
            i ++;
        }
        Map<String ,Object> map = new HashMap<>();
        map.put("departmentNames",departmentNames);
        map.put("score",score);
        return map;
    }


    /**
     * 后台统计第三维  文化工程
     * @param type 0：统计团队文化工程得分   1：团队文化工程完成率  2：个人文化工程得分 3： 团队K总完成率 4：团队结项完成率
     * @param date 日期
     * @param dateType 0：周统计   1：月统计
     * @param companyId 公司ID
     * @param departmentId 部门ID
     * @param userId  用户ID
     * @return
     */
    public List<UserK> queryKCulture(Integer type,Date date,Integer dateType,Integer companyId,
                                     Integer departmentId,Integer userId,Integer ccId) throws ParseException {
        List<UserK> data = new ArrayList<>();
        Date startTime = null;
        Date endTime = null;
        //上次开始时间
        Date lastStartTime = new Date() ;
        //上次结束时间
        Date lastEndTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");

        if (null != date) {
            bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(date,"yyyy-MM-dd")),"yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        }
        if (dateType == 0) {
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate,1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate,7); //获取这个时间的这周星期一的日期 开始时间
            lastStartTime = DateTimeHelper.getWeekByDate(bDate,1,1);
            //获取这个时间的这周星期日的日期 结束时间
            lastEndTime = DateTimeHelper.getWeekByDate(eDate,7,1);
        }
        if (dateType == 1) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
            lastStartTime = DateTimeHelper.getMonthByDate(bDate,"first",1);
            //获取这个时间的这月月末的日期 结束时间
            lastEndTime = DateTimeHelper.getMonthByDate(eDate,"last",1);
        }

        if(0 == type){
            // 统计团队文化工程得分 公式：个人文化总得分/团队人数
            data = usersStatisticsMapper.queryTeamKCulture(startTime,endTime,companyId,departmentId,ccId,userId);
        }
        else if(1 == type){
            // 团队文化工程完成率 = 团队得分/全公司团队平均分
            // 全公司团队平均分= 全公司团队得分总和/团队个数
            data = usersStatisticsMapper.queryTeamKCulture(startTime,endTime,companyId,departmentId,ccId,userId);
            List<UserK> ks = usersStatisticsMapper.queryTeamKCulture(startTime, endTime, ccId, null, ccId, null);
            if(null != data && null != ks){
                // 计算全公司团队的平均分
                double tempK = 0.0;
                for (UserK k : ks) {
                    tempK += k.getUserK();
                }
                for (UserK userK : data) {
                    userK.setUserK(userK.getUserK() / (tempK / ks.size()));
                }
            }
        }
        else if(2 == type){
            // 个人文化工程得分
            List<ThreeVeidooTemp> threeVeidooTemps = threeVeidooMapper.statisticsThreeVeidooForUser(startTime, endTime,
                    lastStartTime,lastEndTime, userId,departmentId,ccId,companyId);
            if(null != threeVeidooTemps){
                for (ThreeVeidooTemp threeVeidooTemp : threeVeidooTemps) {
                    UserK userK = new UserK();
                    userK.setName(threeVeidooTemp.getUserName());
                    if(null == threeVeidooTemp.getTz()){
                        threeVeidooTemp.setTz(0.0);
                    }else{
                        threeVeidooTemp.setTz(threeVeidooTemp.getTz() >= 20 ? 20 : threeVeidooTemp.getTz());
                    }
                    double pK = threeVeidooTemp.getHy() + threeVeidooTemp.getTz() + threeVeidooTemp.getKg()
                            + threeVeidooTemp.getWx() + threeVeidooTemp.getZb() + threeVeidooTemp.getYd();
                    double tK = threeVeidooTemp.getPersonKCulture();
                    userK.setUserK(pK * 0.6 + tK * 0.4);
                    data.add(userK);
                }
            }
        }
        else if(3 == type){
            // 团队K总完成率
            data = usersStatisticsMapper.queryTeamKFinishRate(startTime,endTime,companyId,departmentId);
        }
        else if(4 == type){
            // 团队结项完成率
            data = usersStatisticsMapper.queryteamPostProjectFinishRate(startTime,endTime,companyId,departmentId);
        }

        Collections.sort(data);

        return  data;
    }



    /**
     * 三维绩效总汇表
     * @param time 时间
     * @param timeType 1：周 2：月
     * @param companyId 公司id
     * @return
     */
    public List<TotalAchievements> getTotalAchievements(Long time , Integer timeType , Integer companyId) throws ParseException {

        //开始时间
        Date startTime = new Date();
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(),"yyyy-MM-dd")),"yyyy-MM-dd");
        Date eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");

        if (null != time) {
            bDate = Timestamp.parseDate(String.valueOf(time / 1000), "yyyy-MM-dd");
            eDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(bDate, "yyyy-MM-dd HH:mm:ss") + 24 * 3600 - 1), "yyyy-MM-dd HH:mm:ss");
        }
        if (timeType == 1) {
            //获取这个时间的这周星期一的日期 开始时间
            startTime = DateTimeHelper.getWeekByDate(bDate, 1);
            //获取这个时间的这周星期日的日期 结束时间
            endTime = DateTimeHelper.getWeekByDate(eDate, 7);
        }
        if (timeType == 2) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate, "first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate, "last");
        }

        List<Department> departments = departmentMapper.queryDepartmentByCompany(companyId);

        // 第一维度
        List<TotalAchievements> oneList = usersStatisticsMapper.getTotalAchievementsOfOne(startTime, endTime, companyId);
        // 第二维
        List<TotalAchievements> twoList = usersStatisticsMapper.getTotalAchievementsOfTwo(startTime, endTime, companyId);
        // 第三维
        List<UserK> threeList = usersStatisticsMapper.queryTeamKCulture(startTime, endTime, companyId, null, companyId, null);

        List<TotalAchievements> resultList = new ArrayList<>();

        if(null != departments && departments.size() > 0){
            for (Department department : departments) {
                TotalAchievements result = new TotalAchievements();
                result.setDepartmentName(department.getDepartmentName());
                result.setDepartmentId(department.getId());
                boolean oneExist = false;
                boolean twoExist = false;
                boolean threeExist = false;
                if(null != oneList && oneList.size() > 0){
                    for (TotalAchievements one : oneList) {
                        if(one.getDepartmentId().equals(department.getId())){
                            oneExist = true;
                            result.setActualK(one.getActualK());
                            result.setTargetK(one.getTargetK());
                            result.setkSchedule(one.getkSchedule());
                            result.setOneScore(null == one.getkSchedule()? 0.0 : one.getkSchedule() * 0.5);
                            break;
                        }
                    }
                }
                if(null != twoList && twoList.size() > 0){
                    for (TotalAchievements two : twoList) {
                        if(two.getDepartmentId().equals(department.getId())){
                            twoExist = true;
                            result.setActualJX(two.getActualJX());
                            result.setTargetJX(two.getTargetJX());
                            result.setkProjectSchedule(two.getkProjectSchedule());
                            result.setTwoScore(null == two.getkProjectSchedule() ? 0.0 : two.getkProjectSchedule() * 0.3);
                            break;
                        }
                    }
                }
                if(null != threeList && threeList.size() > 0){
                    double total = 0.0;
                    for (UserK three : threeList) {
                        total += three.getUserK();
                    }
                    double v = total / threeList.size(); //文化工程得分平均值
                    for (UserK three : threeList) {
                        if(three.getId().equals(department.getId())){
                            threeExist = true;
                            result.setTeamScore(three.getUserK());
                            result.setAverageScore(v);
                            result.setThreeScore(null == three.getUserK() ? 0.0 : three.getUserK() / v * 0.2);
                            break;
                        }
                    }
                }
                if(!oneExist){
                    result.setActualK(0.0);
                    result.setTargetK(0.0);
                    result.setkSchedule(0.0);
                    result.setOneScore(0.0);
                }
                if(!twoExist){
                    result.setActualJX(0.0);
                    result.setTargetJX(0.0);
                    result.setkProjectSchedule(0.0);
                    result.setTwoScore(0.0);
                }
                if(!threeExist){
                    result.setTeamScore(0.0);
                    result.setAverageScore(0.0);
                    result.setThreeScore(0.0);
                }
                result.setTotalScore(result.getOneScore() + result.getTwoScore() + result.getThreeScore());
                resultList.add(result);
            }
        }
        // 设置排名
        Collections.sort(resultList);
        for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setRanking(i+1);
        }
        return resultList;
    }


    /**
     * 后台 工时统计
     * @param companyId 分公司ID
     * @param departmentId 部门ID
     * @param type 0:个人工作时间统计  1:个人学习时间统计 2:个人运动时间统计  3:个人工作学习总时间
     */
    public List<UserK> statisticsWorkTime(Integer companyId,Integer departmentId,Integer type,
                                          Date startTime,Date endTime){
       return usersStatisticsMapper.statisticsWorkTime(companyId,departmentId,type,startTime,endTime);
    }



    public void updateUsersStatistics(UsersStatistics usersStatistics) throws Exception{
        usersStatisticsMapper.updateById(usersStatistics);
    }




}
