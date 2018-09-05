package com.dzx.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Job extends PageModel implements Serializable {
	/**
	 * 职位字段
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String remark;
	//	职位总人数
	private String jobSum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date create_date;	// 建档日期
	private Boolean isSuccess;
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", remark=" + remark + ", jobSum=" + jobSum + ", isSuccess="
				+ isSuccess + "]";
	}


	public Job(Integer id, String name, String remark, String jobSum, Boolean isSuccess) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.jobSum = jobSum;
		this.isSuccess = isSuccess;
	}


	public String getJobSum() {
		return jobSum;
	}

	public void setJobSum(String jobSum) {
		this.jobSum = jobSum;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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


	
}
