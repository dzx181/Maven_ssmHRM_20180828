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

	// ��ӡ
	@RequestMapping("/employPrint")
	public String employPrint(HttpServletRequest request) {
		// ��ȡ���е�Ա��
		List<Employ> allEmploy = employService.selectAllEmploy(new Employ());
		request.setAttribute(HrmConstants.ALL_EMPLOY, allEmploy);
		return "employ/employPrint";
	}

	// �����ѯ(����)
	@RequestMapping("/orderByDescEmploy")
	public ModelAndView orderByDescEmploy(@RequestParam(value = "caption", required = false) List<Integer> caption,
			Employ employ) {
		// ����Ϊfalse
		if (employ != null) {
			employ.setIsOrder(false);
		}

		return employService.selectEmploy(employ);
	}

	// �����ѯ(����)
	@RequestMapping("/orderByAscEmploy")
	public ModelAndView orderByAscEmploy(@RequestParam(value = "caption", required = false) List<Integer> caption,
			Employ employ) {
		// ����Ϊtrue
		if (employ != null) {
			employ.setIsOrder(true);
		}
		return employService.selectEmploy(employ);
	}

	// �ļ�����
	@RequestMapping("/employImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// �����ļ�·��,�ļ�����,user�����ֶ�ֵ
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.EMPLOY_TABLENAME + ".xls";
		String[] thTitle = { "Ա�����", "��������", "���ְ��", "��н", "����", "���֤", "��ַ", "�̶��绰", "qq��/΢�ź�", "����", "������ò", "��������",
				"����", "ѧ��", "רҵ", "����", "����", "�Ա�", "�绰", "��������" };
		// ��ȡ���е�Ա��
		List<Employ> allEmploy = employService.selectAllEmploy(new Employ());
		// ���õ���Excel����
		employService.importExcel(thTitle, allEmploy, HrmConstants.EMPLOY_TABLENAME, path);

		// �ļ�����
		return HrmDownloadUtil.download(path, HrmConstants.EMPLOY_TABLENAME+ ".xls");
	}

	// ����Ա��
	@RequestMapping("/selectEmploy")
	public ModelAndView selectEmploy(@RequestParam(value = "caption", required = false) List<Integer> caption,
			Employ employ) {

		return employService.selectEmploy(employ);
	}

	// ����id����Ա��
	@RequestMapping("/editEmploy")
	public String editEmploy(@RequestParam("employId") Integer employId, HttpServletRequest request) {
		Employ employ = employService.findEmployById(employId);
		request.setAttribute(HrmConstants.EMPLOY, employ);
		return "forward:/editEmployFrom";

	}

	// ���ݸ��༭ҳ��ֵ
	@RequestMapping("/editEmployFrom")
	public ModelAndView editEmployFrom(HttpServletRequest request, ModelAndView modelAndView) {
		Employ employ = (Employ) request.getAttribute("employ");
		modelAndView.addObject("editEmploy", employ);
		modelAndView.setViewName("employ/employEdit");
		return modelAndView;

	}

	// ����༭ֵ
	@RequestMapping("/saveEmploy")
	public String saveEmploy(Employ employ) {
		employService.updateEmploy(employ);
		return "redirect:/selectEmploy";

	}

	// ɾ��Ա��
	@RequestMapping("/deleteEmploy")
	public ModelAndView deleteEmploy(Integer[] caption) {
		employService.deleteEmploy(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectEmploy");
		User user = new User();
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	// ���Ա��
	@RequestMapping("/addEmploy")
	public String addEmploy(Employ employ, HttpServletRequest request) {
		if (employService.addEmploy(employ) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/employAdd";
	}

	// �ж����֤�Ƿ��ظ�
	@RequestMapping("/isRecardId")
	@ResponseBody
	public Employ isRecardId(@RequestBody Employ employ) {
		if (employService.findCardId(employ).size() > 0) {
			// ����Ϊfalse�������֤�ظ�
			Employ hasEmploy = new Employ();
			hasEmploy.setCard_id(employ.getCard_id());
			hasEmploy.setIsSuccess(false);
			return hasEmploy;
		} else {
			employ.setIsSuccess(true);
			return employ;
		}

	}

	// ��ת��employAdd
	@RequestMapping("/employAdd")
	public ModelAndView jumpEmployAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employ", new Employ());
		modelAndView.setViewName("employ/employAdd");
		return modelAndView;
	}

}
