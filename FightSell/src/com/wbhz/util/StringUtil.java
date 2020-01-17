package com.wbhz.util;

public class StringUtil {
    private static char UNDERLINE = '_'; //下划线

    /**
     * 判断是串是否为空
     * @param param
     * @return
     */
    public static Boolean isEmpty(String param){
        return (param == null || param.trim().length() == 0)?true:false;
    }

    /**
     * 判断串是否不为空
     * @param param
     * @return
     */
    public static Boolean isNotEmpty(String param){
        return !isEmpty(param);
    }

    /**
     * 字母转小写
     * @param param
     * @return
     */
    public static String toLowerCase(String param){
        if (isEmpty(param)){
            return "";
        }
        return param.toLowerCase();
    }
    /**
     * 将下划线开头的字符串转换为驼峰格式的字符串
     * @param param
     * @return
     */
    public static String underLinetoCamel(String param){
        if (isEmpty(param)){
            return "";
        }
        int len = param.length();
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (UNDERLINE == c){
                if (++i < len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
