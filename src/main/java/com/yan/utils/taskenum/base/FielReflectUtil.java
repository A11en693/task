package com.yan.utils.taskenum.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FielReflectUtil {
    public static final String GET_STR = "get";
    private static final String IS_STR = "is";
    private static final String SET_STR = "set";
    public static final int METHOD_NAME_MIN_LENGTH = 3;

    public static <T>Object getFieldValue(T t,String fieldName) throws
            NoSuchFieldException,IllegalAccessException,NoSuchMethodException, InvocationTargetException{
        Class<?> clazz = t.getClass();
        Field declareField = clazz.getDeclaredField(fieldName);
        int modifiers = declareField.getModifiers();
        if (Modifier.isPublic(modifiers)){
            return declareField.get(t);
        }else {
            String name = declareField.getName();
            String methodName;
            if (declareField.getType().equals(Boolean.TYPE)){
                methodName = IS_STR;
            }else {
                methodName = GET_STR;
            }
            methodName = buildMethodName(methodName,name);
            Method method = clazz.getMethod(methodName,new Class[ 0 ]);
            return method.invoke(t,new Class[ 0 ]);
        }

    }

    public static <T> void setFieldValue(T t,String fieldName,Object object)throws
            NoSuchFieldException,IllegalAccessException,NoSuchMethodException, InvocationTargetException{
        Class<?> clazz = t.getClass();
        Field declareField = clazz.getDeclaredField(fieldName);
        int modifiers = declareField.getModifiers();
        if (Modifier.isPublic(modifiers)&&!Modifier.isFinal(modifiers)){
            declareField.set(t,object);
        }
        String methodName = SET_STR;
        methodName = buildMethodName(methodName,fieldName);
        Method method = clazz.getMethod(methodName,new Class[]{declareField.getType()});
        method.invoke(t,new Object[]{object});
    }

    private static String buildMethodName(String methodNamePre,String fieldName){
        String dealFieldName = fieldName.substring(1,2);
        if (fieldName.length()>2&&dealFieldName.toUpperCase().equals(dealFieldName)){
            methodNamePre = methodNamePre+fieldName;
        }else {
            methodNamePre = methodNamePre+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
        }

        return methodNamePre;
    }
}
