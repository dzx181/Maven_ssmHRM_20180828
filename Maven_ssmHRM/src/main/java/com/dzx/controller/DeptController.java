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

import com.dzx.domain.Dept;
import com.dzx.service.DeptService;
import com.dzx.util.HrmConstants;
import com.dzx.util.HrmDownloadUtil;

@Controller
public class DeptController {
	@Autowired
	private DeptService deptService;

	// 打印
	@RequestMapping("/deptPrint")
	public String deptPrint(HttpServletRequest request) {
		// 获取所有的部门
		List<Dept> allDept = deptService.selectAllDept(new Dept());
		request.setAttribute(HrmConstants.ALL_DEPT, allDept);
		return "dept/deptPrint";
	}

	// 文件导出
	@RequestMapping("/deptImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// 下载文件路径,文件对象,user所有字段值
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.DEPT_TABLENAME + ".xls";
		String[] thTitle = { "部门编号", "部门名称", "部门描述", "部门总人数","创建日期" };
		// 获取所有的部门
		List<Dept> allDept = deptService.selectAllDept(new Dept());
		// 调用导出Excel方法
		deptService.importExcel(thTitle, allDept, HrmConstants.DEPT_TABLENAME, path);

		// 文件下载
		return HrmDownloadUtil.download(path, HrmConstants.DEPT_TABLENAME+ ".xls");
	}

	// 查询职位
	@RequestMapping("/selectDept")
	public ModelAndView selectDept(@RequestParam(value = "caption", required = false) List<Integer> caption, Dept dept,
			HttpServletRequest request) {
		List<Dept> deptList = deptService.selectDept(dept);
		// 保存到request作用域
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(HrmConstants.DEPT_LIST, deptList);
		// 保存到application 中
		List<Dept> contextdeptList = deptService.selectAllDept(new Dept());
		ServletContext sContext = request.getServletContext();
		sContext.setAttribute(HrmConstants.CONTEXT_DEPTlIST, contextdeptList);
		modelAndView.setViewName("dept/deptList");
		return modelAndView;
	}

	// 添加职位
	@RequestMapping("/addDept")
	public String addDept(Dept dept, HttpServletRequest request) {
		if (deptService.addDept(dept) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/deptAdd";
	}

	// 判断职位名称是否重复
	@RequestMapping("/isRedeptName")
	@ResponseBody
	public Dept isRedeptName(@RequestBody Dept dept) {
		if (deptService.findDeptName(dept).size() > 0) {
			// 设置为false代表职位名称重复
			Dept hasDept = new Dept();
			hasDept.setName(dept.getName());
			hasDept.setIsSuccess(false);
			return hasDept;
		} else {
			dept.setIsSuccess(true);
			return dept;
		}

	}

	// 跳转到deptAdd
	@RequestMapping("/deptAdd")
	public String jumpDeptAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		return "dept/deptAdd";
	}

	// 删除部门
	@RequestMapping("/deleteDept")
	public ModelAndView deleteDept(Integer[] caption) {
		deptService.deleteDept(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectDept");
		Dept dept = new Dept();
		modelAndView.addObject("job", dept);
		return modelAndView;
	}

	// 根据id查找部门
	@RequestMapping("/editDept")
	public String editDept(@RequestParam("deptId") Integer deptId, HttpServletRequest request) {
		Dept dept = deptService.findDeptById(deptId);
		request.setAttribute(HrmConstants.DEPT, dept);
		return "forward:/editDeptFrom";

	}

	// 根据给编辑页赋值
	@RequestMapping("/editDeptFrom")
	public ModelAndView editDeptFrom(HttpServletRequest request, ModelAndView modelAndView) {
		Dept dept = (Dept) request.getAttribute("dept");
		System.out.println(dept);
		modelAndView.addObject("editDept", dept);
		modelAndView.setViewName("dept/deptEdit");
		return modelAndView;

	}

	// 保存编辑值
	@RequestMapping("/saveDept")
	public String saveDept(Dept dept) {
		deptService.updateDept(dept);
		return "redirect:/selectDept";

	}
}
