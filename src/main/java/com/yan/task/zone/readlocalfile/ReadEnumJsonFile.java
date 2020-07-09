package com.yan.task.zone.readlocalfile;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 获取本地的json文件数据，但是通用性太差，后续改造
 */
public class ReadEnumJsonFile {

    private final static String CODE_METHOD = "getCode";
    private final static String DESC_METHOD = "getDesc";

    /**
     *
     * @param value 枚举类名称
     * @return 枚举值数组
     */
    public static List<Map<String, Object>> localEnum(String value){
        try {
            //获取项目本地json文件资源
            ClassPathResource classPathResource = new ClassPathResource("Enum.json");
            //获取文件
            File file = classPathResource.getFile();
            //将文件转成字符串
            String fileStr = FileUtils.readFileToString(file);
            //字符串对象转json对象，以便后边解析
            JSONObject jsonObject = JSONObject.parseObject(fileStr);
            Map<String, Object> data =new HashMap<>();
            //循环转换
            Iterator it =jsonObject.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                data.put(entry.getKey(), entry.getValue());
            }
            //获取枚举类路径
            String enumPath = data.get(value).toString();
            //反射获取枚举类
            Class<?> clazz = Class.forName(enumPath);
            //返回枚举类的数据，此处就不做校验--判断拿到的对象是否枚举对象
            Object[] objects = clazz.getEnumConstants();
            //获取方法
            Method methodCode = clazz.getMethod(CODE_METHOD);
            //获取方法
            Method methodDesc = clazz.getMethod(DESC_METHOD);
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> valueMap = null;
            for (Object obj : objects) {
                valueMap = new HashMap<>();
                valueMap.put("code", methodCode.invoke(obj));
                valueMap.put("desc", methodDesc.invoke(obj));
                list.add(valueMap);
            }
           return list;
        }catch (Exception e){

        }
      return null;
    }
}
