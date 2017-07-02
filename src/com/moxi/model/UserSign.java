package com.moxi.model;

import java.util.Date;

/**
 * 用户签到实体<BR>
 * UserSign<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午8:37:34
 * @version 1.0.0
 *
 */
public class UserSign implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	//主键id
	private Integer id;
	//用户id
	private String userId;
	//签到次数
	private Integer ignCount;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//是否已经签到
	private Integer isSign;
	
	//get/set
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getIgnCount() {
		return ignCount;
	}
	public void setIgnCount(Integer ignCount) {
		this.ignCount = ignCount;
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
	public Integer getIsSign() {
		return isSign;
	}
	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
}
