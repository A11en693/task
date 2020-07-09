package com.yan.task.export;

import com.alibaba.fastjson.JSON;
import com.yan.task.controller.TaskUserController;
import com.yan.task.dao.entity.TaskUser;
import com.yan.task.model.user.UserModel;
import com.yan.task.service.TaskUserService;
import com.yan.utils.DateUtils;
import com.yan.utils.Result;
import com.yan.utils.exportutil.ExcelUtil;
import com.yan.utils.taskenum.convert.GeneralConv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("export_user")
public class ExportUserController {

    private final static Logger LOG = LoggerFactory.getLogger(TaskUserController.class);

    @Autowired
    private TaskUserService taskUserService;

    /**
     * 根据ID获取客户信息
     * @param id
     * @return
     */
    @GetMapping("user/{id}")
    public Result<UserModel> exportUserExcel(@PathVariable("id") String id){
        LOG.info("进入exportUserExcel请求参数:{}",id);
        Result<TaskUser>  result = taskUserService.selectById(id);
        LOG.info("获取客户信息:{}", JSON.toJSONString(result));
        Result<UserModel> res = new Result<>();
        UserModel userModel = GeneralConv.conv(result.getData(),UserModel.class);
        LOG.info("转换后的对象参数:{}",JSON.toJSONString(userModel));
        List<UserModel>  userModels = new ArrayList<>();
        userModels.add(userModel);
        ExcelUtil<UserModel> excelUtil = new ExcelUtil<>();
        excelUtil.export(userModels,DateUtils.getCurrentTime(),UserModel.class);
        res.setData(userModel);
        res.setMsg(result.getMsg());
        res.setCode(result.getCode());
        res.setSuccess(true);
        return res;
    }


}
