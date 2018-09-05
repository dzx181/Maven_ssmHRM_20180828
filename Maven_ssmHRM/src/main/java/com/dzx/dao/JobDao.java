package com.dzx.dao;

import java.util.List;

import com.dzx.domain.Job;

public interface JobDao {
	
	public int addJob(Job job);
	public List<Job> findJobName(Job job);
	public List<Job> selectAllJob(Job job);
	public List<Job> selectJob(Job job);
	public int selectJobCount(Job job);
	public int deleteJob(int caption);
	public int updateJob(Job job);
	public Job findJobById(int jobId);
}
