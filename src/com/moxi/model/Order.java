package com.moxi.model;

import java.util.Date;

public class Order implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer	id;
	private Integer	lottoryId;
	private Integer	userId;
	private Date createTime;
	private Date updateTime;
	private Integer	isPay;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLottoryId() {
		return lottoryId;
	}
	public void setLottoryId(Integer lottoryId) {
		this.lottoryId = lottoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsPay() {
		return isPay;
	}
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

}
