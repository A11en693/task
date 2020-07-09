package com.yan.task.dao.mapper;

import com.yan.task.dao.entity.TaskUser;
import com.yan.task.dao.entity.TaskUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskUserMapper {
    int countByExample(TaskUserExample example);

    int deleteByExample(TaskUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    List<TaskUser> selectByExample(TaskUserExample example);

    TaskUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TaskUser record, @Param("example") TaskUserExample example);

    int updateByExample(@Param("record") TaskUser record, @Param("example") TaskUserExample example);

    int updateByPrimaryKeySelective(TaskUser record);

    int updateByPrimaryKey(TaskUser record);
}