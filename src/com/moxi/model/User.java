package com.moxi.model;

import java.util.Date;

/**
 * 用户实体<BR>
 * User<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午8:37:53
 * @version 1.0.0
 *
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 用户id
	private Integer id;
	// 电话号码
	private String phoneNumber;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 创建时间
	private Date creatTime;
	// 更新时间
	private Date updateTime;
	// 积分数
	private Integer score;
	// 用户头像url
	private String imgPath;
	//用户签名
	private String userSign;
	//是否登录flag
	private Integer isLogin;

	//get/set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getUserSign() {
		return userSign;
	}

	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}

	public Integer getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}
	
}
