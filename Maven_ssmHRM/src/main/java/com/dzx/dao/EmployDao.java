package com.dzx.dao;

import java.util.List;

import com.dzx.domain.Employ;

public interface EmployDao {
	public int addEmploy(Employ employ);
	public List<Employ> findCardId(Employ employ);
	public List<Employ> selectAllEmploy(Employ employ);
	public List<Employ> selectEmploy(Employ employ);
	public int selectEmployCount(Employ employ);
	public Employ findEmployById(int employId);
	public int updateEmploy(Employ employ);
	public int deleteEmploy(int caption);
	
}
