package com.dzx.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dzx.domain.Doc;
import com.dzx.domain.User;
import com.dzx.service.DocService;
import com.dzx.util.HrmConstants;
import com.dzx.util.HrmDownloadUtil;

@Controller
public class DocController {
	@Autowired
	private DocService docService;

	// 打印
	@RequestMapping("/docPrint")
	public String docPrint(HttpServletRequest request) {
		// 获取所有的文件
		List<Doc> allFile = docService.selectAllFile(new Doc());
		request.setAttribute(HrmConstants.ALL_DOC, allFile);
		return "doc/docPrint";
	}

	// 文件导出
	@RequestMapping("/docImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// 下载文件路径,文件对象,doc所有字段值
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.DOC_TABLENAME + ".xls";
		String[] thTitle = { "文件编号", "标题", "文件名", "创建时间", "创建人", "描述" };
		// 获取所有的文件
		List<Doc> allFile = docService.selectAllFile(new Doc());
		// 调用导出Excel方法
		docService.importExcel(thTitle, allFile, HrmConstants.DOC_TABLENAME, path);

		// 文件下载
		return HrmDownloadUtil.download(path, HrmConstants.DOC_TABLENAME+ ".xls");
	}

	// 文件下载
	@RequestMapping("/downloadFile")
	public ResponseEntity<byte[]> downloadFile(Doc doc, HttpServletRequest request) throws IOException {
		// 下载文件路径,文件对象
		String servletPath = request.getServletContext()
				.getRealPath("/uploadFile" + File.separator + doc.getUser().getLoginname());
		String path = servletPath + File.separator + doc.getFilename();

		// 文件下载
		return HrmDownloadUtil.download(path, doc.getFilename());
	}

	// 删除
	@RequestMapping("/deleteFile")
	public String delteFile(Integer[] caption) {
		docService.deleteFile(caption);
		return "redirect:/selectFile";
	}

	// 查找
	@RequestMapping("/selectFile")
	public ModelAndView SelectFile(Doc doc) {
		List<Doc> docList = docService.selectFile(doc);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(HrmConstants.DOC_LIST, docList);
		modelAndView.setViewName("doc/docList");
		return modelAndView;
	}

	@RequestMapping("/uploadFile")
	public String uploadFile(Doc doc, HttpServletRequest request) throws IllegalStateException, IOException {
		if (doc.getSrcfile() != null) {
			// session中获取user
			User user = (User) request.getSession().getAttribute("user");
			doc.setUser(user);
			// 上传文件名
			String filename = doc.getSrcfile().getOriginalFilename();
			// 站点路径
			String servletPath = request.getServletContext().getRealPath("/uploadFile");
			// 为上传文件用户创建一个单独的文件夹
			File userDir = new File(servletPath + File.separator + user.getLoginname());
			if (!userDir.exists()) {
				userDir.mkdir();
			}
			// 上传文件到该用户文件夹
			File file = new File(userDir + File.separator + filename);
			if (!file.getParentFile().exists()) {
				file.mkdir();
			}
			// 上传文件到路径
			doc.getSrcfile().transferTo(file);
			// 设置doc的值
			doc.setFilename(filename);
			docService.fileUpload(doc);
			request.setAttribute("message", "上传成功");
			return "doc/docUpload";
		}
		request.setAttribute("message", "上传文件不能为空");
		return "doc/docUpload";
	}

	@RequestMapping("/toUploadFile")
	public String toUploadFile() {
		return "doc/docUpload";
	}

}
