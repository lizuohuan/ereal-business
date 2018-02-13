package com.magic.ereal.business.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 处理数据相关类.
 */
public class FunctionData {
	/**
	 * 通用转换字符串为日期时间.
	 */
	public static Date parseDate(String isDateString) {
		Date tcDate = null;
		if (isDateString != null && !isDateString.equals("")) {
			if (isDateString.contains(" ")) {
				if (isDateString.contains(":")) {
					// 包含时间
					try {
						SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						tcDate = tcDateFormat.parse(isDateString);
						return tcDate;
					}
					catch (Exception e) {

					}
					try {
						SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						tcDate = tcDateFormat.parse(isDateString);
						return tcDate;
					}
					catch (Exception e) {

					}
				}
				else {
					// 不包含时间
					try {
						SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						tcDate = tcDateFormat.parse(isDateString);
						return tcDate;
					}
					catch (Exception e) {

					}
				}
			}
			else {
				try {
					SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					tcDate = tcDateFormat.parse(isDateString);
					return tcDate;
				}
				catch (Exception e) {

				}

				try {
					SimpleDateFormat tcDateFormat = new SimpleDateFormat("公元yyyy年MM月dd日");
					tcDate = tcDateFormat.parse(isDateString);
					return tcDate;
				}
				catch (Exception e) {

				}

				try {
					SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM");
					tcDate = tcDateFormat.parse(isDateString);
					return tcDate;
				}
				catch (Exception e) {

				}
				try {
					SimpleDateFormat tcDateFormat = new SimpleDateFormat("HH:mm");
					tcDate = tcDateFormat.parse(isDateString);
					return tcDate;
				}
				catch (Exception e) {

				}
			}
		}
		return tcDate;
	}

	/**
	 * 清除所有cookie
	 * 
	 * @param req
	 * @param res
	 */
	public static void clearCookie(HttpServletRequest req, HttpServletResponse res, String name) {
		Cookie[] cookies = req.getCookies();
		if (null == cookies) {
			return;
		}
		for (int i = 0, len = cookies.length; i < len; i++) {
			Cookie cookieRequest = cookies[i];
			if (cookieRequest.getName().equals(name)) {
				Cookie cookie = new Cookie(cookieRequest.getName(), null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				res.addCookie(cookie);
			}
		}
	}

	/**
	 * 把yyyy-mm-dd转化
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseChinaDate(String date) {
		Date time = parseDate(date);
		DateFormat df = new SimpleDateFormat("公元yyyy年MM月dd日");
		return df.format(time);
	}

	public static String longToDataString(long date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(new Date(date * 1000));
	}

	public static String parseDateString(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String parseDateString(String date) {
		Date time = parseDate(date);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(time);
	}

	public static Date getCurrentDate() {
		Date time = new Date();
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.parse(df.format(time));
		}
		catch (Exception e) {
			return time;
		}
	}

	public static Date convertDate(String dateTime) {
		Date time = null;
		try {
			time = parseDate(dateTime);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.parse(df.format(time));
		}
		catch (Exception e) {
			return time;
		}
	}

	/**
	 * 获取当前时间字符串.
	 * 
	 * @return
	 */
	public static String getCurrentDateTimeString() {
		return parseDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 *            传入的日期
	 * @param day
	 *            天数
	 * @return
	 */
	public static String computeDate(String date, int day) {
		Date now = parseDate(date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date(now.getTime() + day * 24 * 60 * 60 * 1000));
	}

	/**
	 * 将unix时间戳转换为特定日期字符串.
	 * 
	 * @param timestampString
	 * @param formats
	 * @return
	 */
	public static String timeStamp2Date(int timeStamp, String formats) {
		long longTimeStamp = Long.valueOf(timeStamp) * 1000;
		Date date = new java.util.Date(longTimeStamp);
		return new java.text.SimpleDateFormat(formats).format(date);
	}

	/**
	 * 
	 * 转换成UNIX时间戳
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int timesTamp(Date date) throws ParseException {

		// 获取系统时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = simpleDateFormat.format(date);
		int timeStemp = (int) (simpleDateFormat.parse(time).getTime() / 1000);

		return timeStemp;

	}

	/**
	 * 根据出生日期算岁数
	 * 
	 * @param date1
	 * @return
	 */
	public static long getAges(String date1) {
		/**
		 * 获取现在时间
		 */
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = formatter.format(currentTime);
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
		}
		catch (Exception e) {
		}
		long age = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000) / 365;
		age = Math.abs(age);
		if (age == 0) {
			++age;
		}
		return age;
	}

