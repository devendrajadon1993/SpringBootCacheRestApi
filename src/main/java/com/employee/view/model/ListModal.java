package com.employee.view.model;

import java.util.ArrayList;
import java.util.List;

public class ListModal<T> {

	private int pageNo;
	private int pageSize;
	private Long totalRecords;
	private List<T> data = new ArrayList<>();

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ListModal [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalRecords=" + totalRecords + ", data="
				+ data + "]";
	}

}
