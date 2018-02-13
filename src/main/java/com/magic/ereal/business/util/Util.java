package com.magic.ereal.business.util;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.tools.Tool;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 通用工具
 * Created by Eric Xie on 2017/5/3 0003.
 */
public class Util {




        /**
         * 获取 指定日期之前的 所有天数 包含两天
         * @return
         */
    public static List<Date> getDateList(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();
        List<Date> listDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dBegin = sdf.parse(dateFortimestamp(firstDayOfMonth.getTime(),"yyyy-MM-dd"));
            Date dEnd = sdf.parse(dateFortimestamp(lastDayOfMonth.getTime(),"yyyy-MM-dd"));
            listDate = getDatesBetweenTwoDate(dBegin, dEnd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listDate;
    }

    /**
     *  获取年份的月
     * @param date
     * @return
     */
    public static List<Date> getMonthDate(Date date){
        List<Date> dates = new ArrayList<>();
        for (int i = 1; i <= 12; i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH,i);
            calendar.set(Calendar.DAY_OF_MONTH,0);
            dates.add(dateFortimestamp(calendar.getTimeInMillis(),"yyyy-MM",null));
        }
        return  dates;
    }

    public static Date strToDate(String dataStr,String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date dateFortimestamp(long timestamp,String format,Integer flag){
        if(0 == timestamp){
            timestamp = System.currentTimeMillis();
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return strToDate(dateFormat.format(new Date(timestamp)),format);
    }



    private static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }


    /**
     * 将 时间戳 格式化成 指定格式 时间
     * @return
     */
    public static String dateFortimestamp(long timestamp,String format) throws Exception{
        if(0 == timestamp){
            timestamp = System.currentTimeMillis();
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(timestamp));
    }



//    public static

    public static void webView() throws Exception{
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine ();
        webEngine.load("www.baidu.com");
    }


    public static void main(String[] args) {
        try {
            webView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
