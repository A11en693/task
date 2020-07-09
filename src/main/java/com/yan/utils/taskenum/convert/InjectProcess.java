package com.yan.utils.taskenum.convert;

import java.lang.reflect.Field;

/**
 * 注入过程接口
 */
public interface InjectProcess {
    /**
     * 注入过程处理方法入口
     * @param paramClazz
     * @param param
     * @param field
     * @param <T>
     */
    <T> void process(Class<T> paramClazz, T param, Field field);
}
