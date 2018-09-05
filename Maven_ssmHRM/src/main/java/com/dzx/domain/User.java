package com.dzx.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class User extends PageModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;			// id
	private String username;	// 用户名
	private String loginname;	// 登录名
	private String password;	// 密码
	private Integer status;		// 状态
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;	// 建档日期
	private Boolean isSuccess;   //判断是否登录校验成功
	private List<Integer> caption;
	
	public List<Integer> getCaption() {
		return caption;
	}
	public void setCaption(List<Integer> caption) {
		this.caption = caption;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String username, String loginname, String password, Integer status, Date createDate,
			Boolean isSuccess, List<Integer> caption) {
		super();
		this.id = id;
		this.username = username;
		this.loginname = loginname;
		this.password = password;
		this.status = status;
		this.createDate = createDate;
		this.isSuccess = isSuccess;
		this.caption = caption;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", loginname=" + loginname + ", password=" + password
				+ ", status=" + status + ", createDate=" + createDate + ", isSuccess=" + isSuccess + ", caption="
				+ caption + "]";
	}
	
}