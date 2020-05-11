package com.baoge.springboot.untils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author shaoxubao
 * @Date 2020/5/11 16:55
 */
public class DateUtils {

    public static final String default_patten = "yyyy-MM-dd";
    public static final String long_patten = "yyyy-MM-dd HH:mm:ss";
    public static final String full_patten = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String detail_patten = "yyyy年MM月dd日 HH时mm分";
    public static final String long_filepatten = "yyyyMMddHHmmss";
    public static final String short_filepatten = "yyyyMMdd";

    public DateUtils() {
    }

    public static String getStandarDate() {
        return formatDate((String)null, (Date)null);
    }

    public static String getLongDate() {
        return formatDate("yyyy-MM-dd HH:mm:ss", (Date)null);
    }

    public static String getFullDate() {
        return formatDate("yyyy-MM-dd HH:mm:ss SSS", (Date)null);
    }

    public static String getLongFilepatten() {
        return formatDate("yyyyMMddHHmmss", (Date)null);
    }

    public static String formatDate(String format, Date date) {
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String d = null;
        if (date == null) {
            d = sdf.format(new Date());
        } else {
            d = sdf.format(date);
        }

        return d;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = null;
        if (date == null) {
            d = "";
        } else {
            d = sdf.format(date);
        }

        return d;
    }

    public static String formatDate2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = null;
        if (date == null) {
            d = "";
        } else {
            d = sdf.format(date);
        }

        return d;
    }

    public static String formatDate3(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String d = null;
        if (date == null) {
            d = "";
        } else {
            d = sdf.format(date);
        }

        return d;
    }

    public static String formatDate4(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String d = null;
        if (date == null) {
            d = "";
        } else {
            d = sdf.format(date);
        }

        return d;
    }

    public static boolean isLeapYear(int year) {
        boolean isLeapYear;
        if (year < 1900) {
            isLeapYear = false;
        } else {
            isLeapYear = year % 4 == 0;
        }

        return isLeapYear;
    }

    public static String getDayAdd(String dateStr, int day, String format) {
        if (format == null || format.trim().equals("")) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            Date date = df.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(5, day);
            return df.format(calendar.getTime());
        } catch (ParseException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static String getMonthAdd(String dateStr, int month, String format) {
        if (format == null || format.trim().equals("")) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            Date date = df.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(2, month);
            return df.format(calendar.getTime());
        } catch (ParseException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static String getTimeDelay(int time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = df.parse(getLongDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(12, time);
            return df.format(calendar.getTime());
        } catch (ParseException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static Date getDayAdd(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, day);
        return calendar.getTime();
    }

    public static Date getHourAdd(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(10, hour);
        return calendar.getTime();
    }

    public static Date getMinuteAdd(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(12, minute);
        return calendar.getTime();
    }

    public static Date getMonthAdd(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month);
        return calendar.getTime();
    }

    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        String currentMonth = df.format(cal.getTime());
        return currentMonth;
    }

    public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.add(2, 1);
        cal.add(5, -1);
        String currentMonth = df.format(cal.getTime());
        return currentMonth;
    }

    public static Integer getOffMills(String beginTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date begin = dfs.parse(beginTime);
            Date end = dfs.parse(endTime);
            Long between = (end.getTime() - begin.getTime()) / 1000L;
            return between.intValue();
        } catch (Exception var6) {
            var6.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        Calendar yesterdayStart = Calendar.getInstance();
        yesterdayStart.add(5, -1);
        yesterdayStart.set(11, 0);
        yesterdayStart.set(12, 0);
        yesterdayStart.set(13, 0);
        Calendar yesterdayEnd = Calendar.getInstance();
        yesterdayEnd.add(5, -1);
        yesterdayEnd.set(11, 23);
        yesterdayEnd.set(12, 59);
        yesterdayEnd.set(13, 59);
        String beginTimeStr = DateFormatUtils.format(getHourAdd(yesterdayStart.getTime(), -8), "yyyy-MM-dd'T'HH:mm:ss");
        String endTimeStr = DateFormatUtils.format(getHourAdd(yesterdayEnd.getTime(), -8), "yyyy-MM-dd'T'HH:mm:ss");
        System.out.println(beginTimeStr + "结束:" + endTimeStr);
    }

    public static Integer getOffDays(String beginTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date begin = dfs.parse(beginTime);
            Date end = dfs.parse(endTime);
            Long between = (end.getTime() - begin.getTime()) / 1000L / 3600L / 24L;
            return between.intValue();
        } catch (Exception var6) {
            var6.printStackTrace();
            return 0;
        }
    }

    public static String getLastWorkDay() {
        String theday = getLastDay();
        return theday;
    }

    public static String getDatePart(Date date, String part) {
        if ("y".equalsIgnoreCase(part)) {
            return formatDate("yyyy", date);
        } else if ("M".equals(part)) {
            return formatDate("MM", date);
        } else {
            return "d".equals(part) ? formatDate("dd", date) : formatDate("yyyy-MM-dd", date);
        }
    }

    public static Date getDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(str);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return date;
    }

    public static Date getDateThrowException(String str) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return format.parse(str);
        } catch (Exception var3) {
            throw var3;
        }
    }

    public static Date getDateByString(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;

        try {
            date = format.parse(str);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return date;
    }

    public static String replace(String string, String target, String replacement) {
        return string.replace(target, replacement);
    }

    public static String getDateFormatByString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = sdf.parse(time);
        } catch (ParseException var4) {
            ;
        }

        return date == null ? null : sdf.format(date);
    }

    public static boolean consumptionMess(String time2, String time3) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time1 = sdf.format(new Date());

        try {
            long t1 = sdf.parse(time1).getTime();
            long t2 = sdf.parse(time2).getTime();
            long t3 = sdf.parse(time3).getTime();
            if (t1 >= t2 && t1 <= t3) {
                return true;
            }
        } catch (ParseException var10) {
            var10.printStackTrace();
        }

        return false;
    }

    public static Long getDayZoneLong() {
        return System.currentTimeMillis() / 86400000L * 86400000L - (long) TimeZone.getDefault().getRawOffset();
    }

    public static Long getDayLastLong() {
        return System.currentTimeMillis() / 86400000L * 86400000L - (long)TimeZone.getDefault().getRawOffset() + 86400000L - 1L;
    }

}
