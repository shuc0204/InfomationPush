package com.info.service.impl; 

import com.info.model.Category;
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

import javax.annotation.Resource;

/** 
* CategoryServiceImpl Tester. 
* 
* @author <Authors name> 
* @version 1.0
*/
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

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
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAllCategoryObject() 
* 
*/ 
@Test
public void testGetAllCategoryObject() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCategoryListFromJsonArray(JSONArray allCategoryObject2) 
* 
*/ 
@Test
public void testGetCategoryListFromJsonArray() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getPrimaryCategoryList() 
* 
*/ 
@Test
public void testGetPrimaryCategoryList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCategoryListByParentCode(JSONArray allCategoryObject2, String parentCategoryCode) 
* 
*/ 
@Test
public void testGetCategoryListByParentCodeForAllCategoryObject2ParentCategoryCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCategoryListByParentCode(String parentCategoryCode) 
* 
*/ 
@Test
public void testGetCategoryListByParentCodeParentCategoryCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCategoryByCode(String categoryCode) 
* 
*/ 
@Test
public void testGetCategoryByCode() throws Exception { 
//TODO: Test goes here...
    Category a003_1 = categoryService.getCategoryByCode("A003_1");
    System.out.println(a003_1);
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 


} 
