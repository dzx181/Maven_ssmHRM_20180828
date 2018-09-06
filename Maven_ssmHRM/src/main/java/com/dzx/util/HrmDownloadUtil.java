package com.dzx.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HrmDownloadUtil {
	public static ResponseEntity<byte[]> download(String downloadPath, String filename) throws IOException {
		// �ļ�����
		File file = new File(downloadPath);
		// ������ ��������
		String encodingFilename = new String(filename.getBytes("utf-8"), "iso-8859-1");
		// ����ͷ��attachment(���ط�ʽ)��application/octet-stream
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", encodingFilename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// ����ResponseEntity����,CREATED(201״̬��)�����Ѿ���ʵ�֣�������һ���µ���Դ�Ѿ������������Ҫ������������ URI
		// �Ѿ���Location ͷ��Ϣ���ء�
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
