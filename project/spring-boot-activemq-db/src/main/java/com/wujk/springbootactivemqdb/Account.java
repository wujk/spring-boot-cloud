package com.wujk.springbootactivemqdb;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 2152347032828776318L;

	private String uid;

	private String companyUid;

	private String phone;

	private String passwd;

	private String ip;

	private Integer enable;

	private String browser;

	private Long logintime;

	private Boolean guide;

	private Long createTime;

	private String createUser;

	private Long updateTime;

	private String updateUser;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCompanyUid() {
		return companyUid;
	}

	public void setCompanyUid(String companyUid) {
		this.companyUid = companyUid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public Long getLogintime() {
		return logintime;
	}

	public void setLogintime(Long logintime) {
		this.logintime = logintime;
	}

	public Boolean getGuide() {
		return guide;
	}

	public void setGuide(Boolean guide) {
		this.guide = guide;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}