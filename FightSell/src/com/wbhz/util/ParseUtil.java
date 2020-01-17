package com.wbhz.util;

public class ParseUtil {
    /**
     * 将object的变量转化为Integer
     * @param object
     * @return
     */
    public static Integer parseIntFromObj(Object object){
        return Integer.parseInt(String.valueOf(object));
    }

    /**
     * 将object的变量转化为String
     * @param object
     * @return
     */
    public static String parseStringFromObj(Object object){
        return String.valueOf(object);
    }
}
