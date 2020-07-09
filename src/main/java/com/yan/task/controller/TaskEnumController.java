package com.yan.task.controller;


import com.alibaba.fastjson.JSON;
import com.yan.task.zone.readlocalfile.ReadEnumJsonFile;
import com.yan.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("enum")
public class TaskEnumController {

    private final static Logger LOG = LoggerFactory.getLogger(TaskEnumController.class);

    @GetMapping("/taskenum/{enumName}")
    public Result<List<Map<String, Object>>> taskEnum(@PathVariable(value = "enumName") String enumName){
        LOG.info("获取枚举数据传入参数:{}",enumName);
        Result<List<Map<String, Object>>> result = new Result<>();
        List<Map<String, Object>> list =  ReadEnumJsonFile.localEnum(enumName);
        LOG.info("获取枚举数据结果:{}", JSON.toJSONString(list));
        if (null==list){
            result.setSuccess(false);
            result.setCode("201");
            result.setMsg("无该枚举类实例");
            return result;
        }
        result.setData(list);
        result.setSuccess(true);
        result.setCode("200");
        result.setMsg("获取数据成功");
        return result;
    }

}
