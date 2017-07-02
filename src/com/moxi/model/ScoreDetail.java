package com.moxi.model;

import java.util.Date;

/**
 * 积分明细实体<BR>
 * ScoreDetail<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午9:44:09
 * @version 1.0.0
 *
 */
public class ScoreDetail implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer	id;
	private Integer	type;
	private Integer	scoreDetail;
	private Date createTime;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getScoreDetail() {
		return scoreDetail;
	}
	public void setScoreDetail(Integer scoreDetail) {
		this.scoreDetail = scoreDetail;
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

}
