package com.dzx.dao;

import java.util.List;

import com.dzx.domain.Dept;

public interface DeptDao {
	public int addDept(Dept dept);
	public List<Dept> findDeptName(Dept dept);
	public List<Dept> selectAllDept(Dept dept);
	public List<Dept> selectDept(Dept dept);
	public int selectDeptCount(Dept dept);
	public int deleteDept(int caption);
	public int updateDept(Dept dept);
	public Dept findDeptById(int dept);
}
