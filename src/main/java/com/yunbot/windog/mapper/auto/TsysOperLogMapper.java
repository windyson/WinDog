package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.TsysOperLog;
import com.yunbot.windog.model.auto.TsysOperLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsysOperLogMapper {
    int countByExample(TsysOperLogExample example);

    int deleteByExample(TsysOperLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysOperLog record);

    int insertSelective(TsysOperLog record);

    List<TsysOperLog> selectByExample(TsysOperLogExample example);

    TsysOperLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysOperLog record, @Param("example") TsysOperLogExample example);

    int updateByExample(@Param("record") TsysOperLog record, @Param("example") TsysOperLogExample example);

    int updateByPrimaryKeySelective(TsysOperLog record);

    int updateByPrimaryKey(TsysOperLog record);
}