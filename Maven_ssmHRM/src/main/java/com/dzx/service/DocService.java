package com.dzx.service;

import java.util.List;

import com.dzx.domain.Doc;

public interface DocService {
	public int fileUpload(Doc doc);

	public List<Doc> selectFile(Doc doc);

	public int deleteFile(Integer[] caption);

	// 查找所有文件
	public List<Doc> selectAllFile(Doc doc);

	// 封装导出方法
	public Boolean importExcel(String[] thTitle, List<Doc> listValue, String tableTitle, String path);
}
