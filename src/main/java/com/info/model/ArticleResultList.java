package com.info.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * ArticleResulList�࣬�Ƿ���ҳ��ʵ�ַ�ҳ�Ľ������
 * @author Administrator
 *
 */
public class ArticleResultList {
	//�����б�
	private List<Article> data;
	//�ܼ�¼����
	private Long total;
	//ÿҳ��ʾ��¼����
	private Integer pageSize;
	//��ǰҳ��
	private Integer currentPage;
	//ҳ��
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
