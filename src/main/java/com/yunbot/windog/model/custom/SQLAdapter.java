package com.yunbot.windog.model.custom;

/**
 * @author William
 */
public class SQLAdapter {
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @author William
	 */
	public SQLAdapter(String sql) {
		super();
		this.sql = sql;
	}
	
}
