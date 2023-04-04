package com.yunbot.windog.mapper.auto;

import java.util.List;

import com.yunbot.windog.model.auto.TsysPermission;
import com.yunbot.windog.model.auto.TsysPermissionExample;
import org.apache.ibatis.annotations.Param;

public interface TsysPermissionMapper {
    long countByExample(TsysPermissionExample example);

    int deleteByExample(TsysPermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysPermission record);

    int insertSelective(TsysPermission record);

    List<TsysPermission> selectByExample(TsysPermissionExample example);

    TsysPermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysPermission record, @Param("example") TsysPermissionExample example);

    int updateByExample(@Param("record") TsysPermission record, @Param("example") TsysPermissionExample example);

    int updateByPrimaryKeySelective(TsysPermission record);

    int updateByPrimaryKey(TsysPermission record);
}