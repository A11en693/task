package com.yan.task.controller;


import com.yan.task.dao.entity.TaskDept;
import com.yan.task.service.TaskDeptService;
import com.yan.utils.Constants;
import com.yan.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dept")
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
        result.setCode(Constants.SUCCESS_CODE);
        result.setSuccess(Constants.SUCCESS);
        result.setMsg("获取数据成功");
        return result;
    }
}
