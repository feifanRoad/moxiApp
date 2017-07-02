package com.moxi.model;

import java.util.Date;

/**
 * 评论实体<BR>
 * Discuss<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午9:13:44
 * @version 1.0.0
 *
 */
public class Discuss {

	private Integer	id;
	private Integer	userId;
	private String	content;
	private Date createTime;
	private Date updateTime;
	private Integer	newsId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	

}
