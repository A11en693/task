package com.yan.task.controller;


import com.yan.task.dao.entity.TaskDept;
import com.yan.task.request.dept.DeptPageRequest;
import com.yan.task.service.TaskDeptService;
import com.yan.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dept")
@Api(value = "TaskDeptController", description = "部门信息接口")
public class TaskDeptController {
    private final static Logger LOG = LoggerFactory.getLogger(TaskDeptController.class);
    @Autowired
    private TaskDeptService taskDeptService;

    /**
     * restful风格实现查询
     * @param id
     * @return
     */
    @GetMapping("info/{id}")
    public Result<TaskDept> deptMessageById(@PathVariable("id") String id){
        LOG.info("进入message方法");
        Result<TaskDept> result = new Result<>();
        TaskDept taskDept = taskDeptService.selectById(id);
        result.setData(taskDept);
        result.setMsg("获取数据成功");
        return result;
    }

    @ApiOperation(value="获取部门信息", notes="获取部门信息")
    @ApiImplicitParam(name = "request", value = "获取部门信息请求参数", required = true, dataType = "DeptPageRequest")
    @PostMapping("/msg")
    public Result<List<TaskDept>> dept(@RequestBody DeptPageRequest request){
        Result<List<TaskDept>> result = taskDeptService.queryPage(request);
        return result;
    }


}
