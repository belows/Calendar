package com.belows.calendar.util;

/**
 * Created by belows on 15/6/12.
 */
public class StringUtils {
    public static boolean equals(String pStr1, String pStr2) {
        if (pStr1 == null || pStr2 == null) {
            return false;
        }
        return pStr1.equals(pStr2);
    }
}
