package com.dzx.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dzx.domain.Job;
import com.dzx.service.JobService;
import com.dzx.util.HrmConstants;
import com.dzx.util.HrmDownloadUtil;

@Controller
public class JobController {
	@Autowired
	private JobService jobService;

	// ��ӡ
	@RequestMapping("/jobPrint")
	public String jobPrint(HttpServletRequest request) {
		// ��ȡ���е�ְλ
		List<Job> allJob = jobService.selectAllJob(new Job());
		request.setAttribute(HrmConstants.ALL_JOB, allJob);
		return "job/jobPrint";
	}

	// �ļ�����
	@RequestMapping("/jobImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// �����ļ�·��,�ļ�����,user�����ֶ�ֵ
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.JOB_TABLENAME + ".xls";
		String[] thTitle = { "ְλ���", "ְλ����", "ְλ����", "��ø�ְ������","��������" };
		// ��ȡ���е�ְλ
		List<Job> allJob = jobService.selectAllJob(new Job());
		// ���õ���Excel����
		jobService.importExcel(thTitle, allJob, HrmConstants.JOB_TABLENAME, path);

		// �ļ�����
		return HrmDownloadUtil.download(path, HrmConstants.JOB_TABLENAME+ ".xls");
	}

	// ��ѯְλ
	@RequestMapping("/selectJob")
	public ModelAndView selectJob(@RequestParam(value = "caption", required = false) List<Integer> caption, Job job,
			HttpServletRequest request) {
		List<Job> jobList = jobService.selectJob(job);
		// ���浽request������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(HrmConstants.JOB_LIST, jobList);
		// ���浽application��
		List<Job> contextJobList = jobService.selectAllJob(new Job());
		ServletContext sContext = request.getServletContext();
		sContext.setAttribute(HrmConstants.CONTEXT_JOBTlIST, contextJobList);
		modelAndView.setViewName("job/jobList");
		return modelAndView;
	}

	// ���ְλ
	@RequestMapping("/addJob")
	public String addJob(Job job, HttpServletRequest request) {
		if (jobService.addJob(job) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/jobAdd";
	}

	// �ж�ְλ�����Ƿ��ظ�
	@RequestMapping("/isRejobName")
	@ResponseBody
	public Job isRejobName(@RequestBody Job job) {
		if (jobService.findJobName(job).size() > 0) {
			// ����Ϊfalse����ְλ�����ظ�
			Job hasJob = new Job();
			hasJob.setName(job.getName());
			hasJob.setIsSuccess(false);
			return hasJob;
		} else {
			job.setIsSuccess(true);
			return job;
		}

	}

	// ��ת��userAdd
	@RequestMapping("/jobAdd")
	public String jumpJobAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		return "job/jobAdd";
	}

	// ɾ��ְλ
	@RequestMapping("/deleteJob")
	public ModelAndView deleteJob(Integer[] caption) {
		jobService.deleteJob(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectJob");
		Job job = new Job();
		modelAndView.addObject("job", job);
		return modelAndView;
	}

	// ����id�����û�
	@RequestMapping("/editJob")
	public String editJob(@RequestParam("jobId") Integer jobId, HttpServletRequest request) {
		Job job = jobService.findJobById(jobId);
		request.setAttribute(HrmConstants.JOB, job);
		return "forward:/editJobFrom";

	}

	// ���ݸ��༭ҳ��ֵ
	@RequestMapping("/editJobFrom")
	public ModelAndView editJobFrom(HttpServletRequest request, ModelAndView modelAndView) {
		Job job = (Job) request.getAttribute("job");
		modelAndView.addObject("editJob", job);
		modelAndView.setViewName("job/jobEdit");
		return modelAndView;

	}

	// ����༭ֵ
	@RequestMapping("/saveJob")
	public String saveJob(Job job) {
		jobService.updateJob(job);
		return "redirect:/selectJob";

	}
}
