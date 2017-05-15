package com.info.service.impl; 

import com.info.dao.KeywordMapper;
import com.info.model.Category;
import com.info.service.AnalyseRecordService;
import com.info.service.CategoryService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/** 
* CategoryServiceImpl Tester. 
* 
* @author <Authors name> 
* @version 1.0
*/
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class KeyWordMapperTest {

    @Autowired
    private KeywordMapper keywordMapper;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: convertStreamToString(InputStream is) 
* 
*/ 
@Test
public void testConvertStreamToString() throws Exception { 
//	List analyseAndPush = analyseRecordService.analyseAndPush(null, null);
//	System.out.println(analyseAndPush);
} 

@Test
public void testDeleteTimeKeyword() throws Exception { 
//	List analyseAndPush = analyseRecordService.analyseAndPush(null, null);
//	System.out.println(analyseAndPush);
	int deleteOverdueKeyword = keywordMapper.deleteOverdueKeyword(4);
	System.out.println(deleteOverdueKeyword);
} 

} 
