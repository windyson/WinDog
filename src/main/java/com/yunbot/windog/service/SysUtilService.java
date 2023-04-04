package com.yunbot.windog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunbot.windog.mapper.custom.SysUtilDao;
import com.yunbot.windog.model.custom.SQLAdapter;

@Service
public class SysUtilService {
	@Autowired
	private SysUtilDao dao;
	
	/**
	 * 执行sql
	 * @param sql
	 * @return
	 * @author fuce
	 * @Date 2019年8月31日 下午6:15:08
	 */
	public int executeSQL(String sql){
		return dao.executeSQL(new SQLAdapter(sql));
	}
	
	
	/**
	 * 查询sql
	 * @param sql
	 * @return list<map>
	 * @author fuce
	 * @Date 2020年4月10日 下午4:55:49
	 */
	public List<Map<Object,Object>> SelectExecuteSQL(String sql){
		return dao.SelectExecuteSQL(new SQLAdapter(sql));
	}
}
