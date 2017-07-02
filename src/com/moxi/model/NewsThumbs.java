package com.moxi.model;

import java.util.Date;

/**
 * 点赞实体<BR>
 * NewsThumbs<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午9:18:01
 * @version 1.0.0
 *
 */
public class NewsThumbs {

	private Integer	id;
	private Integer	newsId;
	private Integer	userId;
	private Date createTime;
	private Date updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
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

}
