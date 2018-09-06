package com.dzx.domain;

import com.dzx.util.HrmConstants;

public class PageModel {
	
	//��ǰҳ��
	private int pageIndex;
	//�ܼ�¼����
	private int totalRecord;
	//ÿҳ��������¼
	private int pageSize;
	//��ҳ��
	private int totalPageSum;
	//����ҳ��ĵ�ǰҳ��ֵ
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageIndex() {
		// ��ǰҳ��>=0����Ϊ��һҳ
		/*this.pageIndex = (page <= 0 ? 1 : page);*/
		if (page<=0) {
			page=1;
			this.pageIndex=page;
		} else {
			this.pageIndex=page;
		}
		// ��ǰҳ��>=��ҳ������ǰҳ�������ҳ��
		/*this.pageIndex = (page >= this.getTotalPageSum() ? this.getTotalPageSum() : page);*/
		if (page >= this.getTotalPageSum()) {
			this.pageIndex=this.getTotalPageSum();
		} else {
			this.pageIndex=page;
		}
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		this.pageSize = this.pageSize <= HrmConstants.PAGE_DEFAULT_SIZE?HrmConstants.PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	

	public int getTotalPageSum() {
		
		if(this.getTotalRecord()<=0)
		{
			this.totalPageSum=0;
		}else
		{
			//������ҳ��
			this.totalPageSum=((this.getTotalRecord()-1)/this.getPageSize())+1;
		}
		return totalPageSum;
	}
	
	public void setTotalPageSum(int totalPageSum) {
		this.totalPageSum = totalPageSum;
	}

	public int getStartRowNum()
	{
		int first=(getPageIndex()-1)*this.getPageSize();
		//�������ݿ��ʼλ��
		if (first<0) {
			return 0;
		} else {
			return first;
		}
		
	}

}
