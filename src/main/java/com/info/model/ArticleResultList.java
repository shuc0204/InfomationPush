package com.info.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * ArticleResulList类，是返回页面实现分页的结果集类
 * @author Administrator
 *
 */
public class ArticleResultList {
	//文章列表
	private List<Article> data;
	//总记录条数
	private Long total;
	//每页显示记录条数
	private Integer pageSize;
	//当前页数
	private Integer currentPage;
	//页数
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
