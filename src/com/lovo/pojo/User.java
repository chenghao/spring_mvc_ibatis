package com.lovo.pojo;

public class User extends Page {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pid;//主键
	private String loginName;//登录名
	private String password;//密码
	private String userName;//用户名
	private int age;//年龄
	private int sex;//性别
	private String sessionId;

	/////////////////////////////
	private String sexStr;//页面显示性别字段

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSexStr() {
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
