package com.yan.utils.exportutil;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.yan.task.model.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ExcelUtil<T> {

    private final static Logger LOG = LoggerFactory.getLogger(ExcelUtil.class);

    public  void export(List<T> resource, String fileName,Class clazz){
        String filePath = "C:\\Users\\94868\\Desktop\\excle\\"+fileName+".xlsx";
        LOG.info("获取的路径名称:{}",filePath);
        //写法一
        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(filePath, UserModel.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet(fileName).build();
//        excelWriter.write(resource, writeSheet);
        // 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();

        //写法二
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
         EasyExcel.write(filePath,clazz).sheet(fileName).doWrite(resource);

    }

}
