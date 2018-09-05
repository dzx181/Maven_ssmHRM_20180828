package com.dzx.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.dzx.dao.EmployDao;
import com.dzx.domain.Employ;
import com.dzx.service.EmployService;
import com.dzx.util.HrmConstants;

@Service
public class EmployServiceImpl implements EmployService {
	@Autowired
	private EmployDao employDao;

	@Transactional(readOnly = false)
	@Override
	public int addEmploy(Employ employ) {
		// TODO Auto-generated method stub
		return employDao.addEmploy(employ);
	}

	@Override
	public List<Employ> findCardId(Employ employ) {
		// TODO Auto-generated method stub
		return employDao.findCardId(employ);
	}

	@Override
	public List<Employ> selectAllEmploy(Employ employ) {
		// TODO Auto-generated method stub
		return employDao.selectAllEmploy(employ);
	}

	@Override
	public ModelAndView selectEmploy(Employ employ) {
		// TODO Auto-generated method stub
		if (employ != null) {
			int totalRecord = employDao.selectEmployCount(employ);
			employ.setTotalRecord(totalRecord);
			List<Employ> employList= employDao.selectEmploy(employ);
			// 保存到request作用域
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject(HrmConstants.EMPLOY_LIST, employList);
			modelAndView.setViewName("employ/employList");
			return modelAndView;
		} else {
			return new ModelAndView();
		}

	}

	@Override
	public Employ findEmployById(int employId) {
		// TODO Auto-generated method stub
		return employDao.findEmployById(employId);
	}

	@Transactional(readOnly = false)
	@Override
	public int updateEmploy(Employ employ) {
		// TODO Auto-generated method stub
		return employDao.updateEmploy(employ);
	}

	@Transactional(readOnly = false)
	@Override
	public int deleteEmploy(Integer[] caption) {
		// TODO Auto-generated method stub
		if (caption != null) {
			for (Integer integer : caption) {
				employDao.deleteEmploy(integer);
			}
		}
		return 0;
	}
	
	
	@Override
	public Boolean importExcel(String[] thTitle, List<Employ> listValue, String tableTitle, String path) {
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
		for (int i = 0; i < listValue.size(); i++) {
			XSSFRow nextRow = sheet.createRow(i + 2);
			XSSFCell cell2 = nextRow.createCell(0);
			cell2.setCellValue(listValue.get(i).getId().toString());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(1);
			cell2.setCellValue(listValue.get(i).getDept().getName());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(2);
			cell2.setCellValue(listValue.get(i).getJob().getName());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(3);
			cell2.setCellValue(listValue.get(i).getSalary());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(4);
			cell2.setCellValue(listValue.get(i).getName());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(5);
			cell2.setCellValue(listValue.get(i).getCard_id());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(6);
			cell2.setCellValue(listValue.get(i).getAddress());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(7);
			cell2.setCellValue(listValue.get(i).getTel());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(8);
			cell2.setCellValue(listValue.get(i).getQQ_num());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(9);
			cell2.setCellValue(listValue.get(i).getEmail());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(10);
			cell2.setCellValue(listValue.get(i).getParty());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(11);
			cell2.setCellValue(format.format(listValue.get(i).getBirthday()));
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(12);
			cell2.setCellValue(listValue.get(i).getRace());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(13);
			cell2.setCellValue(listValue.get(i).getEducation());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(14);
			cell2.setCellValue(listValue.get(i).getSpeciality());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(15);
			cell2.setCellValue(listValue.get(i).getHobby());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(16);
			cell2.setCellValue(listValue.get(i).getRemark());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(17);
			if (listValue.get(i).getSex()==1) {
				cell2.setCellValue("男");
				cell.setCellStyle(cellStyle2);
			} else {
				cell2.setCellValue("女");
				cell.setCellStyle(cellStyle2);
			}
			
			cell2 = nextRow.createCell(18);
			cell2.setCellValue(listValue.get(i).getPhone());
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(19);
			cell2.setCellValue(format.format(listValue.get(i).getCreate_date()));
			cell.setCellStyle(cellStyle2);
			
			cell2 = nextRow.createCell(13);
			cell2.setCellValue(listValue.get(i).getEducation());
			cell.setCellStyle(cellStyle2);
//			设置列高,(第几列，像素)
			sheet.setColumnWidth((short) i, (short) 3000);
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
		return true;
	}

}
