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

	// 打印
	@RequestMapping("/jobPrint")
	public String jobPrint(HttpServletRequest request) {
		// 获取所有的职位
		List<Job> allJob = jobService.selectAllJob(new Job());
		request.setAttribute(HrmConstants.ALL_JOB, allJob);
		return "job/jobPrint";
	}

	// 文件导出
	@RequestMapping("/jobImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// 下载文件路径,文件对象,user所有字段值
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.JOB_TABLENAME + ".xls";
		String[] thTitle = { "职位编号", "职位名称", "职位描述", "获得该职称人数","创建日期" };
		// 获取所有的职位
		List<Job> allJob = jobService.selectAllJob(new Job());
		// 调用导出Excel方法
		jobService.importExcel(thTitle, allJob, HrmConstants.JOB_TABLENAME, path);

		// 文件下载
		return HrmDownloadUtil.download(path, HrmConstants.JOB_TABLENAME+ ".xls");
	}

	// 查询职位
	@RequestMapping("/selectJob")
	public ModelAndView selectJob(@RequestParam(value = "caption", required = false) List<Integer> caption, Job job,
			HttpServletRequest request) {
		List<Job> jobList = jobService.selectJob(job);
		// 保存到request作用域
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(HrmConstants.JOB_LIST, jobList);
		// 保存到application中
		List<Job> contextJobList = jobService.selectAllJob(new Job());
		ServletContext sContext = request.getServletContext();
		sContext.setAttribute(HrmConstants.CONTEXT_JOBTlIST, contextJobList);
		modelAndView.setViewName("job/jobList");
		return modelAndView;
	}

	// 添加职位
	@RequestMapping("/addJob")
	public String addJob(Job job, HttpServletRequest request) {
		if (jobService.addJob(job) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/jobAdd";
	}

	// 判断职位名称是否重复
	@RequestMapping("/isRejobName")
	@ResponseBody
	public Job isRejobName(@RequestBody Job job) {
		if (jobService.findJobName(job).size() > 0) {
			// 设置为false代表职位名称重复
			Job hasJob = new Job();
			hasJob.setName(job.getName());
			hasJob.setIsSuccess(false);
			return hasJob;
		} else {
			job.setIsSuccess(true);
			return job;
		}

	}

	// 跳转到userAdd
	@RequestMapping("/jobAdd")
	public String jumpJobAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		return "job/jobAdd";
	}

	// 删除职位
	@RequestMapping("/deleteJob")
	public ModelAndView deleteJob(Integer[] caption) {
		jobService.deleteJob(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectJob");
		Job job = new Job();
		modelAndView.addObject("job", job);
		return modelAndView;
	}

	// 根据id查找用户
	@RequestMapping("/editJob")
	public String editJob(@RequestParam("jobId") Integer jobId, HttpServletRequest request) {
		Job job = jobService.findJobById(jobId);
		request.setAttribute(HrmConstants.JOB, job);
		return "forward:/editJobFrom";

	}

	// 根据给编辑页赋值
	@RequestMapping("/editJobFrom")
	public ModelAndView editJobFrom(HttpServletRequest request, ModelAndView modelAndView) {
		Job job = (Job) request.getAttribute("job");
		modelAndView.addObject("editJob", job);
		modelAndView.setViewName("job/jobEdit");
		return modelAndView;

	}

	// 保存编辑值
	@RequestMapping("/saveJob")
	public String saveJob(Job job) {
		jobService.updateJob(job);
		return "redirect:/selectJob";

	}
}
