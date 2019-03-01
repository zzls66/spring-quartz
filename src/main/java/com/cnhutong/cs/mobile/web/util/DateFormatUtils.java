/**
 * 
 */
package com.cnhutong.cs.mobile.web.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Date related utilities
 * 
 * @author Downpour
 * @author lute
 */
public abstract class DateFormatUtils {

    public static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000;
    
    /**
     * Get default format date string of today
     * 
     * @return  default format date string of today
     * @see     #format(Date)
     */
    public static String todayAsString() {
        return format(new Date());
    }
    
    /**
     * Get date string of current month of the pattern "yyyy-MM"
     * 
     * @return date string of current month
     * @see    #format(Date, String)
     */
    public static String monthAsString() {
        return format(new Date(), "yyyy-MM");
    }
    
    /**
     * Get date string of next month of the pattern "yyyy-MM"
     * 
     * @return date string of next month
     * @see    #format(Date, String)
     */
    public static String nextMonthAsString() {
        return format(DateUtils.addMonths(new Date(), 1), "yyyy-MM");
    }
    
    /**
     * Format date specified by year, month and day to string with default date
     * format "yyyy-MM-dd"
     * 
     * @param year   the year of the date to be formatted
     * @param month  the month of the date to be formatted
     * @param day    the day of the date to be formatted
     * @return       date string with default date format "yyyy-MM-dd"
     */
    public static String format(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    
    /**
     * Format date specified by year and month to string with the format of "yyyy-MM"
     * 
     * @param year   the year of the date to be formatted
     * @param month  the month of the date to be formatted
     * @return       date string with date format "yyyy-MM"
     */
    public static String format(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
    }
    
    /**
     * Format Date to date string with the default format "yyyy-MM-dd"
     * 
     * @param date  the date to be formated
     * @return      the date string of the pattern "yyyy-MM-dd"
     * @see         SimpleDateFormat#format(Date)
     */
    public static String format(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
          calendar.setTime(date);
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    
    /**
     * Format date to string with specific pattern
     * 
     * @param date     the date to be formatted
     * @param pattern  the string pattern to be applied
     * @return         the date string with specific pattern
     */
    public static String format(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }
    
    /**
     * Get current time milliseconds as a string
     * 
     * @return  the milliseconds string of current time
     */
    public static String getCurrentTimeMillis() {
        return String.valueOf(System.currentTimeMillis());
    }
    
    /**
     * Get current year number
     * 
     * @return  the year number of current year
     * @see     #year(Date)
     */
    public static int year() {
        return year(new Date());
    }
    
    /**
     * Get year number of given date
     * 
     * @param date  the date to get the year number from
     * @return      the year number of given date
     */
    public static int year(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    
    /**
     * Get current year number as string
     * 
     * @return  the year number string of current year
     * @see     #year(Date)
     */
    public static String getYear() {
        return String.valueOf(year(new Date()));
    }
    
    /**
     * Get year number string of given date
     * 
     * @param date  the date to get the year number from
     * @return      the year number string of given date
     */
    public static String getYear(Date date) {
        return String.valueOf(year(date));
    }
    
    /**
     * Get current month number
     * 
     * @return  the month number of current month
     * @see     #month(Date)
     */
    public static int month() {
        return month(new Date());
    }
    
    /**
     * Get month number of given date
     * 
     * @param date  the date to get month number from
     * @return      the month number of given date
     */
    public static int month(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    /**
     * Get current month number as string
     * 
     * @return  the month number string of current month
     * @see     #getMonth(Date)
     */
    public static String getMonth() {
        return getMonth(new Date());
    }
    
    /**
     * Get month number string of given date
     * 
     * @param date  the date to get month number from
     * @return      the month number string of given date
     */
    public static String getMonth(Date date) {
        int month = month(date);
        return month < 10 ? "0" + month : "" + month;
    }
    
    /**
     * Get day number of month of today
     * 
     * @return the day number of month of today
     * @see    #day(Date)
     */
    public static int day() {
        return day(new Date());
    }
    
    /**
     * Get day number of month of specific date
     * 
     * @param date  the date to get day number of month from
     * @return      the day number of month of given date
     */
    public static int day(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
    
    /**
     * Get today's day number of month as string
     * 
     * @return  today's day number string of month
     * @see     #getDay(Date)
     */
    public static String getDay() {
        return getDay(new Date());
    }
    
    /**
     * Get day number of month as string
     * 
     * @param date  the date to get day number of month from
     * @return      the day number string of month
     */
    public static String getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.DATE));
    }
    
    /**
     * Get number of days of in the specified year and month
     * 
     * @param year   the year number of the month to get number of days
     * @param month  the month number of the month to get number of days 
     * @return       the number of days in the month specified by the year and month
     */
    public static int getMonthDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get day name of week in Chinese
     * 
     * @param date  the day to get Chinese day name of week 
     * @return      the Chinese day name of week
     */
    public static String getChineseDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String displayName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.CHINA);
        return displayName.substring(displayName.length() - 1);
    }

    /**
     * Get month display name in Chinese
     * 
     * @param date  the day to get month display name in Chinese
     * @return      the month display name in Chinese
     */
    public static String getChineseMonthName(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.CHINA);
    }

}
