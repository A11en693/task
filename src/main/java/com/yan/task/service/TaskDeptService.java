package com.yan.task.service;

import com.yan.task.dao.entity.TaskDept;
import com.yan.task.request.dept.DeptPageRequest;
import com.yan.utils.Result;

import java.util.List;

public interface TaskDeptService {

    TaskDept selectById(String id);

    Result<List<TaskDept>> queryPage(DeptPageRequest request);
}
