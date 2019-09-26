package com.wujk.springbootjpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 2152347032828776318L;

	@Id
	@Column(name="UID")
	private String uid;

	@Column(name="COMPANY_UID")
	private String companyUid;

	@Column(name="PHONE")
	private String phone;

	@Column(name="PASSWD")
	private String passwd;

	@Column(name="IP")
	private String ip;

	@Column(name="ENABLE")
	private Integer enable;

	@Column(name="BROWSER")
	private String browser;

	@Column(name="LOGINTIME")
	private Long logintime;

	@Column(name="GUIDE")
	private Boolean guide;

	@Column(name="CREATE_TIME")
	private Long createTime;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="UPDATE_TIME")
	private Long updateTime;

	@Column(name="UPDATE_USER")
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