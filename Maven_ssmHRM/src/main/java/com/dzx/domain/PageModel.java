package com.dzx.domain;

import com.dzx.util.HrmConstants;

public class PageModel {
	
	//当前页码
	private int pageIndex;
	//总记录条数
	private int totalRecord;
	//每页多少条记录
	private int pageSize;
	//总页数
	private int totalPageSum;
	//接受页面的当前页面值
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageIndex() {
		// 当前页码>=0，则为第一页
		/*this.pageIndex = (page <= 0 ? 1 : page);*/
		if (page<=0) {
			page=1;
			this.pageIndex=page;
		} else {
			this.pageIndex=page;
		}
		// 当前页码>=总页数，则当前页码等于总页数
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
			//计算总页数
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
		//计算数据库初始位置
		if (first<0) {
			return 0;
		} else {
			return first;
		}
		
	}

}
