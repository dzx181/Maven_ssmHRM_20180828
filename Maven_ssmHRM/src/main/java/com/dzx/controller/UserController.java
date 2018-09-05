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

	// 打印
	@RequestMapping("/userPrint")
	public String userPrint(HttpServletRequest request) {
		// 获取所有的用户
		List<User> allUser = userService.selectAllUser(new User());
		request.setAttribute(HrmConstants.ALL_USER, allUser);
		return "user/userPrint";
	}

	// 文件导出
	@RequestMapping("/userImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// 下载文件路径,文件对象,user所有字段值
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.USER_TABLENAME + ".xls";
		String[] thTitle = { "用户编号", "登录名", "密码", "状态", "创建时间", "用户名" };
		// 获取所有的用户
		List<User> allUser = userService.selectAllUser(new User());
		// 调用导出Excel方法
		userService.importExcel(thTitle, allUser, HrmConstants.USER_TABLENAME, path,request);

		// 文件下载
		return HrmDownloadUtil.download(path, HrmConstants.USER_TABLENAME+ ".xls");
	}

	// 查询用户,caption用于接收翻页时用户选中的id值
	@RequestMapping("/selectUser")
	public ModelAndView selectUser(@RequestParam(value = "caption", required = false) Integer[] caption, User user,
			HttpServletRequest request) {
		List<User> userList = userService.selectUser(user);
		
		// 保存到request作用域
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(HrmConstants.USER_LIST, userList);
		modelAndView.setViewName("user/userList");
		return modelAndView;
	}

	// 根据id查找用户
	@RequestMapping("/editUser")
	public String editUser(@RequestParam("userId") Integer userId, HttpServletRequest request) {
		User user = userService.findUserById(userId);
		request.setAttribute(HrmConstants.USER, user);
		return "forward:/editUserFrom";

	}

	// 根据给编辑页赋值
	@RequestMapping("/editUserFrom")
	public ModelAndView editUserFrom(HttpServletRequest request, ModelAndView modelAndView) {
		User user = (User) request.getAttribute("user");
		modelAndView.addObject("editUser", user);
		modelAndView.setViewName("user/userEdit");
		return modelAndView;

	}

	// 保存编辑值
	@RequestMapping("/saveUser")
	public String saveUser(User user) {
		userService.updateUser(user);
		return "redirect:/selectUser";

	}

	// 删除用户
	@RequestMapping("/deleteUsers")
	public ModelAndView deleteUsers(Integer[] caption) {
		userService.deleteUser(caption);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/selectUser");
		User user = new User();
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	// 添加用户
	@RequestMapping("/insertUser")
	public String insertUser(User user, HttpServletRequest request) {
		if (userService.addUser(user) > 0) {
			request.setAttribute("type", "yes");
		} else {
			request.setAttribute("type", "no");
		}
		return "forward:/userAdd";
	}

	// 判断登录名是否重复
	@RequestMapping("/isReLoginname")
	@ResponseBody
	public User isReLoginname(@RequestBody User user) {
		if (userService.findLoginname(user).size() > 0) {
			// 设置为false代表登录名重复
			User hasUser = new User();
			hasUser.setLoginname(user.getLoginname());
			hasUser.setIsSuccess(false);
			return hasUser;
		} else {
			user.setIsSuccess(true);
			return user;
		}

	}

	// 跳转到userAdd
	@RequestMapping("/userAdd")
	public String jumpUserAdd(HttpServletRequest request) {
		request.setAttribute("type", request.getAttribute("type"));
		return "user/userAdd";
	}

}
