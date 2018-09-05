package com.dzx.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dzx.domain.Dept;
import com.dzx.domain.Job;
import com.dzx.domain.User;
import com.dzx.service.DeptService;
import com.dzx.service.JobService;
import com.dzx.service.LoginService;
import com.dzx.util.HrmConstants;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginservice;
	@Autowired
	private JobService jobService;
	@Autowired
	private DeptService deptService;

	// 登录之后需要先查询所有的部门和职位，以便员工模块赋值
	@RequestMapping("/selectjobAndDept")
	public String selectjobAndDept(HttpServletRequest request) {
		ServletContext sContext = request.getServletContext();
		List<Job> contextJobList = jobService.selectAllJob(new Job());
		List<Dept> contextDeptList = deptService.selectAllDept(new Dept());
		sContext.setAttribute(HrmConstants.CONTEXT_JOBTlIST, contextJobList);
		sContext.setAttribute(HrmConstants.CONTEXT_DEPTlIST, contextDeptList);
		return "right";
	}

	// 判断ajax传输过来的值是否正确,登录成功 ，跳转到主页，不成功返回登录页
	@RequestMapping("/loginTo")
	public String isLogin(User user, HttpSession session, HttpServletRequest request) {
		List<User> userList = loginservice.findUserByLogin(user);
		if (userList.size() == 0) {
			request.setAttribute("type", "no");
			return "login";
		} else {
			// 保存该用户
			session.setAttribute("user", userList.get(0));
			return "main";
		}
	}

	// 注销退出
	@RequestMapping("/logOff")
	public String logOff(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "login";
	}

	// 跳转到登录页,top,left,right,main
	@RequestMapping(value = { "/{path}" })
	public String toLogin(@PathVariable("path") String path) {
		return path;
	}

}
