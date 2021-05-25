package com.trojan.entity;

import java.sql.Date;

public class User {
	private int id; // 用户id
	private String loginName;// 登录名
	private String password;// 密码
	private String realName;// 真实名称
	private String nickName;// 昵称
	private String email;// 邮箱
	private String phone;// 手机号
	private int status;// 状态
	private int role;// 角色
	private Date registerTime;// 注册时间
	private String registeIP;// 注册ip
	private int tryTime;// 尝试次数
	private Double balance;// 余额
	private String idno;//证件号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisteIP() {
		return registeIP;
	}

	public void setRegisteIP(String registeIP) {
		this.registeIP = registeIP;
	}

	public int getTryTime() {
		return tryTime;
	}

	public void setTryTime(int tryTime) {
		this.tryTime = tryTime;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", realName=" + realName
				+ ", nickName=" + nickName + ", email=" + email + ", phone=" + phone + ", status=" + status + ", role="
				+ role + ", registerTime=" + registerTime + ", registeIP=" + registeIP + ", tryTime=" + tryTime
				+ ", balance=" + balance + ", idno=" + idno + "]";
	}

}
