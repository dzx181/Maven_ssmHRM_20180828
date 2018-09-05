package com.dzx.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dzx.domain.Employ;
import com.dzx.domain.User;
import com.dzx.service.EmployService;
import com.dzx.util.HrmConstants;
import com.dzx.util.HrmDownloadUtil;

@Controller
public class EmployController {
	@Autowired
	private EmployService employService;

	// 打印
	@RequestMapping("/employPrint")
	public String employPrint(HttpServletRequest request) {
		// 获取所有的员工
		List<Employ> allEmploy = employService.selectAllEmploy(new Employ());
		request.setAttribute(HrmConstants.ALL_EMPLOY, allEmploy);
		return "employ/employPrint";
	}

	// 排序查询(降序)
	@RequestMapping("/orderByDescEmploy")
	public ModelAndView orderByDescEmploy(@RequestParam(value = "caption", required = false) List<Integer> caption,
			Employ employ) {
		// 设置为false
		if (employ != null) {
			employ.setIsOrder(false);
		}

		return employService.selectEmploy(employ);
	}

	// 排序查询(升序)
	@RequestMapping("/orderByAscEmploy")
	public ModelAndView orderByAscEmploy(@RequestParam(value = "caption", required = false) List<Integer> caption,
			Employ employ) {
		// 设置为true
		if (employ != null) {
			employ.setIsOrder(true);
		}
		return employService.selectEmploy(employ);
	}

	// 文件导出
	@RequestMapping("/employImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// 下载文件路径,文件对象,user所有字段值
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.EMPLOY_TABLENAME + ".xls";
		String[] thTitle = { "员工编号", "所属部门", "获得职称", "月薪", "姓名", "身份证", "地址", "固定电话", "qq号/微信号", "邮箱", "政治面貌", "出生日期",
				"种族", "学历", "专业", "爱好", "评论", "性别", "电话", "建档日期" };
		// 获取所有的员工
		List<Employ> allEmploy = employService.selectAllEmploy(new Employ());
		// 调用导出Excel方法
		employService.importExcel(thTitle, allEmploy, HrmConstants.EMPLOY_TABLENAME, path);

		// 文件下载
		return HrmDownloadUtil.download(path, HrmConstants.EMPLOY_TABLENAME+ ".xls");
	}

	// 查找员工
	@RequestMapping("/selectEmploy")
	public ModelAndView selectEmploy(@RequestParam(value = "caption", required = false) List<Integer> caption,
			Employ employ) {

		return employService.selectEmploy(employ);
	}

	// 根据id查找员工
	@RequestMapping("/editEmploy")
	public String editEmploy(@RequestParam("employId") Integer employId, HttpServletRequest request) {
		Employ employ = employService.findEmployById(employId);
		request.setAttribute(HrmConstants.EMPLOY, employ);
		return "forward:/editEmployFrom";

	}

	// 根据给编辑页赋值
	@RequestMapping("/editEmployFrom")
	public ModelAndView editEmployFrom(HttpServletRequest request, ModelAndView modelAndView) {
		Employ employ = (Employ) request.getAttribute("employ");
		modelAndView.addObject("editEmploy", employ);
		modelAndView.setViewName("employ/employEdit");
		return modelAndView;

	}

	// 保存编辑值
	@RequestMapping("/saveEmploy")
	public String saveEmploy(Employ employ) {
		employService.updateEmploy(employ);
		return "redirect:/selectEmploy";

	}

	// 删除员工
	@RequestMapping("/deleteEmploy")
	public ModelAndView deleteEmploy(Integer[] caption) {
		employService.deleteEmploy(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectEmploy");
		User user = new User();
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	// 添加员工
	@RequestMapping("/addEmploy")
	public String addEmploy(Employ employ, HttpServletRequest request) {
		if (employService.addEmploy(employ) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/employAdd";
	}

	// 判断身份证是否重复
	@RequestMapping("/isRecardId")
	@ResponseBody
	public Employ isRecardId(@RequestBody Employ employ) {
		if (employService.findCardId(employ).size() > 0) {
			// 设置为false代表身份证重复
			Employ hasEmploy = new Employ();
			hasEmploy.setCard_id(employ.getCard_id());
			hasEmploy.setIsSuccess(false);
			return hasEmploy;
		} else {
			employ.setIsSuccess(true);
			return employ;
		}

	}

	// 跳转到employAdd
	@RequestMapping("/employAdd")
	public ModelAndView jumpEmployAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employ", new Employ());
		modelAndView.setViewName("employ/employAdd");
		return modelAndView;
	}

}
