package com.yan.utils.taskenum.convert;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象转换工具类
 */
public class GeneralConv {

    private static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    /**
     * 单个对象转换
     * @param source
     * @param destinationClazz
     * @param <T>
     * @return
     */
    public static <T> T conv(Object source,Class<T> destinationClazz){
        if (null!=source){
            T convResource = dozerBeanMapper.map(source,destinationClazz);
            BaseInjectProcess.inject(destinationClazz,convResource);
            return convResource;
        }else {
            return null;
        }
    }

    /**
     * 集合对象转换
     * @param sourceList
     * @param destinationClazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convList(List<?> sourceList, Class<T> destinationClazz){
        List<T> destinationList = new ArrayList<>();
        sourceList.forEach(value->{
            T conv = GeneralConv.conv(value,destinationClazz);
            destinationList.add(conv);
        });
        return  destinationList;

    }

}
