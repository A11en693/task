package com.yan.utils.taskutils;

public class StringUtils {

    public static Boolean isBlank(String value){
        if (null==value){
            return true;
        }else{
            if ("".equals(value.trim())){
                return true;
            }
        }
       return false;
    }

    public static Boolean isNotBlank(String value){
        if (null!=value){
            if (!"".equals(value.trim())){
                return true;
            }
        }
        return false;
    }

}
