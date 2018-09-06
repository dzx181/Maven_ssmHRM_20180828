package com.dzx.service;

import java.util.List;


import com.dzx.domain.Job;

public interface JobService {
	public int addJob(Job job);

	public List<Job> findJobName(Job job);

	public List<Job> selectJob(Job job);

	public int deleteJob(Integer[] caption);

	public int updateJob(Job job);

	public Job findJobById(int jobId);

	// ��װ��������
	public Boolean importExcel(String[] thTitle, List<Job> listValue, String tableTitle, String path);

	// ��������ְλ
	public List<Job> selectAllJob(Job job);
}
