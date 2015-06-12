package com.belows.calendar.util;

import java.text.SimpleDateFormat;

/**
 * Created by belows on 15/6/12.
 */
public class CalendarUtil {

    public static final ChineseCalendar TODAY = new ChineseCalendar();

    public static final SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isSameDay(ChineseCalendar pCalendar1, ChineseCalendar pCalendar2) {
        return StringUtils.equals(getSimpleDay(pCalendar1), getSimpleDay(pCalendar2));
    }

    public static String getSimpleDay(ChineseCalendar pCalendar) {
        return sSimpleDateFormat.format(pCalendar.getTime());
    }

    public static boolean isToday(ChineseCalendar pCalendar) {
        return isSameDay(pCalendar, TODAY);
    }
}
