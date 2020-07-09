package com.yan.utils.taskenum.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 枚举方法json格式化处理类
 * @param <T>
 */
public class MethodEnumJasonFormatter<T> extends JsonSerializer<T> {
    private final static Logger LOG = LoggerFactory.getLogger(MethodEnumJasonFormatter.class);

    @Override
    public void serialize(T item, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        Class<?> clazz = item.getClass();
        Method[] methods = clazz.getMethods();
        int methodsLength = methods.length;
        for (int value=0;value<methodsLength;++value){
            Method method = methods[value];
            if (!method.getDeclaringClass().equals(Object.class)){
                String clazzName = method.getName();
                if (clazzName.length()>FielReflectUtil.METHOD_NAME_MIN_LENGTH && clazzName.startsWith(FielReflectUtil.GET_STR)){
                    clazzName = clazzName.substring(FielReflectUtil.METHOD_NAME_MIN_LENGTH,clazzName.length());
                    String firstStr = String.valueOf(clazzName.charAt(0));
                    clazzName = clazzName.replaceFirst(firstStr,firstStr.toLowerCase());
                }
                try {
                    Field declareField = clazz.getDeclaredField(clazzName);
                    EnumValue annotztionEnumVlue = (EnumValue)declareField.getAnnotation(EnumValue.class);
                    String code = declareField.getName();
                    Object object = FielReflectUtil.getFieldValue(item,code);
                    if (null != annotztionEnumVlue){
                        Class<?extends BaseEnum> enumClazz = annotztionEnumVlue.enumClass();
                        BaseEnum[] baseEnums = (BaseEnum[]) enumClazz.getEnumConstants();
                        int baseEnumsLength = baseEnums.length;
                        for (int baseLength=0;baseLength<baseEnumsLength;++baseLength){
                            BaseEnum baseEnum = baseEnums[baseLength];
                            if (baseEnum.getCode().equals(object)){
                                jsonGenerator.writeObjectField(code+annotztionEnumVlue.suffix(),baseEnum.getDesc());
                            }
                        }
                    }
                    jsonGenerator.writeObjectField(code,object);

                }catch (Exception e){
                    if (e instanceof NoSuchFieldException){
                        //此异常类型不打印信息，因为此处是因为找不到未被EnumValue注解的字段属性信息，不算错误
                    }else {
                        LOG.error("枚举方法json格式化处理类发生异常:{}",e);
                    }

                }
            }

        }
        jsonGenerator.writeEndObject();
    }
}
