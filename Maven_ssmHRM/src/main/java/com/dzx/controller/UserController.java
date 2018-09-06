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

import com.dzx.domain.User;
import com.dzx.service.UserService;
import com.dzx.util.HrmConstants;
import com.dzx.util.HrmDownloadUtil;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	// ��ӡ
	@RequestMapping("/userPrint")
	public String userPrint(HttpServletRequest request) {
		// ��ȡ���е��û�
		List<User> allUser = userService.selectAllUser(new User());
		request.setAttribute(HrmConstants.ALL_USER, allUser);
		return "user/userPrint";
	}

	// �ļ�����
	@RequestMapping("/userImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// �����ļ�·��,�ļ�����,user�����ֶ�ֵ
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.USER_TABLENAME + ".xls";
		String[] thTitle = { "�û����", "��¼��", "����", "״̬", "����ʱ��", "�û���" };
		// ��ȡ���е��û�
		List<User> allUser = userService.selectAllUser(new User());
		// ���õ���Excel����
		userService.importExcel(thTitle, allUser, HrmConstants.USER_TABLENAME, path,request);

		// �ļ�����
		return HrmDownloadUtil.download(path, HrmConstants.USER_TABLENAME+ ".xls");
	}

	// ��ѯ�û�,caption���ڽ��շ�ҳʱ�û�ѡ�е�idֵ
	@RequestMapping("/selectUser")
	public ModelAndView selectUser(@RequestParam(value = "caption", required = false) Integer[] caption, User user,
			HttpServletRequest request) {
		List<User> userList = userService.selectUser(user);
		
		// ���浽request������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(HrmConstants.USER_LIST, userList);
		modelAndView.setViewName("user/userList");
		return modelAndView;
	}

	// ����id�����û�
	@RequestMapping("/editUser")
	public String editUser(@RequestParam("userId") Integer userId, HttpServletRequest request) {
		User user = userService.findUserById(userId);
		request.setAttribute(HrmConstants.USER, user);
		return "forward:/editUserFrom";

	}

	// ���ݸ��༭ҳ��ֵ
	@RequestMapping("/editUserFrom")
	public ModelAndView editUserFrom(HttpServletRequest request, ModelAndView modelAndView) {
		User user = (User) request.getAttribute("user");
		modelAndView.addObject("editUser", user);
		modelAndView.setViewName("user/userEdit");
		return modelAndView;

	}

	// ����༭ֵ
	@RequestMapping("/saveUser")
	public String saveUser(User user) {
		userService.updateUser(user);
		return "redirect:/selectUser";

	}

	// ɾ���û�
	@RequestMapping("/deleteUsers")
	public ModelAndView deleteUsers(Integer[] caption) {
		userService.deleteUser(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectUser");
		User user = new User();
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	// ����û�
	@RequestMapping("/insertUser")
	public String insertUser(User user, HttpServletRequest request) {
		if (userService.addUser(user) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/userAdd";
	}

	// �жϵ�¼���Ƿ��ظ�
	@RequestMapping("/isReLoginname")
	@ResponseBody
	public User isReLoginname(@RequestBody User user) {
		if (userService.findLoginname(user).size() > 0) {
			// ����Ϊfalse�����¼���ظ�
			User hasUser = new User();
			hasUser.setLoginname(user.getLoginname());
			hasUser.setIsSuccess(false);
			return hasUser;
		} else {
			user.setIsSuccess(true);
			return user;
		}

	}

	// ��ת��userAdd
	@RequestMapping("/userAdd")
	public String jumpUserAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		return "user/userAdd";
	}

}
