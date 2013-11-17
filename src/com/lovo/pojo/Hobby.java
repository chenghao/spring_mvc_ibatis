package com.lovo.pojo;

public class Hobby extends Page {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pid;
	private Integer userId;//关联user表
	private String name;//爱好名字

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
