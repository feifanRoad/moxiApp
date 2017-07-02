package com.moxi.model;

import java.util.Date;

/**
 * 新闻类型实体<BR>
 * NewsType<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午9:25:45
 * @version 1.0.0
 *
 */
public class NewsType implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer	id;
	private String	newsType;
	private Date createTime;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
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
