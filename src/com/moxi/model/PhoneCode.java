package com.moxi.model;

import java.sql.Timestamp;

/**
 * 短信验证码实体<BR>
 * PhoneCode<BR>
 * 创建人:feifan<BR>
 * 时间：2017年7月2日-上午8:38:58
 * @version 1.0.0
 *
 */
public class PhoneCode implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String phonenumber;
	private String code;
	private Timestamp createtime ;
	private String strTime ;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}
}
