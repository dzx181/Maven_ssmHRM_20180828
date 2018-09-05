package com.dzx.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Dept extends PageModel implements Serializable {
	/**
	 * 部门字段
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String remark;
	// 部门总人数
	private String dept_sum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_date; // 建档日期
	private Boolean isSuccess;

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + ", remark=" + remark + ", dept_sum=" + dept_sum + ", isSuccess="
				+ isSuccess + "]";
	}

	public Dept(Integer id, String name, String remark, String dept_sum, Boolean isSuccess) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.dept_sum = dept_sum;
		this.isSuccess = isSuccess;
	}

	

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getDept_sum() {
		return dept_sum;
	}

	public void setDept_sum(String dept_sum) {
		this.dept_sum = dept_sum;
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

}
