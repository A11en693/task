package com.yan.task.serviceimpl;

import com.yan.task.dao.entity.TaskDept;
import com.yan.task.dao.mapper.TaskDeptMapper;
import com.yan.task.service.TaskDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskDeptServiceImpl implements TaskDeptService {
    @Autowired
    private TaskDeptMapper taskDeptMapper;
    public TaskDept selectById(String id){
        return taskDeptMapper.selectByPrimaryKey(id);
    }
}
