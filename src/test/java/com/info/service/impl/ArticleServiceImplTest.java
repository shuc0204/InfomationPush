package com.info.service.impl; 

import com.alibaba.fastjson.JSONObject;
import com.info.model.ArticleResultList;
import com.info.service.ArticleSearchService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

/** 
* ArticleServiceImpl Tester. 
* 
* @author <Authors name> 
* @version 1.0
*/ 
public class ArticleServiceImplTest { 

//    ArticleServiceImpl articleService = new ArticleServiceImpl();
ArticleSearchServiceImpl articleSearchService = new ArticleSearchServiceImpl();

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

@Test
public void testQueryByCategoryCode() throws UnsupportedEncodingException {
//    String[] zgrk201311006s = articleService.getKeyWords("ZGRK201311006");
//    System.out.println(Arrays.asList(zgrk201311006s));
//    System.out.println(URLEncoder.encode(new Date().toString(), "utf-8") );
    ArticleResultList a = articleSearchService.queryByCategoryCode("A");
    System.out.println(JSONObject.toJSONString(a,true));

}


    @Test
    public void testQueryByKeyWords() throws UnsupportedEncodingException {
//    String[] zgrk201311006s = articleService.getKeyWords("ZGRK201311006");
//    System.out.println(Arrays.asList(zgrk201311006s));
//    System.out.println(URLEncoder.encode(new Date().toString(), "utf-8") );
        ArticleResultList a = articleSearchService.queryByKeyWords(Arrays.asList(new String[]{"教育","信息技术","大数据","云计算","互联网"}));
        System.out.println(JSONObject.toJSONString(a,true));

    }

} 
