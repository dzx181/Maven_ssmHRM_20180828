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
		// 批量删除
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

	// 根据ID查找用户
	@Override
	public User findUserById(int userId) {
		User otherUser = userDao.findUserById(userId);
		return otherUser;
	}

	// 文件导出
	@Override
	public Boolean importExcel(String[] thTitle, List<User> listValue, String tableTitle, String path,
			HttpServletRequest request) {
		// 创建Excelworkbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建一个工作表sheet
		XSSFSheet sheet = workbook.createSheet();

		// 创建表头样式
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();// 字体
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 14);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
		cellStyle.setFont(font);// 字体
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, thTitle.length - 1)); // 合并单元格

		// 创建表格样式
		XSSFCellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setAlignment(HorizontalAlignment.CENTER);// 居中
		
		
		// 创建第一行
		XSSFRow row0 = sheet.createRow(0);
		XSSFCell cell = null;
		cell = row0.createCell(0);
		cell.setCellValue(tableTitle);// 设置标题值
		cell.setCellStyle(cellStyle);
		row0.setHeightInPoints((short) 20);// 设置行高

		// 创建第二行
		XSSFRow row2 = sheet.createRow(1);
		// 插入第二行的数据
		for (int i = 0; i < thTitle.length; i++) {
			cell = row2.createCell(i);
			cell.setCellValue(thTitle[i]);
			cell.setCellStyle(cellStyle2);
		}
		// 第三行,追加数据
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");// 格式化日期
		HttpSession session = request.getSession();// 获取session
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
				
				// 判断是否是超级管理员，是则打印密码列,否则隐藏密码列
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
					cell2.setCellValue("超级管理员");
					cell2.setCellStyle(cellStyle2);
				} else {
					cell2.setCellValue("普通用户");
					cell2.setCellStyle(cellStyle2);
				}
				cell2 = nextRow.createCell(4);
				cell2.setCellValue(listValue.get(i).getUsername());
				cell2.setCellStyle(cellStyle2);
				
				cell2 = nextRow.createCell(5);
				String dateString = format.format(listValue.get(i).getCreateDate());
				cell2.setCellValue(dateString);
				cell2.setCellStyle(cellStyle2);
				
				// 设置列高,(第几列，像素)
				sheet.setColumnWidth((short) i, (short) 4000);
			}
			// 创建一个文件
			File file = new File(path);
			try {
				file.createNewFile();
				// 将excel的内容写入到流中
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

	// 查找所有用户
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
					it.remove();// 使用迭代器的remove()方法删除元素
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
