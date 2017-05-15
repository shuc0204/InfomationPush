package com.info.util;

import java.util.Date;
import javax.annotation.Resource;
import com.info.dao.KeywordMapper;

/**
 * TimeToDeleteUtil 工具类
 * 实现服务器启动后 定时实现删除数据库中已经过时的关键
 * @author Administrator
 *
 */
public class TimeToDeleteUtil {
	//spring注入Dao 
	@Resource KeywordMapper keywordDao;       
	//删除时间，删除多久以前的关键字，单位为分钟，可在jdbc.properties中进行设置
	private String deltime;
		
	 public String getDeltime() {
		return deltime;
	}
	public void setDeltime(String deltime) {
		this.deltime = deltime;
	}


	//到了spring-mybatis.xml配置的时刻就会被调用此方法
    public void autoRun(){
    	  	
        System.out.println("It's time to run :" + new Date().toString());
        
       // 执行任务逻辑 实现删除超时的关键字记录
       int delCount = keywordDao.deleteOverdueKeyword(Integer.parseInt(getDeltime()));
       
       if(delCount>0){
    	   System.out.println("keyword被删除了.........");
       }
        
    }
}