	/**
	 * 根据年龄计算出生日期
	 * 
	 * @param age
	 * @return
	 */
	public static String getBirthDay(String age, int type) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = formatter.format(currentTime);
		int year = Integer.parseInt(date2.split("-")[0]);
		year -= Integer.parseInt(age);
		String date = "";
		if (type == 1) {
			date = year + "-01-01";
		}
		else {
			date = year + "-12-31";
		}
		return date;
	}

	/**
	 * 保存异常日志.
	 * 
	 * @param icLogger
	 * @param e
	 */
	public static void setLogError(Logger logger, Exception e) {
		StringBuffer tsError = new StringBuffer();
		StackTraceElement[] tcaMessages = e.getStackTrace();
		tsError.append(e.toString() + "\r\n");
		for (int i = 0; i < tcaMessages.length; i++) {
			StackTraceElement tcElement = tcaMessages[i];
			tsError.append("\r\n");
			tsError.append(tcElement.toString());
		}
		logger.error(tsError.toString());
	}

	/**
	 * 根据时间格式转换
	 * 
	 * @param dateStr
	 * @param formart
	 * @return
	 * @throws ParseException
	 */
	public static String dateUpdate(String dateStr, String formart) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formart);
		if (formart.contains("-")) {
			if (dateStr.contains("/")) {
				dateStr = dateStr.replace("/", "-");
			}
			else if (dateStr.contains("月")) {
				dateStr = dateStr.replace("年", "-").replace("月", "-").replace("日", "");
			}
		}
		if (formart.contains("/")) {
			if (dateStr.contains("-")) {
				dateStr = dateStr.replace("-", "/");
			}
			else if (dateStr.contains("月")) {
				dateStr = dateStr.replace("年", "/").replace("月", "/").replace("日", "");
			}
		}
		if (formart.contains("年") || formart.contains("月") || formart.contains("日")) {
			String[] dateType = null;
			if (dateStr.contains("-")) {
				dateType = dateStr.split("-");
			}
			else if (dateStr.contains("/")) {
				dateType = dateStr.split("-");
			}
			if (formart.contains("年")) {
				if (dateType.length == 3) {
					dateStr = dateType[0] + "年" + dateType[1] + "月" + dateType[2] + "日";
				}
				if (dateType.length == 2) {
					dateStr = dateType[0] + "年" + dateType[1] + "月";
				}
				if (dateType.length == 1) {
					dateStr = dateType[0] + "年";
				}
			}

		}

		if (formart.contains("月") || formart.contains("MM")) {
			Date date = simpleDateFormat.parse(dateStr);
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			dateStr = simpleDateFormat.format(calender.getTime());
		}

		return dateStr;
	}

	/**
	 * 将数字字符串转换成整型数组
	 * 
	 * @param str
	 * @return
	 */
	public static Integer[] getIntArray(String str) {

		String[] sid = str.split(",");
		Integer[] ids = new Integer[sid.length];

		for (int i = 0; i < sid.length; i++) {

			ids[i] = Integer.parseInt(sid[i]);
		}
		return ids;
	}

	/**
	 * 根据时间戳和次数算出速率
	 * 
	 * @param velocity
	 * @param count
	 * @return
	 */
	public static String timeToVelocity(long totalTime, int count) {

		int velocity = (int) (totalTime / count);
		String newVelocity = velocity / 60 + "分钟" + velocity % 60 + "秒";
		return newVelocity;
	}

	/**
	 * 获取随机编码.
	 * 
	 * @return
	 */
	public static String getRandomNameCode() {
		Random random = new Random();
		String code = "";

		for (int i = 0; i < 4; i++) {
			code += random.nextInt(10);
		}

		return code;
	}
}

