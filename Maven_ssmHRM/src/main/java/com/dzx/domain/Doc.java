package com.dzx.domain;

import java.io.Serializable;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Doc extends PageModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String filename;
	private String remark;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date create_date;
	private MultipartFile srcfile;
	private User user;
	
	public MultipartFile getSrcfile() {
		return srcfile;
	}
	public void setSrcfile(MultipartFile srcfile) {
		this.srcfile = srcfile;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Doc [id=" + id + ", title=" + title + ", filename=" + filename + ", remark=" + remark + ", create_date="
				+ create_date + ", user=" + user + "]";
	}
	public Doc(Integer id, String title, String filename, String remark, Date create_date, User user) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.remark = remark;
		this.create_date = create_date;
		this.user = user;
	}
	public Doc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
