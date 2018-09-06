package com.dzx.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.dzx.domain.Employ;

public interface EmployService {
	public int addEmploy(Employ employ);

	public List<Employ> findCardId(Employ employ);

	public List<Employ> selectAllEmploy(Employ employ);

	// ��װ��ѯԱ���ķ���
	public ModelAndView selectEmploy(Employ employ);

	public Employ findEmployById(int employId);

	public int updateEmploy(Employ employ);

	public int deleteEmploy(Integer[] caption);

	// ��װ��������
	public Boolean importExcel(String[] thTitle, List<Employ> listValue, String tableTitle, String path);

}
