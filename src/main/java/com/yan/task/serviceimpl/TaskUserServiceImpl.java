package com.yan.task.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.yan.task.dao.entity.TaskUser;
import com.yan.task.dao.entity.TaskUserExample;
import com.yan.task.dao.mapper.TaskUserMapper;
import com.yan.task.service.TaskUserService;
import com.yan.utils.DateUtils;
import com.yan.utils.GeneratorKeyUtils;
import com.yan.utils.Result;
import com.yan.utils.taskenum.YesOrNo;
import com.yan.utils.taskutils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class TaskUserServiceImpl implements TaskUserService {
    private final static Logger LOG = LoggerFactory.getLogger(TaskUserServiceImpl.class);
    @Autowired
    private TaskUserMapper taskUserMapper;

    @Override
    public Result<TaskUser> selectById(String id){
        Result<TaskUser> result = new Result<>();
        TaskUser taskUser = taskUserMapper.selectByPrimaryKey(id);
        result.setMsg("操作成功");
        result.setSuccess(true);
        result.setCode("200");
        result.setData(taskUser);
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<TaskUser> saveOrUpdate(TaskUser taskUser){
        LOG.info("TaskUserServiceImpl,更新或保存用户信息，获取参数:{}", JSON.toJSONString(taskUser));
        Result<TaskUser> result = new Result<>();
        //设置更新日期
        taskUser.setUpdateDate(DateUtils.formatDate(new Date()));
        taskUser.setUpdateTime(DateUtils.formatTime(new Date()));
        if (StringUtils.isNotBlank(taskUser.getId())){
            //更新操作
           taskUserMapper.insertSelective(taskUser);
        }else{
            //设置是否有效
            taskUser.setActive(YesOrNo.YES_CODE.getCode());
            taskUser.setCreateDate(DateUtils.formatDate(new Date()));
            taskUser.setCreateTime(DateUtils.formatTime(new Date()));
            //生成主键ID
            String id = GeneratorKeyUtils.generatorKey();
            LOG.info("user表生成的主键位数:{},主键值:{}",id.length(),id);
            taskUser.setId(id);
            taskUserMapper.insertSelective(taskUser);
        }
        TaskUser updateTaskUser = taskUserMapper.selectByPrimaryKey(taskUser.getId());
        result.setMsg("操作成功");
        result.setSuccess(true);
        result.setCode("200");
        result.setData(updateTaskUser);
        return result;
    }

    public Result<TaskUser> login(TaskUser taskUser){
        LOG.info("TaskUserServiceImpl，账户密码登陆参数:{}",JSON.toJSONString(taskUser));
        Result<TaskUser> result = new Result<>();
        TaskUserExample example = new TaskUserExample();
        TaskUserExample.Criteria criteria = example.createCriteria();
        criteria.andActiveEqualTo(YesOrNo.YES_CODE.getCode());
        criteria.andLoginNameEqualTo(taskUser.getLoginName());
        List<TaskUser> list = taskUserMapper.selectByExample(example);
        if (null != list&&list.size()>0){
            result.setData(list.get(0));
            result.setCode("200");
            result.setSuccess(true);
            result.setMsg("获取用户信息成功");
            return  result;
        }
        result.setData(null);
        result.setCode("200");
        result.setSuccess(false);
        result.setMsg("登陆账户错误");
        return  result;
    }

//    public static void main(String[] args) {
//        LOG.info("111");
//    }

}
