package com.yunbot.windog.model.custom;

/**
 * @author William
 */
public class Tablepar {
	private int pageNum;//页码
	private int pageSize;//数量
	private String orderByColumn;//排序字段
	private String isAsc;//排序字符 asc desc 
	private String searchText;//列表table里面的搜索
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderByColumn() {
		return orderByColumn;
	}
	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}
	public String getIsAsc() {
		return isAsc;
	}
	public void setIsAsc(String isAsc) {
		this.isAsc = isAsc;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}
