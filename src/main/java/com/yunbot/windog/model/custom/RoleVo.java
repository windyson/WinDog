package com.yunbot.windog.model.custom;

import com.yunbot.windog.model.auto.TsysRole;

/**
 * @author William
 */
public class RoleVo extends TsysRole{
	private static final long serialVersionUID = 1L;
	private boolean ischeck;//判断是否又这个权限
	
	public boolean isIscheck() {
		return ischeck;
	}
	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
	
	public RoleVo() {
		super();
	}
	public RoleVo(String id, String name,Boolean ischeck) {
		super(id, name);
		this.ischeck=ischeck;
	}
	
}
