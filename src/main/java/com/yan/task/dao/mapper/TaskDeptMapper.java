package com.yan.task.dao.mapper;

import com.yan.task.dao.entity.TaskDept;
import com.yan.task.dao.entity.TaskDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDeptMapper {
    int countByExample(TaskDeptExample example);

    int deleteByExample(TaskDeptExample example);

    int deleteByPrimaryKey(String id);

    int insert(TaskDept record);

    int insertSelective(TaskDept record);

    List<TaskDept> selectByExample(TaskDeptExample example);

    TaskDept selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TaskDept record, @Param("example") TaskDeptExample example);

    int updateByExample(@Param("record") TaskDept record, @Param("example") TaskDeptExample example);

    int updateByPrimaryKeySelective(TaskDept record);

    int updateByPrimaryKey(TaskDept record);
}