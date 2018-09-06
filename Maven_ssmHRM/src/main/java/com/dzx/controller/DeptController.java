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

	// ��ӡ
	@RequestMapping("/deptPrint")
	public String deptPrint(HttpServletRequest request) {
		// ��ȡ���еĲ���
		List<Dept> allDept = deptService.selectAllDept(new Dept());
		request.setAttribute(HrmConstants.ALL_DEPT, allDept);
		return "dept/deptPrint";
	}

	// �ļ�����
	@RequestMapping("/deptImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// �����ļ�·��,�ļ�����,user�����ֶ�ֵ
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.DEPT_TABLENAME + ".xls";
		String[] thTitle = { "���ű��", "��������", "��������", "����������","��������" };
		// ��ȡ���еĲ���
		List<Dept> allDept = deptService.selectAllDept(new Dept());
		// ���õ���Excel����
		deptService.importExcel(thTitle, allDept, HrmConstants.DEPT_TABLENAME, path);

		// �ļ�����
		return HrmDownloadUtil.download(path, HrmConstants.DEPT_TABLENAME+ ".xls");
	}

	// ��ѯְλ
	@RequestMapping("/selectDept")
	public ModelAndView selectDept(@RequestParam(value = "caption", required = false) List<Integer> caption, Dept dept,
			HttpServletRequest request) {
		List<Dept> deptList = deptService.selectDept(dept);
		// ���浽request������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(HrmConstants.DEPT_LIST, deptList);
		// ���浽application ��
		List<Dept> contextdeptList = deptService.selectAllDept(new Dept());
		ServletContext sContext = request.getServletContext();
		sContext.setAttribute(HrmConstants.CONTEXT_DEPTlIST, contextdeptList);
		modelAndView.setViewName("dept/deptList");
		return modelAndView;
	}

	// ���ְλ
	@RequestMapping("/addDept")
	public String addDept(Dept dept, HttpServletRequest request) {
		if (deptService.addDept(dept) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/deptAdd";
	}

	// �ж�ְλ�����Ƿ��ظ�
	@RequestMapping("/isRedeptName")
	@ResponseBody
	public Dept isRedeptName(@RequestBody Dept dept) {
		if (deptService.findDeptName(dept).size() > 0) {
			// ����Ϊfalse����ְλ�����ظ�
			Dept hasDept = new Dept();
			hasDept.setName(dept.getName());
			hasDept.setIsSuccess(false);
			return hasDept;
		} else {
			dept.setIsSuccess(true);
			return dept;
		}

	}

	// ��ת��deptAdd
	@RequestMapping("/deptAdd")
	public String jumpDeptAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		return "dept/deptAdd";
	}

	// ɾ������
	@RequestMapping("/deleteDept")
	public ModelAndView deleteDept(Integer[] caption) {
		deptService.deleteDept(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectDept");
		Dept dept = new Dept();
		modelAndView.addObject("job", dept);
		return modelAndView;
	}

	// ����id���Ҳ���
	@RequestMapping("/editDept")
	public String editDept(@RequestParam("deptId") Integer deptId, HttpServletRequest request) {
		Dept dept = deptService.findDeptById(deptId);
		request.setAttribute(HrmConstants.DEPT, dept);
		return "forward:/editDeptFrom";

	}

	// ���ݸ��༭ҳ��ֵ
	@RequestMapping("/editDeptFrom")
	public ModelAndView editDeptFrom(HttpServletRequest request, ModelAndView modelAndView) {
		Dept dept = (Dept) request.getAttribute("dept");
		System.out.println(dept);
		modelAndView.addObject("editDept", dept);
		modelAndView.setViewName("dept/deptEdit");
		return modelAndView;

	}

	// ����༭ֵ
	@RequestMapping("/saveDept")
	public String saveDept(Dept dept) {
		deptService.updateDept(dept);
		return "redirect:/selectDept";

	}
}
