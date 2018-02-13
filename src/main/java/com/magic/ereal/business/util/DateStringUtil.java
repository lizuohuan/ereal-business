package com.magic.ereal.business.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class DateStringUtil {

    private static DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat monthFormat  = new SimpleDateFormat("yyyy-MM");

    public static String dateToStringBySimple(Date date,String simple){
        SimpleDateFormat sdf1 = new SimpleDateFormat(simple);
        String str = sdf1.format(date);
        return str;
    }

    /**
     * 改变date的格式
     * @param date
     * @param format
     * @return
     */
    public static Date dateFormt(Date date,String format){
       return StringToDate(dateToString(date,format),format);
    }

    /**
     * 时间转字符串
     * @param date
     * @return
     */
        public static String dateToString(Date date,String format) {
            SimpleDateFormat sdf1 = new SimpleDateFormat(format);
            String str = sdf1.format(date);
          /*  str = str.replaceAll("-","");*/
            return str;
        }

    public static Date stringToDate(String dateStr,String format){
        SimpleDateFormat sdf =   new SimpleDateFormat(format);
        try {
            Date date = sdf.parse( dateStr);
            return date;
        }catch (ParseException p){
            p.printStackTrace();
            return null;
        }

    }

    /**
     * 字符串转时间
     * @param date
     * @param format
     * @return
     */
    public static Date StringToDate(String date,String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(date, pos);
        return strtodate;

    }

    /**
     * 短时间字符串转长时间字符串
     * @param shortDate
     * @return
     */
    public static String shortToLong(String shortDate,String extend){
        if(null == shortDate || "".equals(shortDate)) {
            return shortDate;
        }
   /*     Pattern pattern = Pattern.compile(extend);
        Matcher matcher = pattern.matcher(shortDate);
        if (matcher.find()) {
            return shortDate;
        }*/
        return shortDate  + extend;
    }


    /**
     * @date 2017年1月24日 10:40:34
     * @Author joefan
     *
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取现在时间,这个好用
     *
     * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static Date getSqlDate() {
        Date sqlDate = new java.sql.Date(new Date().getTime());
        return sqlDate;
    }

    /**
     * 获取现在时间
     *
     * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    public static Date friendDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat
     *            yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
                    / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
                    / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000L) + Long.parseLong(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000L);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {
        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            return (year % 100) != 0;
        } else
            return false;
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     *
     * @param str
     * @return
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * 获取一个月的最后一天
     *
     * @param dat
     * @return
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
                || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    public static Integer getWeekInt(String sdate) {
        String str = "";
        str = getWeek(sdate);
        Integer w = 0;
        if ("星期日".equals(str)) {
            w = 0;
        } else if ("星期一".equals(str)) {
            w = 1;
        } else if ("星期二".equals(str)) {
            w = 2;
        } else if ("星期三".equals(str)) {
            w = 3;
        } else if ("星期四".equals(str)) {
            w = 4;
        } else if ("星期五".equals(str)) {
            w = 5;
        } else if ("星期六".equals(str)) {
            w = 6;
        }
        return w;
    }
    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     *
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";
        // 得到这个月的1号是星期几
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = getNextDay(sdate, (1 - u) + "");
        return newday;
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     *
     * @param k
     *            表示是取几位随机数，可以自己定
     */
    public static String getNo(int k) {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }

    /**
     * 返回一个随机数
     *
     * @param i
     * @return
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * @param
     */
    public static boolean RightDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (date == null)
            return false;
        if (date.length() > 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            sdf.parse(date);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * 获取倒计时间.
     * @param date 结束时间
     * @return 倒计时(秒)
     */
    public static Long countDown(Date date) {
        if (null == date) {
            return null;
        }

        long timeDiff = date.getTime() - System.currentTimeMillis();
        return timeDiff / 1000;
    }

    /**
     * 获取指定一个月的天数
     * @param date 年份月份
     * @return
     */
    public static int getMonthDays(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        c.setTime(sdf.parse(date));
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static  int[]  getDateLength(String  fromDate, String  toDate)  {
            Calendar  c1  =  getCal(fromDate);
            Calendar  c2  =  getCal(toDate);
            int[]  p1  =  {  c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH)  };
            int[]  p2  =  {  c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH)  };
            return  new  int[]  {  p2[0]  -  p1[0], p2[0]  *  12  +  p2[1]  -  p1[0]  *  12  -  p1[1], (int)  ((c2.getTimeInMillis()  -  c1.getTimeInMillis())  /  (24  *  3600  *  1000))  };
        }
    public static  Calendar  getCal(String  date)  {
            Calendar  cal  =  Calendar.getInstance();
            cal.clear();
            cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))  -  1, Integer.parseInt(date.substring(6, 8)));
            return  cal;
        }

    /**
     * 校验时间段是否有效，无效则重置到有效范围【日：30天、月：12个月】
     * @param params
     * @param dateType
     */
    public static void checkDateParams(Map<String, String> params, String dateType){
        String start = params.get("start");
        String end   = params.get("end");
        Calendar startCal = new GregorianCalendar();
        Calendar endCal   = new GregorianCalendar();
        switch (dateType){
            case "month":
                //开始时间、结束时间为空 设置默认时间段（当前时间推前12个月）
                if(StringUtils.isEmpty(start) && StringUtils.isEmpty(end)){
                    startCal.set(Calendar.MONTH, startCal.get(Calendar.MONTH) - 12);
                    startCal.set(Calendar.DAY_OF_MONTH, 1);
                    start = monthFormat.format(startCal.getTime());
                    endCal.set(Calendar.MONTH, endCal.get(Calendar.MONTH) + 1);
                    endCal.set(Calendar.DAY_OF_MONTH, 0);
                    end = monthFormat.format(endCal.getTime());
                }else //结束时间为空，设置结束时间为开始时间后延12个月或到当前月份
                    if(!StringUtils.isEmpty(start) && StringUtils.isEmpty(end)){
                        endCal   = buildCalendar(start, dateType);
                        endCal.set(Calendar.MONTH, endCal.get(Calendar.MONTH) + 12);
                        end = monthFormat.format(endCal.getTime()); 
                    }else //开始时间为空，设置开始时间默认为结束时间往前推12个月
                        if(StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)){
                            startCal = buildCalendar(end, dateType);
                            startCal.set(Calendar.MONTH, startCal.get(Calendar.MONTH) - 12);
                            start = monthFormat.format(startCal.getTime());
                        }else //开始、结束时间都不为空，校验时间是否超出有效范围12个月，否则重置起始日期
                            if(!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)){
                                startCal = buildCalendar(start, dateType);
                                startCal.set(Calendar.MONTH, startCal.get(Calendar.MONTH) + 12);
                                endCal   = buildCalendar(end, dateType);
                                if(startCal.getTime().before(endCal.getTime())){
                                    end = monthFormat.format(startCal.getTime());
                                }
                            }
                break;
            case "day":
                //开始时间、结束时间为空 设置默认时间段（当前时间推前30天）
                if(StringUtils.isEmpty(start) && StringUtils.isEmpty(end)){
                    startCal.set(Calendar.DAY_OF_MONTH, startCal.get(Calendar.DAY_OF_MONTH) - 30);
                    start = simpleFormat.format(startCal.getTime());
                    end = simpleFormat.format(endCal.getTime());
                }else //结束时间为空，设置结束时间为开始时间后延30天或到当前日期
                    if(!StringUtils.isEmpty(start) && StringUtils.isEmpty(end)){
                        startCal = buildCalendar(start, dateType);
                        startCal.set(Calendar.DAY_OF_MONTH, startCal.get(Calendar.DAY_OF_MONTH) + 30);
                        if(startCal.getTime().before(endCal.getTime())){
                            end = simpleFormat.format(startCal.getTime());
                        }else {
                            end = simpleFormat.format(endCal.getTime());
                        }
                    }else //开始时间为空，设置开始时间默认为结束时间往前推30天
                        if(StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)){
                            startCal = buildCalendar(end, dateType);
                            startCal.set(Calendar.DATE, startCal.get(Calendar.DATE) - 30);
                            start = simpleFormat.format(startCal.getTime());
                        }else //开始、结束时间都不为空，校验时间是否超出有效范围30天，否则重置起始日期
                            if(!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)){
                                startCal = buildCalendar(start, dateType);
                                endCal   = buildCalendar(end, dateType);
                                startCal.set(Calendar.DAY_OF_MONTH, startCal.get(Calendar.DAY_OF_MONTH) + 30);
                                if(startCal.getTime().before(endCal.getTime())){
                                    end = simpleFormat.format(startCal.getTime());
                                }
                            }
                break;
            default:
                break;
        }
        params.put("start", start);
        params.put("end", end);
    }

    public static Calendar buildCalendar(String date, String dateType){
        Calendar calendar = new GregorianCalendar();
        switch (dateType){
            case "month":
                calendar.set(Calendar.YEAR, Integer.parseInt(date.split("-")[0]));
                calendar.set(Calendar.MONTH, Integer.parseInt(date.split("-")[1]) - 1);
                break;
            case "day":
                calendar.set(Calendar.YEAR, Integer.parseInt(date.split("-")[0]));
                calendar.set(Calendar.MONTH, Integer.parseInt(date.split("-")[1]) - 1);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.split("-")[2]));
                break;
            default:
                break;
        }
        return calendar;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(dateToString(new Date(), "yyyy年MM月") + "工作学习时间统计表");
    }

/*    public static void main(String[] args) {
            Date date = new Date();

            String str =  dateToString(date);
            System.out.println(str);

         *//*   Date date =  new Date();
            System.out.println(date);
            System.out.println(friendTime(date));*//*

        }*/
    //获取某月最大天数
    public static String getLastDayOfMonth(String str){


        String a=str.replace("-", "");
        int year=Integer.valueOf(a.substring(0, 4));

        int month=Integer.parseInt(a.substring(a.length()-2));

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        String lastTime = DateStringUtil.shortToLong(lastDayOfMonth, " 23:59:59");
        return lastTime;

    }



    }
