package com.dzx.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Employ extends PageModel implements Serializable {
	/**
	 * 员工字段
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer dept_id;
	private Integer job_id;
	private Dept dept;
	private Job job;
	private Float salary;
	private String name;
	private String card_id;
	private String address;
	private String tel;
	private String QQ_num;
	private String email;
	private String party;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String race;
	private String education;
	private String speciality;
	private String hobby;
	private String remark;
	private Integer sex;
	private String phone;
	private Boolean isSuccess;
	private Boolean isOrder;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_date;

	public Employ() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employ(Integer id, Integer dept_id, Integer job_id, Dept dept, Job job, Float salary, String name,
			String card_id, String address, String tel, String qQ_num, String email, String party, Date birthday,
			String race, String education, String speciality, String hobby, String remark, Integer sex, String phone,
			Boolean isSuccess, Boolean isOrder, Date create_date) {
		super();
		this.id = id;
		this.dept_id = dept_id;
		this.job_id = job_id;
		this.dept = dept;
		this.job = job;
		this.salary = salary;
		this.name = name;
		this.card_id = card_id;
		this.address = address;
		this.tel = tel;
		QQ_num = qQ_num;
		this.email = email;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.sex = sex;
		this.phone = phone;
		this.isSuccess = isSuccess;
		this.isOrder = isOrder;
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "Employ [id=" + id + ", dept_id=" + dept_id + ", job_id=" + job_id + ", dept=" + dept + ", job=" + job
				+ ", salary=" + salary + ", name=" + name + ", card_id=" + card_id + ", address=" + address + ", tel="
				+ tel + ", QQ_num=" + QQ_num + ", email=" + email + ", party=" + party + ", birthday=" + birthday
				+ ", race=" + race + ", education=" + education + ", speciality=" + speciality + ", hobby=" + hobby
				+ ", remark=" + remark + ", sex=" + sex + ", phone=" + phone + ", isSuccess=" + isSuccess + ", isOrder="
				+ isOrder + ", create_date=" + create_date + "]";
	}

	public Boolean getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(Boolean isOrder) {
		this.isOrder = isOrder;
	}

	public Integer getDept_id() {
		// 设置部门id
		if (getDept() != null) {
			return getDept().getId();
		} else {
			return dept_id;
		}

	}

	public void setDept_id(Integer dept_id) {
		if (dept_id==null) {
			this.dept_id = getDept().getId();
		} else {
			this.dept_id = dept_id;
		}
		
	}

	public Integer getJob_id() {
		// 设置职位id
		if (getJob() != null) {
			return getJob().getId();
		} else {
			return job_id;
		}

	}

	public void setJob_id(Integer job_id) {
		if (job_id==null) {
			this.job_id = getJob().getId();
		} else {
			this.job_id = job_id;
		}
		
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQQ_num() {
		return QQ_num;
	}

	public void setQQ_num(String qQ_num) {
		QQ_num = qQ_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

}
