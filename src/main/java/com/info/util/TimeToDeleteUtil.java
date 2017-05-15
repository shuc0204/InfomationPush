package com.info.util;

import java.util.Date;
import javax.annotation.Resource;
import com.info.dao.KeywordMapper;

/**
 * TimeToDeleteUtil ������
 * ʵ�ַ����������� ��ʱʵ��ɾ�����ݿ����Ѿ���ʱ�Ĺؼ�
 * @author Administrator
 *
 */
public class TimeToDeleteUtil {
	//springע��Dao 
	@Resource KeywordMapper keywordDao;       
	//ɾ��ʱ�䣬ɾ�������ǰ�Ĺؼ��֣���λΪ���ӣ�����jdbc.properties�н�������
	private String deltime;
		
	 public String getDeltime() {
		return deltime;
	}
	public void setDeltime(String deltime) {
		this.deltime = deltime;
	}


	//����spring-mybatis.xml���õ�ʱ�̾ͻᱻ���ô˷���
    public void autoRun(){
    	  	
        System.out.println("It's time to run :" + new Date().toString());
        
       // ִ�������߼� ʵ��ɾ����ʱ�Ĺؼ��ּ�¼
       int delCount = keywordDao.deleteOverdueKeyword(Integer.parseInt(getDeltime()));
       
       if(delCount>0){
    	   System.out.println("keyword��ɾ����.........");
       }
        
    }
}
