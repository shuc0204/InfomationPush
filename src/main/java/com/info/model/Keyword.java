package com.info.model;

/**
 * Keyword类为保存在数据库中的关键字 实体类
 * 继承了KeywordKey
 * @author Administrator
 *
 */
 
public class Keyword extends KeywordKey {
	
	//关键字插入数据库的最新更新时间
	private Long updatetime;		
	//此关键字的出现次数统计
    private Integer keycount;

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getKeycount() {
        return keycount;
    }

    public void setKeycount(Integer keycount) {
        this.keycount = keycount;
    }
}