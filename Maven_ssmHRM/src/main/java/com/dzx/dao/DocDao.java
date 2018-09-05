package com.dzx.dao;

import java.util.List;

import com.dzx.domain.Doc;

public interface DocDao {
	public int fileUpload(Doc doc);
	public List<Doc> selectFile(Doc doc);
	public int selectFileCount(Doc doc);
	public int deleteFile(int caption);
	public List<Doc> selectAllFile(Doc doc);
}
