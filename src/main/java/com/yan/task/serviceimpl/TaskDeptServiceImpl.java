package com.yan.task.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.task.dao.entity.TaskDept;
import com.yan.task.dao.entity.TaskDeptExample;
import com.yan.task.dao.mapper.TaskDeptMapper;
import com.yan.task.request.dept.DeptPageRequest;
import com.yan.task.service.TaskDeptService;
import com.yan.utils.Result;
import com.yan.utils.commom.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDeptServiceImpl implements TaskDeptService {
    private final static Logger LOG = LoggerFactory.getLogger(TaskDeptServiceImpl.class);
    @Autowired
    private TaskDeptMapper taskDeptMapper;
    public TaskDept selectById(String id){
        return taskDeptMapper.selectByPrimaryKey(id);
    }

    public Result<List<TaskDept>> queryPage(DeptPageRequest request){
        LOG.info("获取部门信息传入参数:{}",JSON.toJSONString(request));
        TaskDeptExample example = new TaskDeptExample();
        TaskDeptExample.Criteria criteria = example.createCriteria();
        criteria.andActiveEqualTo(Constant.ACTIVE_TRUE);
        Page page = PageHelper.startPage(request.getStart(),request.getPageSize(),false);
        List<TaskDept> list = taskDeptMapper.selectByExample(example);
        LOG.info("获取的分页对象信息：{}", JSON.toJSONString(page));
        LOG.info("获取的实际数据信息:{}",JSON.toJSONString(list));
        return new Result<List<TaskDept>>(list,request.getStart(),request.getPageSize(),Integer.parseInt(String.valueOf(page.getTotal())));

    }
}
