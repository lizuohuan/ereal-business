package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.enums.RoleEnum;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.*;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.StatusConstant;
import com.magic.ereal.business.util.Timestamp;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * K 值统计 -- 业务
 * @author lzh
 * @create 2017/5/17 15:47
 */
@Service
public class KStatisticsService {

    @Resource
    private IKStatisticsMapper kStatisticsMapper;
    @Resource
    private ISecondTargetMapper secondTargetMapper;
    @Resource
    private ISecondVeidooMapper secondVeidooMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private ISecondVeidooDepartmentMapper secondVeidooDepartmentMapper;

    /**
     * 查询时间段的k值 个人
     * @param time 时间
     * @param userId 结束时间
     * @param timeType 查询时间阶段类型 1:周 2：月 3：年
     * @return
     */
    public Map<String ,Object> getByTimePersonage(Long time , Integer userId  , Integer timeType , User user) throws ParseException {
        Map<String ,Object> map = new HashMap<>();
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
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
        }
        KPersonageStatistics k = kStatisticsMapper.getByTimePersonage(startTime, endTime, userId ,timeType);
        if (null == k) {
            k = new KPersonageStatistics();
        }
        map.put("kw",k.getKw());
        map.put("kn",k.getKn());
        if (timeType == 1) {
            map.put("kl",k.getKl());
            map.put("kc",k.getKc());
        }
        if (timeType == 2) {
            //k目标
            map.put("km",k.getKmb());
            //完成率
            // TODO: 2017/5/18  完成率计算公式 k值完成率 = kw + kn + kl + kc / （k可比 * 个人转换系数）
            // TODO: 2017/5/18  详细 看 入职培训-三维继续考核.pdf  2 三维绩效考核 第一维：K值完成率
            map.put("wcl",(k.getKw() + k.getKn() + k.getKl() + k.getKc()) /
                    (0 == (k.getKmb() * k.getCr()) ? 1 : (k.getKmb() * k.getCr())));
        }
        return map;
    }

    /**
     * 查询时间段的k值 个人（团队） 统计图（柱状）
     * @param time 时间
     * @param departmentId 团队id
     * @param timeType 查询时间阶段类型 1:周 2：月 3：年
     * @return
     */
    public Map<String ,Object> getByTimePersonageMap(Long time , Integer departmentId , Integer timeType ,Integer userId) throws ParseException {
        // TODO: 2017/5/18  当前统计的只要产生记录的k值 就进行了统计 并不是统计的完成的项目   后期修改
        Map<String ,Object> map = new HashMap<>();
        //开始时间
        Date startTime ;
        //结束时间
        Date endTime;
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
            //封装的个人团队的柱状图
            packagingPersonageMap(map,startTime,endTime,departmentId);
        }
        if (timeType == 2) {
            //type 月初 first  月末 last
            //获取这个时间的这月月初的日期 开始时间
            startTime = DateTimeHelper.getMonthByDate(bDate,"first");
            //获取这个时间的这月月末的日期 结束时间
            endTime = DateTimeHelper.getMonthByDate(eDate,"last");
            //获取本月天数
            Integer num = DateTimeHelper.getDaysOfMonth(bDate);
            packagingPersonageMap(map,startTime,endTime,userId,timeType,num);

        }
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
            packagingPersonageMap(map,startTime,endTime,userId,timeType,12);
        }
        return map;
    }

    /**
     * 封装的个人团队的柱状图（周）
     * @param map map
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 部门id
     */
    public void packagingPersonageMap(Map<String ,Object> map ,
                                      Date startTime ,Date endTime , Integer departmentId) {
        List<KPersonageStatistics> list = kStatisticsMapper.getByTimePersonageMap(startTime, endTime ,departmentId);
        //k外数组
        Double[] kw = new Double[list.size()];
        //k内数组
        Double[] kn = new Double[list.size()];
        //k临时数组
        Double[] kl = new Double[list.size()];
        //k常规数组
        Double[] kc = new Double[list.size()];
        //项目总k数组
        Double[] kt = new Double[list.size()];

        String[] name = new String[list.size()];
        int i = 0;
        for (KPersonageStatistics statistics : list) {
            kw[i] = new BigDecimal(statistics.getKw()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
            kn[i] = new BigDecimal(statistics.getKn()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
            kl[i] = new BigDecimal(statistics.getKl()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
            kc[i] = new BigDecimal(statistics.getKc()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
            kt[i] = kw[i] + kn[i];
            kt[i] = new BigDecimal(kt[i]).setScale(10,BigDecimal.ROUND_DOWN).doubleValue();
            name[i] = statistics.getName();
            i ++;
        }
        map.put("kw",kw);
        map.put("kn",kn);
        map.put("kl",kl);
        map.put("kc",kc);
        map.put("kt",kt);
        map.put("name",name);
    }


    /**
     * 封装的个人月 年 统计折线统计图
     * @param map map
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param timeType  2：月 3：年
     * @param num  timeType ：2 天数  3：月数
     */
    public void packagingPersonageMap(Map<String ,Object> map ,
                                      Date startTime ,Date endTime , Integer userId , Integer timeType ,Integer num) throws ParseException {
        //k外数组
        Double[] kw = new Double[num];
        //k内数组
        Double[] kn = new Double[num];
        //项目总k数组
        Double[] kt = new Double[num];
        String[] date = new String[num];
        Integer startTimeInt = Timestamp.timesTamp(startTime,"yyyy-MM-dd");
        Integer endTimeInt = Timestamp.timesTamp(endTime,"yyyy-MM-dd HH:mm:ss");

        if (timeType == 2) {
            //统计外部项目的k值 外部结项 内部结项
            List<ProjectK> projectKs = kStatisticsMapper.
                    getPersonageProjectK(startTime, endTime, userId);
            //统计外部项目的k值 周验收
            List<ProjectWeekKAllocation> projectWeekK =
                    kStatisticsMapper.getPersonageProjectWeek(startTime, endTime, userId);
            //统计内部项目的k值 周验收
            List<ProjectInteriorWeekKAllocation> projectInteriorWeek =
                    kStatisticsMapper.getPersonageProjectInWeek(startTime, endTime, userId);
            //计算月
            int j = 0;
            for (int i = startTimeInt ; i < endTimeInt ; i += 24 * 3600) {
                //计算k内
                {
                    Double knD = 0.0;
                    out:for (ProjectInteriorWeekKAllocation p : projectInteriorWeek) {
                        Integer dateTime = Timestamp.timesTamp(p.getCreateTime(),"yyyy-MM-dd");
                        if (dateTime.equals(i)) {
                            knD += new BigDecimal(p.getK()).setScale(3,BigDecimal.ROUND_DOWN).doubleValue();
                        }
                    }
                    kn[j] = knD;
                }
                //计算k外
                {
                    //k外周
                    Double kww = 0.0;
                    //k外 结项
                    Double kwD = 0.0;
                    out:for (ProjectWeekKAllocation p : projectWeekK) {
                        Integer dateTime = Timestamp.timesTamp(p.getCreateTime(),"yyyy-MM-dd");
                        if (dateTime.equals(i)) {
                            kww += new BigDecimal(p.getSectionSumK() * p.getRatio() / 100).setScale(3,BigDecimal.ROUND_DOWN).doubleValue();;
                        }
                    }
                    out:for (ProjectK p : projectKs) {
                        Integer dateTime = Timestamp.timesTamp(p.getCreateTime(),"yyyy-MM-dd");
                        if (dateTime.equals(i)) {
                            kwD += new BigDecimal(p.getSumK()).setScale(3,BigDecimal.ROUND_DOWN).doubleValue();
                        }
                    }
                    kw[j] = kww + kwD;
                }
                kt[j] = kn[j] + kw[j];
                date[j] = Timestamp.TimeStamp2Date(String.valueOf(i),"M月d日");
                j ++;
            }
        } else {
            //统计外部项目的k值 外部结项 内部结项（12个月）
            List<KStatisticsPMonth> getPersonageProjectKMonth = kStatisticsMapper.getPersonageProjectKMonth(startTime, endTime, userId);
            //统计外部项目的k值 周验收（12个月）
            List<KStatisticsPMonth> getPersonageProjectWeekMonth = kStatisticsMapper.getPersonageProjectWeekMonth(startTime, endTime, userId);
            //统计内部项目的k值 周验收（12个月）
            List<KStatisticsPMonth> getPersonageProjectInWeekMonth = kStatisticsMapper.getPersonageProjectInWeekMonth(startTime, endTime, userId);
            //每月月初
            int getMonthStar = startTimeInt;
            int j = 0;
            for (int i = getMonthStar ; i < endTimeInt ; i = getMonthStar) {
                //计算k外
                {
                    //k外周
                    Double kww = 0.0;
                    //k外 结项
                    Double kwD = 0.0;
                    out:for (KStatisticsPMonth pk : getPersonageProjectKMonth) {
                        Integer dateTime = Timestamp.timesTamp(pk.getMonthTime(),"yyyy-MM");
                        if (dateTime.equals(i)) {
                            kwD = new BigDecimal(pk.getTk()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                            break out;
                        }
                    }
                    out:for (KStatisticsPMonth pk : getPersonageProjectWeekMonth) {
                        Integer dateTime = Timestamp.timesTamp(pk.getMonthTime(),"yyyy-MM");
                        if (dateTime.equals(i)) {
                            kww = new BigDecimal(pk.getTk()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                            break out;
                        }
                    }
                    kw[j] = kww + kwD;
                }
                //计算k内
                {
                    Double knD = 0.0;
                    out:for (KStatisticsPMonth pk : getPersonageProjectInWeekMonth) {
                        Integer dateTime = Timestamp.timesTamp(pk.getMonthTime(),"yyyy-MM");
                        if (dateTime.equals(i)) {
                            knD = new BigDecimal(pk.getTk()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                            break out;
                        }
                    }
                    kn[j] = knD;
                }
                kt[j] = kn[j] + kw[j];
                date[j] = Timestamp.TimeStamp2Date(String.valueOf(i),"yy年M月");
                //计算每月月初
                getMonthStar = Timestamp.timesTamp(DateTimeHelper.getMonthByDate(new Date(Long.valueOf(getMonthStar * Long.valueOf(1000) + 1)),"last"),"yyyy-MM-dd") + 3600 * 24;
                j ++;
            }

        }

        map.put("kw",kw);
        map.put("kn",kn);
        map.put("kt",kt);
        map.put("date",date);
    }


    /**
     * 查询时间段的k值 部门 公司
     * @param time 时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 1:周 2：月 3：年
     * @return
     */
    public Map<String ,Object> getByTimeDeCom(Long time , Integer departmentId ,Integer companyId , Integer timeType ,Integer type) throws ParseException {
        Map<String ,Object> map = new HashMap<>();
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
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
        }
//        KDCStatistics k = kStatisticsMapper.test(startTime, endTime ,departmentId ,companyId,type ,timeType );
        KDCStatistics k = kStatisticsMapper.getByTimeDeCom(startTime, endTime ,departmentId ,companyId,type ,timeType );
        if (null == k) {
            k = new KDCStatistics();
        }
        if (timeType == 1) {
            map.put("kw",k.getKw());
            map.put("kn",k.getKn());
        }

        if (timeType == 2) {
            map.put("kw",k.getKw());
            map.put("kn",k.getKn());
            //k目标
            // TODO: 2017/5/18  K目标
            map.put("km",k.getMonthKTarget());
            //完成率
            // TODO: 2017/5/18  完成率计算公式 k值完成率 = kw + kn + kl + kc / （k可比 * 个人转换系数）
            // TODO: 2017/5/18  详细 看 入职培训-三维继续考核.pdf  2 三维绩效考核 第一维：K值完成率
            map.put("wcl",k.getMonthSchedule());
        }
        if (timeType == 3) {
            // 已承接所有外部项目总k
            map.put("yearAllTkw",k.getYearAllTkw());
            // 已完成外部项目总k
            map.put("yearCompleteTkw",k.getYearCompleteTkw());
            // k外完成率
            map.put("yearKwSchedule",k.getYearKwSchedule());
            // 已承接所有内部项目总k
            map.put("yearAllTkn", k.getYearAllTkn());
            // 已完成内部项目总k
            map.put("yearCompleteTkn", k.getYearCompleteTkn());
            // k内完成率
            map.put("yearKnSchedule", k.getYearKnSchedule());



        }
        return map;
    }


    /**
     * 查询时间段的k值 折线图使用 部门 公司
     * @param time 时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 1:周 2：月 3：年
     * @return
     */
    public Map<String , Object> getCarToGramDapCom(Long time , Integer type ,
                                                   Integer timeType , Integer departmentId , Integer companyId) throws ParseException {
        Map<String ,Object> map = new HashMap<>();
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

        Integer num = null;
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
            //获取本月天数
            num = DateTimeHelper.getDaysOfMonth(bDate);
        }
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
            num = 12;
        }
        List<KDCStatistics> ks = new ArrayList<>();
        //k外数组
        Double[] kw = null;
        //k内数组
        Double[] kn = null;
        //项目总k数组
        Double[] kt = null;
        String[] name = null;
        if (timeType == 1) {
            if (type == 1) {
                ks = kStatisticsMapper.getByTimeWeekDepartmentMap(startTime, endTime ,departmentId);
                kw = new Double[ks.size()];
                kn = new Double[ks.size()];
                kt = new Double[ks.size()];
                name = new String[ks.size()];
                int i = 0;
                for (KDCStatistics k : ks) {

                    kw[i] = new BigDecimal(k.getKw()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                    kn[i] = new BigDecimal(k.getKn()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                    kt[i] = kw[i] + kn[i];
                    kt[i] = new BigDecimal(kt[i]).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                    name[i] = k.getUserName();
                    i ++;
                }
            }
            if (type == 2) {
                ks = kStatisticsMapper.getByTimeWeekCompanyMap(startTime,endTime,companyId);
                kw = new Double[ks.size()];
                kn = new Double[ks.size()];
                kt = new Double[ks.size()];
                name = new String[ks.size()];
                int i = 0;
                for (KDCStatistics k : ks) {
                    kw[i] = new BigDecimal(k.getKw()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                    kn[i] = new BigDecimal(k.getKn()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                    kt[i] = kw[i] + kn[i];
                    kt[i] = new BigDecimal(kt[i]).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                    name[i] = k.getDepartmentName();
                    i ++;
                }
            }
            map.put("kw",kw);
            map.put("kn",kn);
            map.put("kt",kt);
            map.put("name",name);
        } else {
            getByTimeProjectMap(map,startTime,endTime,departmentId,companyId,type,timeType ,num);
        }
        return map;
    }

    /**
     * k值统计 折线图使用 部门公司
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 2：月 3：年
     * @return
     */
    public void getByTimeProjectMap(Map<String ,Object> map , Date startTime ,Date endTime ,
                                    Integer departmentId ,Integer companyId ,Integer type, Integer timeType,Integer num ) throws ParseException {
        //k外数组
        Double[] kw = new Double[num];
        //k内数组
        Double[] kn = new Double[num];
        //项目总k数组
        Double[] kt = new Double[num];
        String[] name = new String[num];
        //获取外部项目周验收 折线图使用 部门公司
        List<KDCStatisticsMap> projectWeekKMap = kStatisticsMapper.getByTimeProjectWeekKMap
                (startTime, endTime, departmentId, companyId, type, timeType);
        //获取外部项目结项k值验收 折线图使用 部门公司
        List<KDCStatisticsMap> projectKMap = kStatisticsMapper.getByTimeProjectKMap
                (startTime, endTime, departmentId, companyId, type, timeType);
        //获取内部项目周验收 折线图使用 部门公司
//        List<KDCStatisticsMap> projectInMap = kStatisticsMapper.getByTimeProjectWeekKMap
//                (startTime, endTime, departmentId, companyId, type, timeType);
        //获取内部项目周验收 折线图使用 部门公司
        List<KDCStatisticsMap> projectInMap = kStatisticsMapper.getByTimeProjectInMap
                (startTime, endTime, departmentId, companyId, type, timeType);

        Integer startTimeInt = Timestamp.timesTamp(startTime,"yyyy-MM-dd");
        Integer endTimeInt = Timestamp.timesTamp(endTime,"yyyy-MM-dd HH:mm:ss");
        if (timeType == 2) {
            int j = 0;
            for (int i = startTimeInt; i < endTimeInt ; i += 3600 *24 ) {
                //外部项目周验收 k值 临时使用
                Double pwk = 0.0;
                //外部项目结项 k值 临时使用
                Double pk = 0.0;
                //内部项目周验收  k值 临时使用
                Double pik = 0.0;
                out:for (KDCStatisticsMap k : projectWeekKMap) {
                    k.setDateTime(DateTimeHelper.dateFortimestamp(k.getDateTime().getTime(),"yyyy-MM-dd"));
                    if (k.getDateTime().getTime() / 1000 == i ) {
                        pwk = new BigDecimal(k.getK()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                        break;
                    }
                }
                out:for (KDCStatisticsMap k : projectKMap) {
                    k.setDateTime(DateTimeHelper.dateFortimestamp(k.getDateTime().getTime(),"yyyy-MM-dd"));
                    if (k.getDateTime().getTime() / 1000 == i ) {
                        pk = new BigDecimal(k.getK()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                        break;
                    }
                }
                out:for (KDCStatisticsMap k : projectInMap) {
                    k.setDateTime(DateTimeHelper.dateFortimestamp(k.getDateTime().getTime(),"yyyy-MM-dd"));
                    if (k.getDateTime().getTime() / 1000 == i  ) {
                        pik = new BigDecimal(k.getK()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                        break;
                    }
                }

                kw[j] = pwk + pk;
                kn[j] = pik;
                kt[j] = kw[j] + kn[j];
                name[j] = Timestamp.TimeStamp2Date(String.valueOf(i),"M月d日");
                j ++;
            }

        }
        if (timeType == 3) {
            int j = 0;
            //每月月初
            int getMonthStar = startTimeInt;
            for (int i = getMonthStar ; i < endTimeInt ; i = getMonthStar) {
                //外部项目周验收 k值 临时使用
                Double pwk = 0.0;
                //外部项目结项 k值 临时使用
                Double pk = 0.0;
                //内部项目周验收  k值 临时使用
                Double pik = 0.0;
                out:for (KDCStatisticsMap k : projectWeekKMap) {
                    Integer dateTime = Timestamp.timesTamp(k.getDateTime(),"yyyy-MM");
                    if (dateTime.equals(i)) {
                        pwk = new BigDecimal(k.getK()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                        break out;
                    }
                }
                out:for (KDCStatisticsMap k : projectKMap) {
                    Integer dateTime = Timestamp.timesTamp(k.getDateTime(),"yyyy-MM");
                    if (dateTime.equals(i)) {
                        pk = new BigDecimal(k.getK()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                        break out;
                    }
                }
                out:for (KDCStatisticsMap k : projectInMap) {
                    Integer dateTime = Timestamp.timesTamp(k.getDateTime(),"yyyy-MM");
                    if (dateTime.equals(i)) {
                        pik = new BigDecimal(k.getK()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                        break out;
                    }
                }
                //计算每月月初
                getMonthStar = Timestamp.timesTamp(DateTimeHelper.getMonthByDate(new Date(Long.valueOf(getMonthStar * Long.valueOf(1000) + 1)),"last"),"yyyy-MM-dd") + 3600 * 24;
                kw[j] = pwk + pk;
                kn[j] = pik;
                kt[j] = kw[j] + kn[j];
                name[j] = Timestamp.TimeStamp2Date(String.valueOf(i),"yy年M月");
                j ++;
            }
        }

        map.put("kw",kw);
        map.put("kn",kn);
        map.put("kt",kt);
        map.put("name",name);

    }

    /**
     * 一维统计 web
     * @param time 时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    public PageList<OneStatistics> oneDimensional(Long time ,Integer userId , Integer departmentId ,
                                                  Integer companyId ,Integer type , Integer timeType,
                                                  Integer groupType,PageArgs pageArgs) throws ParseException {
        PageList<OneStatistics> pageList = new PageList<>();
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
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
        }
        List<Object> count = kStatisticsMapper.oneDimensionalCount(startTime,endTime,userId,departmentId,companyId,type,groupType);
        if(count.size() != 0) {
            List<OneStatistics> list = kStatisticsMapper.oneDimensional(startTime, endTime, userId, departmentId, companyId, type, groupType, pageArgs);

            // 计算K值 完成率 以及 K可比
            if(null != list){
                for (OneStatistics oneStatistics : list) {
                    oneStatistics.setkSchedule(
                            (oneStatistics.getKw() + oneStatistics.getKn() + oneStatistics.getKc() + oneStatistics.getKl())
                            / oneStatistics.getKm()
                    );
//                    oneStatistics.setKk(
//                            (oneStatistics.getKc() * oneStatistics.getZhxh() + oneStatistics.getKl() * oneStatistics.getZhxh()
//                            + oneStatistics.getKw() + oneStatistics.getKn()) / oneStatistics.getZhxh()
//                    );
                }
            }
            pageList.setList(list);
        }
        pageList.setTotalSize(count.size());
        return pageList;

    }


    /**
     * 一维统计 web
     * @param time 时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    public List<OneStatistics> oneDimensionalNOPage(Long time ,Integer userId , Integer departmentId ,
                                                  Integer companyId ,Integer type , Integer timeType,
                                                  Integer groupType) throws ParseException {
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
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
        }
        List<OneStatistics> list = kStatisticsMapper.oneDimensional(startTime, endTime, userId, departmentId, companyId, type, groupType, null);
        // 计算K值 完成率 以及 K可比
        if(null != list){
            for (OneStatistics oneStatistics : list) {
                oneStatistics.setkSchedule(
                        (oneStatistics.getKw() + oneStatistics.getKn() + oneStatistics.getKc() + oneStatistics.getKl())
                                / oneStatistics.getKm()
                );
                oneStatistics.setKk(
                        (oneStatistics.getKc() * oneStatistics.getZhxh() + oneStatistics.getKl() * oneStatistics.getZhxh()
                                + oneStatistics.getKw() + oneStatistics.getKn()) / oneStatistics.getZhxh()
                );
            }
        }
        return list;
    }

    /**
     * 二维统计 web
     * @param time 时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    public PageList<TwoStatistics> towListForWeb(Long time ,Integer userId , Integer departmentId ,
                                                  Integer companyId ,Integer type , Integer timeType,
                                                  Integer groupType,PageArgs pageArgs) throws ParseException {
        PageList<TwoStatistics> pageList = new PageList<>();
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
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
        }

        //获取这个时间的这月月初的日期 开始时间
        Date monthStartTime = DateTimeHelper.getMonthByDate(bDate,"first");
        //获取这个时间的这月月末的日期 结束时间
        Date monthEndTime = DateTimeHelper.getMonthByDate(eDate,"last");
        //判断 当前时间 该分公司下的月度二维目标 是否 提交完
        if(companyId != null){
            Integer c = secondTargetMapper.countSecondTargetByCompanyId(companyId, monthStartTime, monthEndTime);
            if(c == 0){
                //还有其他部门没有制定第二维数据
                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"该公司下还有其他部门没有制定目标");
            }
            Integer cc = secondVeidooDepartmentMapper.countDepartmentMethod(companyId);
            if(cc == 0){
                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"该公司下还有其他部门没有设置公式");
            }
        }




        // 查询部门ID  增加第三种 计算方式
        // 如果是 职能部门 并且 是第三种计算方式 ： 打分 + 权重比例  之和
//        User user = userMapper.queryManagerByDepartment(departmentId);
//        if(user.getRoleId() != RoleEnum.PROJECT_MANAGER.ordinal()){
//            // 如果是职能部门
//            SecondVeidoo secondVeidoo = secondVeidooMapper.querySecondVeidooByType(2);
//            if(secondVeidoo.getMethod() == 3){
//                // 如果是第三种考核方式
//                List<Object> count = secondTargetMapper.listForWebCount(startTime,endTime,userId,departmentId,companyId,type,groupType);
//                if(count.size() != 0) {
//                    pageList.setList(secondTargetMapper.listForWebThree(startTime,endTime,userId,departmentId,companyId,type,groupType,pageArgs));
//                }
//                pageList.setTotalSize(count.size());
//                return pageList;
//            }
//        }

        List<Object> count = secondTargetMapper.listForWebCount(startTime,endTime,userId,departmentId,companyId,type,groupType);
        if(count.size() != 0) {
            pageList.setList(secondTargetMapper.listForWeb(startTime,endTime,userId,departmentId,companyId,type,groupType,pageArgs,
                    monthStartTime,monthEndTime));
        }
        pageList.setTotalSize(count.size());
        return pageList;
    }

  /**
     * 二维统计 web
     * @param time 时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    public List<TwoStatistics> towListForWebNoPage(Long time ,Integer userId , Integer departmentId ,
                                                  Integer companyId ,Integer type , Integer timeType,
                                                  Integer groupType) throws ParseException {
        PageList<TwoStatistics> pageList = new PageList<>();
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
        if (timeType == 3) {
            //type 月初 first  月末 last
            //获取这个时间的这年年初的日期 开始时间
            startTime = DateTimeHelper.getYearByDate(bDate,"first");
            //获取这个时间的这年年末的日期 结束时间
            endTime = DateTimeHelper.getYearByDate(eDate,"last");
        }
        // 查询部门ID  增加第三种 计算方式
        // 如果是 职能部门 并且 是第三种计算方式 ： 打分 + 权重比例  之和
//        User user = userMapper.queryManagerByDepartment(departmentId);
//        if(user.getRoleId() != RoleEnum.PROJECT_MANAGER.ordinal()){
//            // 如果是职能部门
//            SecondVeidoo secondVeidoo = secondVeidooMapper.querySecondVeidooByType(2);
//            if(secondVeidoo.getMethod() == 3){
//                // 如果是第三种考核方式
//                return secondTargetMapper.listForWebThree(startTime,endTime,userId,departmentId,companyId,type,groupType,null);
//            }
//        }
        // 计算月度时间
        //获取这个时间的这月月初的日期 开始时间
        Date monthStartTime = DateTimeHelper.getMonthByDate(bDate,"first");
        //获取这个时间的这月月末的日期 结束时间
        Date monthEndTime = DateTimeHelper.getMonthByDate(eDate,"last");
        return secondTargetMapper.listForWeb(startTime,endTime,userId,departmentId,companyId,type,groupType,null,
                monthStartTime,monthEndTime);
    }


    /**
     * 后台个人K可比 统计
     * @param time 日期时间戳
     * @param userId 用户ID
     * @param departmentId  部门ID
     * @param companyId 分公司ID
     * @param timeType 1：周统计   2：月统计
     * @return
     * @throws Exception
     */
    public List<UserK> personKk(Long time ,Integer userId , Integer departmentId ,
                                Integer companyId , Integer timeType) throws Exception {

        //开始时间
        Date startTime = new Date();
        //结束时间
        Date endTime = new Date();
        Date bDate = Timestamp.parseDate(String.valueOf(Timestamp.timesTamp(new Date(), "yyyy-MM-dd")), "yyyy-MM-dd");
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
        return kStatisticsMapper.personKk(startTime,endTime,userId,departmentId,companyId);
    }



}
