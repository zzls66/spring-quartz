package com.zzl.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class DateUtil {

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat yMFormat = new SimpleDateFormat("yyyy-MM");
	static SimpleDateFormat hanDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

	private static final int FIRST_DAY = Calendar.MONDAY;

	/** 某个月的最后一个星期天 */
	public static String getMonth_LastSUNDAY(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(yMFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return dateFormat.format(cal.getTime());
	}

	/** 某个月的第一个星期天 */
	public static String getMonth_firstSUNDAY(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(yMFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		return dateFormat.format(cal.getTime());
	}

	// 本周的周一
	public static long getMonday(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		// System.out.println("所在周星期一的日期："+dateFormat.format(cal.getTime()));
		return cal.getTime().getTime();
	}

	// 本周的周一
	public static String getMondayStr(Date time, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		// System.out.println("所在周星期一的日期："+dateFormat.format(cal.getTime()));
		return format(cal.getTime(), format);
	}

	// 本周的周末
	public static long getSunday(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DATE, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, 6);
		System.out.println("所在周星期日的日期：" + dateFormat.format(cal.getTime()));

		return cal.getTime().getTime();
	}

	// 本周的周末
	public static String getSunday(Date time, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DATE, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, 6);
		// System.out.println("所在周星期日的日期："+dateFormat.format(cal.getTime()));

		return format(cal.getTime(), format);
	}

	// 当前日期的周一 ——周末的日期
	public static String getWeekStr(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// System.out.println("要计算日期为:"+dateFormat.format(cal.getTime()));
		// //输出要计算日期

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		// System.out.println("所在周星期一的日期："+dateFormat.format(cal.getTime()));
		// System.out.println(cal.getFirstDayOfWeek()+"-"+day+"+6="+(cal.getFirstDayOfWeek()-day+6));
		String mon = hanDateFormat.format(cal.getTime());
		cal.add(Calendar.DATE, 6);
		// System.out.println("所在周星期日的日期："+dateFormat.format(cal.getTime()));

		String str = mon + "——" + hanDateFormat.format(cal.getTime());

		return str;

	}

	// 获取本周的周一-周末的日期
	public static List<String> printWeekdays() {
		Calendar calendar = Calendar.getInstance();
		setToFirstDay(calendar);
		List<String> weeks = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			printDay(calendar);
			calendar.add(Calendar.DATE, 1);
			weeks.add(printDay(calendar));
		}
		return weeks;
	}

	public static void setToFirstDay(Calendar calendar) {
		while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	public static String printDay(Calendar calendar) {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd EE");
		// System.out.println(dateFormat.format(calendar.getTime()));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/***
	 * 
	* 方法说明:判断当前时间跟某一时间是否为同一周几
	* @Title: isWeekOfDate
	* @param dt
	* @return 
	* @see 传入参数
	* @see 返回参数
	* @author ht_yanghongyan
	* @date 2017年5月24日 下午5:21:26 
	* @updateAuthor 
	* @updateTime 
	* @updateReason
	 */
	public static boolean isWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		Calendar cal1 = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_WEEK) == cal1.get(Calendar.DAY_OF_WEEK)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* 方法说明:判断当前时间跟某一时间在是否都在一月的某一天
	* @Title: isMonthOfDate
	* @param dt
	* @return 
	* @see 传入参数
	* @see 返回参数
	* @author ht_yanghongyan
	* @date 2017年5月24日 下午5:31:33 
	* @updateAuthor 
	* @updateTime 
	* @updateReason
	 */
	public static boolean isMonthOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		Calendar cal1 = Calendar.getInstance();
		if (cal.get(Calendar.DATE) == cal1.get(Calendar.DATE)) {
			return true;
		}
		return false;
	}
	
	//1-周一，2-周二。。 7-周日
	public static int getWeekDayOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w <= 0) {
			w = 7;

		}
		return w;
	}

	public static int getWeekDayOfDate(Calendar cal) {
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w <= 0) {
			w = 7;

		}
		return w;
	}
	
	public static int getWeekDayOfDate2(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w <= 0) {
			w = 0;

		}
		return w;
	}
	

	/**
	 * 方法说明:获取当前日期属于本月的第几周
	 */
	public static int getMonthWeekNumOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		// System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 方法说明:获取当前日期属于本月的第几周
	 */
	public static int getMonthWeekNumOfStr(String today) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		int res = 0;
		try {
			date = format.parse(today);
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(date);
			res = calendar.get(Calendar.WEEK_OF_MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;

		}
		// System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
		return res;
	}

	/**
	 * 方法说明:两个日期分钟差
	 * 
	 * @Title: dateMinuteDiff
	 * @param one
	 * @param two
	 * @return
	 * @see 传入参数
	 * @see 返回参数
	 * @author ht_zhouxiuhong
	 * @date 2016年10月11日 上午11:29:02
	 * @updateAuthor
	 * @updateTime
	 * @updateReason
	 */
	public static long dateMinuteDiff(Date one, Date two) {
		// System.out.println(DateUtil.format(one,
		// "yyyy-MM-dd HH:mm:ss")+"//"+DateUtil.format(two,
		// "yyyy-MM-dd HH:mm:ss"));
		Calendar dateOne = Calendar.getInstance(), dateTwo = Calendar
				.getInstance();
		dateOne.setTime(one);
		dateTwo.setTime(two);
		long timeOne = dateOne.getTimeInMillis();
		long timeTwo = dateTwo.getTimeInMillis();
		long minute = (timeOne - timeTwo) / (1000 * 60);// 转化minute
		// System.out.println("相隔"+minute+"分钟");
		return minute;

	}

	public static long DateTimeInMillis(Date one) {
		Calendar dateOne = Calendar.getInstance();
		dateOne.setTime(one);
		long timeOne = dateOne.getTimeInMillis();
		return timeOne;

	}

	/**
	 * 方法说明:两个时间差
	 * 
	 * @Title: dateDiff
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return
	 * @throws Exception
	 * @see 传入参数
	 * @see 返回参数
	 * @author ht_zhouxiuhong
	 * @date 2016年10月11日 上午11:07:57
	 * @updateAuthor
	 * @updateTime
	 * @updateReason
	 */
	public static String dateDiff_min(String startTime, String endTime,
			String format) throws Exception {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;

		// 获得两个时间的毫秒时间差异
		diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		System.out.println("=" + diff / (60 * 1000) % 60);
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果
		System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec
				+ "秒。");

		return "时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。";
	}

	/**
	 * @param date是为则默认今天日期
	 *            、可自行设置“2013-06-03”格式的日期
	 * @return 返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
	 */

	public static int getDayofweek(String date) {
		Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date(System.currentTimeMillis()));
		if (date.equals("")) {
			cal.setTime(new Date(System.currentTimeMillis()));
		} else {
			cal.setTime(new Date(StrToDate(date).getTime()));
		}
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = null;

		try {

			date = format.parse(str);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return date;
	}

	public static Date StrToDate(String str, String format1) {

		SimpleDateFormat format = new SimpleDateFormat(format1);

		Date date = null;

		try {

			date = format.parse(str);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return date;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 
	 * @Title: addOrMinusMinute
	 * @Description: TODO(分钟加减)
	 * @param @param dt
	 * @param @param minute
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String addOrMinusMinute(Date dt, int minute) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		/*
		 * rightNow.add(Calendar.YEAR,-1);//日期减1年
		 * rightNow.add(Calendar.MONTH,3);//日期加3个月
		 * rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
		 */rightNow.add(Calendar.MINUTE, minute); // 分钟
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	/**
	 * 
	 * @Title: addOrMinusHour
	 * @Description: TODO(小时加减)
	 * @param @param dt
	 * @param @param hour
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String addOrMinusHour(Date dt, int hour) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.HOUR, hour);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	/**
	 * 
	 * @Title: addOrMinusDay
	 * @Description: TODO(日期加减)
	 * @param @param dt
	 * @param @param day
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String addOrMinusDay(Date dt, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_YEAR, day); // 日期加30天
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	/**
	 * 
	 * @Title: addOrMinusDay
	 * @Description: TODO(日期加减)
	 * @param @param dt
	 * @param @param day
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static Date addOrMinusDayDate(Date dt, int day) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_YEAR, day); // 日期加30天
		return rightNow.getTime();
	}
	
	public static String addOrMinusDay(Date dt, int day, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_YEAR, day); // 日期加30天
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	public static String addOrMinusDay(String dt, int day, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(StrToDate(dt, format));
		rightNow.add(Calendar.DAY_OF_YEAR, day); // 日期加30天
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	public static String addOrMinusMonth(Date dt, int day, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.MONTH, day); // 日期加30天
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	public static Date addOrMinusMonth_Date(Date dt, int day) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.MONTH, day); // 日期加30天
		Date dt1 = rightNow.getTime();
		// Calendar calendar = new GregorianCalendar();
		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.MONTH, -2);
		// SimpleDateFormat formatter_shuzi = new SimpleDateFormat("yyyyMM");
		// String mdatetimeshuzi=formatter_shuzi.format(cal.getTime());
		// System.out.print(mdatetimeshuzi);
		return dt1;
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	/**
	 * 方法说明:年份加减
	 * 
	 * @Title: addOrMinusYear
	 * @param dt
	 * @param day
	 * @param dateFormat
	 * @return
	 * @see 传入参数
	 * @see 返回参数
	 * @author ht_zhouxiuhong
	 * @date 2016年9月1日 下午5:11:09
	 * @updateAuthor
	 * @updateTime
	 * @updateReason
	 */
	public static String addOrMinusYear(Date dt, int day, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, day);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}
	
	
	public static Date addOrMinusYear(Date dt, int day) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, day);
		return rightNow.getTime();
	}

	/**
	 * 
	 * @Title: format
	 * @Description: TODO(日期转 指定格式的 string)
	 * @param @param date
	 * @param @param format
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sft = new SimpleDateFormat(format);
		return sft.format(date);
	}

	public static long addMinutes(Date one, Date two) {
		if (one == null) {
			return 0;
		}
		if (two == null) {
			return 0;
		}
		Calendar from = Calendar.getInstance();
		from.setTime(one);
		Calendar to = Calendar.getInstance();
		to.setTime(two);
		//
		// System.out.println(Math.abs((from.getTimeInMillis() - to
		// .getTimeInMillis())));
		return Math.abs((from.getTimeInMillis() - to.getTimeInMillis())
				/ (1000 * 60));
	}

	/**
	 * 判断传入字符是否是日期格式(yyyy-MM-dd)
	 * 
	 * @Title: isDateStringValid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param date
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isDateStringValid(String date) {
		boolean isValidDateStr = false;
		String yyyyMMddFmt = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern p = Pattern.compile(yyyyMMddFmt);
		if (p.matcher(date).matches()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(date);
				isValidDateStr = true;
			} catch (ParseException parseExp) {
			}
		}
		return isValidDateStr;
	}

	/**
	 * 判断传入字符是否是日期格式(yyyy-MM-dd hh:mm:ss)
	 * 
	 * @Title: isDateStringValidYMDHMS
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param date
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isDateStringValidYMDHMS(String date) {
		boolean isValidDateStr = false;
		String yyyyMMddFmt = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
		Pattern p = Pattern.compile(yyyyMMddFmt);
		if (p.matcher(date).matches()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				sdf.parse(date);
				isValidDateStr = true;
			} catch (ParseException parseExp) {
			}
		}
		return isValidDateStr;
	}

	/**
	 * 判断传入字符是否是日期格式(yyyy-yyyy)
	 * 
	 * @Title: isDateStringValid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param date
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isSearchDateStringValid(String date) {
		boolean isValidDateStr = false;
		String yyyyMMddFmt = "[0-9]{4}-[0-9]{4}";
		Pattern p = Pattern.compile(yyyyMMddFmt);
		if (p.matcher(date).matches()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-yyyy");
			try {
				sdf.parse(date);
				isValidDateStr = true;
			} catch (ParseException parseExp) {
			}
		}
		return isValidDateStr;
	}

	/**
	 * 
	 * @Title: nextMonthFirstDate
	 * @Description: TODO(获取日期的下一月)
	 * @param @param date
	 * @param @return
	 * @param @throws ParseException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String nextMonthFirstDate(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());

	}

	public static String nextMonthFirstDate(Date date, String format)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());

	}

	public static void printDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
	}

	/**
	 * 获取日期年份
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getYear(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @Title: getYearMonth
	 * @Description: TODO(获取日期年月)
	 * @param @param date
	 * @param @return
	 * @param @throws ParseException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getYearMonth(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1);
	}

	public static String getYearMonth() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取日期月份
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getMonth(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		return (calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取日期月份
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getMonth(Date date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取日期号
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getDay(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月份起始日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMinMonthDate(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取月份最后日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMaxMonthDate(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获取月份最后日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMaxMonthDate(Date date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	public static Boolean checkDate(String inputDate, String format)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return inputDate.equals(sdf.format(sdf.parse(inputDate)));
	}

	/**
	 * 
	 * @Title: getOldMonthfirstDay
	 * @Description: TODO(获取上月第一天)
	 * @param @return
	 * @param @throws ParseException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getOldMonthfirstDay() {
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = dateFormat.format(cal_1.getTime());
		// System.out.println("-----1------firstDay:"+firstDay);
		return firstDay;
	}

	/**
	 * 
	 * @Title: getOldMonthlastDay
	 * @Description: TODO(获取上月最后一天)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getOldMonthlastDay() {
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String lastDay = dateFormat.format(cale.getTime());
		// System.out.println("-----2------lastDay:"+lastDay);
		return lastDay;
	}

	/**
	 * 
	 * @Title: getMonthfirstDay
	 * @Description: TODO(获取当前月第一天)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getMonthfirstDay() {
		// 获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = dateFormat.format(c.getTime());
		// System.out.println("===============first:"+first);
		return first;
	}

	/**
	 * 
	 * @Title: getMonthlastDay
	 * @Description: TODO(获取当前月最后一天)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getMonthlastDay() {
		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = dateFormat.format(ca.getTime());
		// System.out.println("===============last:"+last);
		return last;
	}

	/**
	 * 
	 * @Title: getHour
	 * @Description: TODO 返回小时
	 * @return
	 * @throws
	 */
	public static int getHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 
	 * @Title: getMinute
	 * @Description: TODO 返回分钟
	 * @return
	 * @throws
	 */

	public static int getMinute() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}

	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 
	 * @Title: compare_date
	 * @Description: TODO(比较日期大小)
	 * @param @param DATE1
	 * @param @param DATE2
	 * @param @return 设定文件
	 * @return int 返回类型 （0：日期相等 1：date1大于date2 -1： date2大于date1）
	 * @throws
	 */
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;

			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 
	 * @Title: compare_date
	 * @Description: TODO(比较日期大小)
	 * @param @param DATE1
	 * @param @param DATE2
	 * @param @return 设定文件
	 * @return int 返回类型 （0：日期相等 1：date1大于date2 -1： date2大于date1）
	 * @throws
	 */
	public static int compare_date_day(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
				
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static String getStrDateForYYYYMMDDHHMMSS(Date date) {
		String result = "";
		if (date == null) {
			return result;
		}
		SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		result = l_sdf.format(date);
		return result;
	}

	/**
	 * 方法说明:某个月有多少周
	 * 
	 * @Title: getMonthWeek
	 * @param date
	 * @return
	 * @throws Exception
	 * @see 传入参数
	 * @see 返回参数
	 * @author ht_zhouxiuhong
	 * @date 2016年12月9日 下午5:58:57
	 * @updateAuthor
	 * @updateTime
	 * @updateReason
	 */
	public static int getMonthWeek(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date s = sdf.parse(date);
		Calendar ca = Calendar.getInstance();
		ca.setTime(s);
		ca.setFirstDayOfWeek(Calendar.MONDAY);
		// System.out.println(ca.getActualMaximum(Calendar.WEEK_OF_MONTH));
		return ca.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 方法说明:查看某个月有几周
	 * 
	 * @Title: getMonthWeek
	 * @param s
	 * @return
	 * @throws Exception
	 * @see 传入参数
	 * @see 返回参数
	 * @author ht_zhouxiuhong
	 * @date 2016年12月9日 下午6:00:41
	 * @updateAuthor
	 * @updateTime
	 * @updateReason
	 */
	public static int getMonthWeek(Date s) throws Exception {
		Calendar ca = Calendar.getInstance();
		ca.setTime(s);
		ca.setFirstDayOfWeek(Calendar.MONDAY);
		// System.out.println(ca.getActualMaximum(Calendar.WEEK_OF_MONTH));
		return ca.getActualMaximum(Calendar.WEEK_OF_MONTH);

	}

	public static void gettime() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017); // 2016年
		cal.set(Calendar.WEEK_OF_YEAR, 2); // 设置为2016年的第10周
		cal.set(Calendar.DAY_OF_WEEK, 2); // 1表示周日，2表示周一，7表示周六
		Date date = cal.getTime();

		System.out.println(dateFormat.format(date));

	}

	/**
	 * 获取月日
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthDay(String date) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		String str = (calendar.get(Calendar.MONTH) + 1) + "月" + (getDay(date))
				+ "号";
		return str;
	}

	// 获取月第一天：
	public static String getMonthfirstDay(Calendar c) {

		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = dateFormat.format(c.getTime());
		// System.out.println("===============first:"+first);
		return first;
	}

	/** 根据某天、周几、时间来确定后面的时间 */
	public static Date getTime(Date date, int dayOfWeek, Date time) {

		try {
			// 获取指定日期的周一日期
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			cal1.setFirstDayOfWeek(Calendar.MONDAY);
			cal1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			// 获取指定周几的日期 +0-周一 +1-周二 +2-周三 +3-周四 +4-周五 +5-周六 +6-周日
			cal1.add(Calendar.DAY_OF_WEEK, dayOfWeek);

			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df3 = new SimpleDateFormat("HH:mm:ss");
			String s2 = df2.format(cal1.getTime());
			String s3 = df3.format(time);
			String s1 = s2 + " " + s3;
			return df1.parse(s1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
		 
		/*//获取指定时间
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(time); 
		System.out.println(cal2.getTime().getTime());

		//组合指定日期的年月日 和 指定时间的时分秒
		Calendar cal = Calendar.getInstance();
		cal.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH),
				cal1.get(Calendar.DAY_OF_MONTH),
				cal2.get(Calendar.HOUR_OF_DAY), cal2.get(Calendar.MINUTE),
				cal2.get(Calendar.SECOND));
		
		
		return cal.getTime();*/

	}
	
	public static int compareTime(Date time1, Date time2) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			Date dt1 = df.parse(df.format(time1)); 
			Date dt2 = df.parse(df.format(time2)); 
			if (dt1.getTime() > dt2.getTime()) {
				return 1;

			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static boolean isSaturday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return dayOfWeek == 6;
	}

	public static boolean isMonday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return dayOfWeek == 1;
	}
	public static long diffMinutes(Date startTime,Date endTime){
		long nm = 1000 * 60;// 一分钟的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = endTime.getTime() - startTime.getTime();
		long min = diff / nm;// 计算差多少分钟
		return min;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int monthsBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		int y0 = cal.get(Calendar.YEAR);
		int m0 = cal.get(Calendar.MONTH);
		cal.setTime(bdate);
		int y1 = cal.get(Calendar.YEAR);
		int m1 = cal.get(Calendar.MONTH);
		return (y1 - y0) * 12 + m1 - m0;
	}
}
