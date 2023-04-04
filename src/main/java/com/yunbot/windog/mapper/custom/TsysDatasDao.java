package com.yunbot.windog.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunbot.windog.model.auto.TsysDatas;

public interface TsysDatasDao {
	
	public List<TsysDatas> selectByPrimaryKeys(@Param("ids") List<String> ids);
	
}