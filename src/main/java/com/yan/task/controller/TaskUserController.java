package com.yan.task.controller;

import com.alibaba.fastjson.JSON;
import com.yan.task.dao.entity.TaskUser;
import com.yan.task.model.user.UserModel;
import com.yan.task.service.TaskUserService;
import com.yan.utils.Md5Utils;
import com.yan.utils.Result;
import com.yan.utils.taskenum.convert.GeneralConv;
import com.yan.utils.taskutils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("user")
public class TaskUserController {
    private final static Logger LOG = LoggerFactory.getLogger(TaskUserController.class);
    @Autowired
    private TaskUserService taskUserService;

    /**
     * 根据ID获取客户信息
     * @param id
     * @return
     */
    @GetMapping("info/{id}")
    public Result<UserModel> userMessageById(@PathVariable("id") String id){
        LOG.info("进入userMessageById请求参数:{}",id);
        Result<TaskUser>  result = taskUserService.selectById(id);
        LOG.info("获取客户信息:{}",JSON.toJSONString(result));
        Result<UserModel> res = new Result<>();
        res.setData(GeneralConv.conv(result.getData(),UserModel.class));
        List<TaskUser> taskUsers = new ArrayList<>();
        taskUsers.add(result.getData());
        List<UserModel> list = GeneralConv.convList(taskUsers,UserModel.class);
        LOG.info("转换成list对象:{}",JSON.toJSONString(list));
        res.setMsg(result.getMsg());
        res.setCode(result.getCode());
        res.setSuccess(true);
        return res;
    }

    /**
     * 更新user表操作但是注意此处不做密码修改的更新操作，修改密码需要单独定义接口
     * @param taskUser
     * @return
     */
    @PostMapping("/saveorupdate")
    public Result<TaskUser> saveOrUpdate(@RequestBody TaskUser taskUser){
        LOG.info("userController获取参数:{}", JSON.toJSONString(taskUser));
        Result<TaskUser> result = null;
        try {
            if (StringUtils.isBlank(taskUser.getId())){
                //密码加密
                String passWord = Md5Utils.encry(taskUser.getPassWord());
                LOG.info("加密后的密码:{}",passWord);
                taskUser.setPassWord(passWord);
            }
            result = taskUserService.saveOrUpdate(taskUser);
            return result;
        }catch (Exception e){
            LOG.info("系统异常:{}",e);
        }
        return result;

    }

    @PostMapping("/login")
    public Result<TaskUser> login(@RequestBody TaskUser taskUser){
        LOG.info("userController获取参数:{}", JSON.toJSONString(taskUser));
        //密码加密
        String passWord = Md5Utils.encry(taskUser.getPassWord());
        LOG.info("加密后的密码:{}",passWord);
        taskUser.setPassWord(passWord);
        Result<TaskUser> result = taskUserService.login(taskUser);
        if (result.getSuccess()){
            //判断密码是否一致
            if (passWord.equals(result.getData().getPassWord())){
                return result;
            }else {
                result.setData(null);
                result.setSuccess(false);
                result.setCode("201");
                result.setMsg("登陆密码错误");
                return result;
            }
        }
        return result;
    }


    }
