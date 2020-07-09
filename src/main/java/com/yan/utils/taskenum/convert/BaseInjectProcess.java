package com.yan.utils.taskenum.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class BaseInjectProcess {
    private final static Logger LOG = LoggerFactory.getLogger(BaseInjectProcess.class);
    protected static Map<String,InjectProcess> injectProcessMap;

    public abstract void init();

    private static boolean isInit(){ return (null != injectProcessMap);}

    private static InjectProcess getInjectProcess(String beanName){
        if (!isInit()){
            return  null;
        }
        return injectProcessMap.get(beanName);
    }

    public static <T> void inject(Class<T> clazz,T t){
        if (isInit()){
            Field[] declareFields = clazz.getDeclaredFields();
            for (Field declareField:declareFields){
                InjectField injectField = declareField.getAnnotation(InjectField.class);
                if (null != injectField){
                    String[] beanNames = injectField.processBeanName();
                    for (String beanName:beanNames){
                        InjectProcess injectProcess = getInjectProcess(beanName);
                        if (null != injectProcess){
                            try {
                                injectProcess.process(clazz,t,declareField);
                            }catch (Exception e){
                              //异常信息打印
                                LOG.error("注入发生异常:{}",t,e);
                            }
                        }
                    }
                }
            }

        }
    }


}
