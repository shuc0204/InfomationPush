package com.info.model;

/**
 * Keyword��Ϊ���������ݿ��еĹؼ��� ʵ����
 * �̳���KeywordKey
 * @author Administrator
 *
 */
 
public class Keyword extends KeywordKey {
	
	//�ؼ��ֲ������ݿ�����¸���ʱ��
	private Long updatetime;		
	//�˹ؼ��ֵĳ��ִ���ͳ��
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