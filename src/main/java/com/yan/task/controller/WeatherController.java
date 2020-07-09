package com.yan.task.controller;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("weather")
public class WeatherController {
    private final static Logger LOG = LoggerFactory.getLogger(WeatherController.class);

//    @GetMapping("/cityweather/{cityName}")
    @PostMapping("/cityweather")
    public String weather(@RequestBody Map<String,String> map){
        String cityName =  map.get("cityName");
        LOG.info("传入参数:{}",cityName);
        try{
            // 参数url化
            String city = URLEncoder.encode(cityName, "utf-8");
            // 拼地址
            String apiUrl = "http://t.weather.sojson.com/api/weather/city/" + cityName;
            //String apiUrl = String.format("https://www.sojson.com/open/api/weather/json.shtml?city=%s", city);
            // 开始请求
            URL url = new URL(apiUrl);
            URLConnection open = url.openConnection();
            InputStream input = open.getInputStream();
            // 这里转换为String
            String result = IOUtils.toString(input, "utf-8");
            LOG.info("获取{},天气信息{}",cityName,result);
            return  result;
        }catch (Exception e){
            LOG.error("获取天气信息异常:{}",e);
        }

       return null;
    }
}
