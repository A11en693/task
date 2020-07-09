package com.yan.task.service;


import com.yan.task.dao.entity.TaskUser;
import com.yan.utils.Result;

public interface TaskUserService {

   Result<TaskUser> selectById(String id);

   Result<TaskUser> saveOrUpdate(TaskUser taskUser);

   Result<TaskUser> login(TaskUser taskUser);
}
