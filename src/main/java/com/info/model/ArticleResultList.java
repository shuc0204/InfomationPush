package com.info.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class ArticleResultList {
	
	private List<Article> data;
	private Long total;
	private Integer pageSize;
	private Integer currentPage;
	private Long pageCount;
	
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public List<Article> getData() {
		if(data==null){
			data = new ArrayList<>();
		}
		return data;
	}
	public void setData(List<Article> data) {
		this.data = data;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this,true);
	}
	
	
}
