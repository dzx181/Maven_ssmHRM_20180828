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

	// ��ӡ
	@RequestMapping("/docPrint")
	public String docPrint(HttpServletRequest request) {
		// ��ȡ���е��ļ�
		List<Doc> allFile = docService.selectAllFile(new Doc());
		request.setAttribute(HrmConstants.ALL_DOC, allFile);
		return "doc/docPrint";
	}

	// �ļ�����
	@RequestMapping("/docImportExcel")
	public ResponseEntity<byte[]> importExcel(HttpServletRequest request) throws IOException {
		// �����ļ�·��,�ļ�����,doc�����ֶ�ֵ
		String servletPath = request.getServletContext().getRealPath("/uploadFile");
		String path = servletPath + File.separator + HrmConstants.DOC_TABLENAME + ".xls";
		String[] thTitle = { "�ļ����", "����", "�ļ���", "����ʱ��", "������", "����" };
		// ��ȡ���е��ļ�
		List<Doc> allFile = docService.selectAllFile(new Doc());
		// ���õ���Excel����
		docService.importExcel(thTitle, allFile, HrmConstants.DOC_TABLENAME, path);

		// �ļ�����
		return HrmDownloadUtil.download(path, HrmConstants.DOC_TABLENAME+ ".xls");
	}

	// �ļ�����
	@RequestMapping("/downloadFile")
	public ResponseEntity<byte[]> downloadFile(Doc doc, HttpServletRequest request) throws IOException {
		// �����ļ�·��,�ļ�����
		String servletPath = request.getServletContext()
				.getRealPath("/uploadFile" + File.separator + doc.getUser().getLoginname());
		String path = servletPath + File.separator + doc.getFilename();

		// �ļ�����
		return HrmDownloadUtil.download(path, doc.getFilename());
	}

	// ɾ��
	@RequestMapping("/deleteFile")
	public String delteFile(Integer[] caption) {
		docService.deleteFile(caption);
		return "redirect:/selectFile";
	}

	// ����
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
			// session�л�ȡuser
			User user = (User) request.getSession().getAttribute("user");
			doc.setUser(user);
			// �ϴ��ļ���
			String filename = doc.getSrcfile().getOriginalFilename();
			// վ��·��
			String servletPath = request.getServletContext().getRealPath("/uploadFile");
			// Ϊ�ϴ��ļ��û�����һ���������ļ���
			File userDir = new File(servletPath + File.separator + user.getLoginname());
			if (!userDir.exists()) {
				userDir.mkdir();
			}
			// �ϴ��ļ������û��ļ���
			File file = new File(userDir + File.separator + filename);
			if (!file.getParentFile().exists()) {
				file.mkdir();
			}
			// �ϴ��ļ���·��
			doc.getSrcfile().transferTo(file);
			// ����doc��ֵ
			doc.setFilename(filename);
			docService.fileUpload(doc);
			request.setAttribute("message", "�ϴ��ɹ�");
			return "doc/docUpload";
		}
		request.setAttribute("message", "�ϴ��ļ�����Ϊ��");
		return "doc/docUpload";
	}

	@RequestMapping("/toUploadFile")
	public String toUploadFile() {
		return "doc/docUpload";
	}

}
