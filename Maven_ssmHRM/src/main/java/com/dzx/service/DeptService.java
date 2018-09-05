package com.dzx.service;

import java.util.List;

import com.dzx.domain.Dept;

public interface DeptService {
	public int addDept(Dept dept);

	public List<Dept> findDeptName(Dept dept);

	public List<Dept> selectDept(Dept dept);

	public int deleteDept(Integer[] caption);

	public int updateDept(Dept dept);

	public Dept findDeptById(int deptId);

	// 封装导出方法
	public Boolean importExcel(String[] thTitle, List<Dept> listValue, String tableTitle, String path);

	// 查找所有职位
	public List<Dept> selectAllDept(Dept dept);
}
