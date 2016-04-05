package com.mobanker.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <b>Package Name:</b> com.mobanker.util <br/>
 * <b>Description:</b>〈日期操作工具类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils
{

    protected final static Logger logger                      = Logger.getLogger(DateUtils.class);

    public static final String    DATE_SHORT_FORMAT           = "yyyyMMdd";
    public static final String    DATE_TIME_SHORT_FORMAT      = "yyyyMMddHHmm";
    public static final String    DATE_TIMESTAMP_SHORT_FORMAT = "yyyyMMddHHmmss";
    public static final String    DATE_TIMESTAMP_LONG_FORMAT  = "yyyyMMddHHmmssS";
    public static final String    DATE_CH_FORMAT              = "yyyy年MM月dd日";

    public static final String    DATE_TIME_FORMAT            = "yyyy-MM-dd HH:mm:ss";
    public static final String    TIMESTAMP_FORMAT            = "yyyy-MM-dd HH:mm:ss.S";
    public static final String    DATE_FORMAT                 = "yyyy-MM-dd";
    public static final String    TIME_FORMAT                 = "HH:mm:ss";
    public static final String    TIME_SHORT_FORMAT           = "HHmmss";

    public static final String    DAYTIME_START               = "00:00:00";
    public static final String    DAYTIME_END                 = "23:59:59";

    private DateUtils()
    {
    }

    private static final String[] FORMATS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "HH:mm",
            "HH:mm:ss", "yyyy-MM", "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss" };

    public static Date convert(String str)
    {
        if (str != null && str.length() > 0)
        {
            if (str.length() > 10 && str.charAt(10) == 'T')
            {
                str = str.replace('T', ' '); // 鍘绘帀json-lib鍔犵殑T瀛楁瘝
            }
            for (String format : FORMATS)
            {
                if (str.length() == format.length())
                {
                    try
                    {
                        Date date = new SimpleDateFormat(format).parse(str);
                        return date;
                    }
                    catch (ParseException e)
                    {
                        if (Level.WARN.isGreaterOrEqual(logger.getEffectiveLevel()))
                        {
                            logger.warn(e.getMessage(), e);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static Date convert(String str, String format)
    {
        if (!StringUtils.isEmpty(str))
        {
            try
            {
                Date date = new SimpleDateFormat(format).parse(str);
                return date;
            }
            catch (ParseException e)
            {
                if (Level.WARN.isGreaterOrEqual(logger.getEffectiveLevel()))
                {
                    logger.warn(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public static Date concat(String ymd, String hm)
    {
        if (!StringUtils.isEmpty(ymd) && !StringUtils.isEmpty(hm))
        {
            try
            {
                String dateString = ymd.concat(" ").concat(
                        hm.substring(0, 2).concat(":").concat(hm.substring(2, 4)).concat(":00"));
                Date date = DateUtils.convert(dateString, DateUtils.DATE_TIME_FORMAT);
                return date;
            }
            catch (NullPointerException e)
            {
                if (Level.WARN.isGreaterOrEqual(logger.getEffectiveLevel()))
                {
                    logger.warn(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public static String getDay(Date date)
    {
        return convert(date, DATE_SHORT_FORMAT);
    }

    public static String getChDate(Date date)
    {
        return convert(date, DATE_CH_FORMAT);
    }

    public static Date strToDate(String dateStr)
    {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = null;
        try
        {
            date = formatDate.parse(dateStr);
        }
        catch (Exception e)
        {

        }
        return date;
    }

    public static String convert(Date date)
    {
        return convert(date, DATE_TIME_FORMAT);
    }

    public static String convert(Date date, String dateFormat)
    {
        if (date == null)
        {
            return null;
        }

        if (null == dateFormat)
        {
            dateFormat = DATE_TIME_FORMAT;
        }

        return new SimpleDateFormat(dateFormat).format(date);
    }

    public static Date getStartDatetime(Date date)
    {
        String thisdate = convert(date, DATE_FORMAT);
        return convert(thisdate + " " + DAYTIME_START);

    }

    public static Date getStartDatetime(Date date, Integer diffDays)
    {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String thisdate = df.format(date.getTime() + 1000l * 24 * 60 * 60 * diffDays);
        return convert(thisdate + " " + DAYTIME_START);
    }

    public static Date getEndDatetime(Date date)
    {
        String thisdate = convert(date, DATE_FORMAT);
        return convert(thisdate + " " + DAYTIME_END);

    }

    public static Date getEndDatetime(Date date, Integer diffDays)
    {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String thisdate = df.format(date.getTime() + 1000l * 24 * 60 * 60 * diffDays);
        return convert(thisdate + " " + DAYTIME_END);

    }

    public static Timestamp getLastEndDatetime(Date endTime)
    {
        Timestamp ts = new Timestamp(endTime.getTime());
        ts.setNanos(999999999);
        return ts;
    }

    public static Timestamp getEndTimeAdd(Date endTime)
    {
        Timestamp ts = new Timestamp(endTime.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(ts);
        c.add(Calendar.MILLISECOND, 1000);
        c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    public static String addDay(Date date, int day)
    {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

        return df.format(new Date(date.getTime() + 1000l * 24 * 60 * 60 * day));
    }

    public static Date addDayToDate(Date date, long day)
    {
        return new Date(date.getTime() + 1000 * 24 * 60 * 60 * day);
    }

    public static Long getDayDiff(String startTime, String endTime)
    {
        Long days = null;
        Date startDate = null;
        Date endDate = null;
        try
        {
            if (startTime.length() == 10 && endTime.length() == 10)
            {
                startDate = new SimpleDateFormat(DATE_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_FORMAT).parse(endTime);
            }
            else
            {
                startDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(endTime);
            }

            days = getDayDiff(startDate, endDate);
        }
        catch (ParseException e)
        {
            if (Level.WARN.isGreaterOrEqual(logger.getEffectiveLevel()))
            {
                logger.warn(e.getMessage());
            }
            days = null;
        }
        return days;
    }

    public static Long getDayDiff(Date startTime, Date endTime)
    {
        Long days = null;

        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long l_e = c.getTimeInMillis();
        days = (l_e - l_s) / 86400000;
        return days;
    }

    public static Long getMinuteDiff(Date startTime, Date endTime)
    {
        Long minutes = null;

        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        long l_e = c.getTimeInMillis();
        minutes = (l_e - l_s) / (1000l * 60);
        return minutes;
    }

    public static Long getSecondDiff(Date startTime, Date endTime)
    {
        Long minutes = null;
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        long l_e = c.getTimeInMillis();
        minutes = (l_e - l_s) / 1000l;
        return minutes;
    }

    public static Long getSecondDiff(String startTime, String endTime)
    {
        Long seconds = null;
        Date startDate = null;
        Date endDate = null;
        try
        {
            if (startTime.length() == 10 && endTime.length() == 10)
            {
                startDate = new SimpleDateFormat(DATE_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_FORMAT).parse(endTime);
            }
            else
            {
                startDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(endTime);
            }

            seconds = getSecondDiff(startDate, endDate);
        }
        catch (ParseException e)
        {
            if (Level.WARN.isGreaterOrEqual(logger.getEffectiveLevel()))
            {
                logger.warn(e.getMessage());
            }
            seconds = null;
        }
        return seconds;
    }

    public static String getPidFromDate(Date date)
    {
        if (date == null)
        {
            return "";
        }

        String m = convert(date, "yyyyMM");
        String d = convert(date, "dd");

        if (Integer.valueOf(d) <= 10)
        {
            d = "01";
        }
        else if (Integer.valueOf(d) <= 20)
        {
            d = "02";
        }
        else
        {
            d = "03";
        }

        return m.concat(d);
    }

}
