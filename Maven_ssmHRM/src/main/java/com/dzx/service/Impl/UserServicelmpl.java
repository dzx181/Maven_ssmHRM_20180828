package com.dzx.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dzx.dao.UserDao;
import com.dzx.domain.User;
import com.dzx.service.UserService;

@Service
public class UserServicelmpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = false)
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Transactional(readOnly = false)
	@Override
	public int deleteUser(Integer[] caption) {
		// TODO Auto-generated method stub
		// ����ɾ��
		if (caption != null) {
			for (Integer integer : caption) {
				userDao.deleteUser(integer);
			}
		}

		return 0;
	}

	@Transactional(readOnly = false)
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public List<User> selectUser(User user) {
		// TODO Auto-generated method stub
		if (user != null) {
			int totalRecord = userDao.selectUserCount(user);
			user.setTotalRecord(totalRecord);
			return userDao.selectUser(user);
		} else {
			return new ArrayList<>();
		}

	}

	@Override
	public List<User> findLoginname(User user) {
		// TODO Auto-generated method stub
		return userDao.findLoginname(user);
	}

	// ����ID�����û�
	@Override
	public User findUserById(int userId) {
		User otherUser = userDao.findUserById(userId);
		return otherUser;
	}

	// �ļ�����
	@Override
	public Boolean importExcel(String[] thTitle, List<User> listValue, String tableTitle, String path,
			HttpServletRequest request) {
		// ����Excelworkbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// ����һ��������sheet
		XSSFSheet sheet = workbook.createSheet();

		// ������ͷ��ʽ
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();// ����
		font.setFontName("����");
		font.setFontHeightInPoints((short) 14);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);// ����
		cellStyle.setFont(font);// ����
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, thTitle.length - 1)); // �ϲ���Ԫ��

		// ���������ʽ
		XSSFCellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setAlignment(HorizontalAlignment.CENTER);// ����
		
		
		// ������һ��
		XSSFRow row0 = sheet.createRow(0);
		XSSFCell cell = null;
		cell = row0.createCell(0);
		cell.setCellValue(tableTitle);// ���ñ���ֵ
		cell.setCellStyle(cellStyle);
		row0.setHeightInPoints((short) 20);// �����и�

		// �����ڶ���
		XSSFRow row2 = sheet.createRow(1);
		// ����ڶ��е�����
		for (int i = 0; i < thTitle.length; i++) {
			cell = row2.createCell(i);
			cell.setCellValue(thTitle[i]);
			cell.setCellStyle(cellStyle2);
		}
		// ������,׷������
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");// ��ʽ������
		HttpSession session = request.getSession();// ��ȡsession
		User user = (User) session.getAttribute("user");
		if (user != null) {
			for (int i = 0; i < listValue.size(); i++) {
				XSSFRow nextRow = sheet.createRow(i + 2);
				XSSFCell cell2 = nextRow.createCell(0);
				cell2.setCellValue(listValue.get(i).getId().toString());
				cell2.setCellStyle(cellStyle2);
				
				
				cell2 = nextRow.createCell(1);
				cell2.setCellValue(listValue.get(i).getLoginname());
				cell2.setCellStyle(cellStyle2);
				
				// �ж��Ƿ��ǳ�������Ա�������ӡ������,��������������
				cell2 = nextRow.createCell(2);
				if (user.getStatus() == 1) {
					cell2.setCellValue(listValue.get(i).getPassword());
					cell2.setCellStyle(cellStyle2);
				} else {
					cell2.setCellValue("******");
					cell2.setCellStyle(cellStyle2);
				}
					
				cell2 = nextRow.createCell(3);
				if (listValue.get(i).getStatus() == 1) {
					cell2.setCellValue("��������Ա");
					cell2.setCellStyle(cellStyle2);
				} else {
					cell2.setCellValue("��ͨ�û�");
					cell2.setCellStyle(cellStyle2);
				}
				cell2 = nextRow.createCell(4);
				cell2.setCellValue(listValue.get(i).getUsername());
				cell2.setCellStyle(cellStyle2);
				
				cell2 = nextRow.createCell(5);
				String dateString = format.format(listValue.get(i).getCreateDate());
				cell2.setCellValue(dateString);
				cell2.setCellStyle(cellStyle2);
				
				// �����и�,(�ڼ��У�����)
				sheet.setColumnWidth((short) i, (short) 4000);
			}
			// ����һ���ļ�
			File file = new File(path);
			try {
				file.createNewFile();
				// ��excel������д�뵽����
				FileOutputStream stream = FileUtils.openOutputStream(file);
				workbook.write(stream);
				stream.close();
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	// ���������û�
	@Override
	public List<User> selectAllUser(User user) {
		// TODO Auto-generated method stub
		return userDao.selectAllUser(user);
	}

	@Override
	public Map<Integer, Integer[]> saveCheckbox(Integer[] caption, User user, HttpServletRequest request) {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		HashMap<Integer, Integer[]> captionMap = (HashMap<Integer, Integer[]>) session.getAttribute("captionMap");
		if (captionMap == null) {
			HashMap<Integer, Integer[]> firstCaptionMap = new HashMap<>();
			firstCaptionMap.put(user.getPageIndex(), caption);
			session.setAttribute("captionMap", firstCaptionMap);
			for (Map.Entry<Integer, Integer[]> integer : firstCaptionMap.entrySet()) {
				System.out.println("firstCaptionMap=" + integer.getKey());
				System.out.println("firstCaptionMap=" + integer.getValue());
			}
			return firstCaptionMap;
		} else {
			Iterator<Map.Entry<Integer, Integer[]>> it = captionMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Integer[]> entry = it.next();
				if (entry.getKey().equals(user.getPageIndex()))
					it.remove();// ʹ�õ�������remove()����ɾ��Ԫ��
			}

			captionMap.put(user.getPageIndex(), caption);
			for (Map.Entry<Integer, Integer[]> integer : captionMap.entrySet()) {
				System.out.println("captionMap=" + integer.getKey());
				System.out.println("captionMap=" + integer.getValue());
			}
			session.setAttribute("captionMap", captionMap);
			return captionMap;
		}

	}

}
