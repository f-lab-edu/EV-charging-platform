package com.shyb.evcharging.utils;

public class CustomStringUtils {

    /**
     * 동일 문자열 체크합니다.
     *
     * @param a 문자열 a
     * @param b 문자열 b
     * @return 동일 여부를 리턴합니다.
     */
    public static boolean isSameString(String a, String b) {
        if (a.equals(b)) {
            return true;
        } else {
            return false;
        }
    }

}
