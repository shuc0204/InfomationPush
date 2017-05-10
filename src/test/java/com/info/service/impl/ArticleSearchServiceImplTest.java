package com.info.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.info.model.ArticleResultList;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * ArticleSearchServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class ArticleSearchServiceImplTest {
    ArticleSearchServiceImpl articleSearchService = new ArticleSearchServiceImpl();


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: queryByCategoryCode(String categoryCode)
     */

    @Test
    public void testGetRelationFromString() {
        for (int i = 1; i < 7; i++) {
            System.out.println(i + ":\t" + articleSearchService.getRelationFromString("1232213", i, 1) + " \t" + articleSearchService.getRelationFromString("1232213", i, 2));
        }


    }

    @Test
    public void testQueryByCategoryCode() throws UnsupportedEncodingException {
        ArticleResultList a = articleSearchService.queryByCategoryCode("A");
        System.out.println(JSONObject.toJSONString(a, true));

    }


    @Test
    public void testQueryByKeyWords() throws UnsupportedEncodingException {
//    String[] zgrk201311006s = articleService.getKeyWords("ZGRK201311006");
//    System.out.println(Arrays.asList(zgrk201311006s));
//    System.out.println(URLEncoder.encode(new Date().toString(), "utf-8") );
        List<String> keyWordList = Arrays.asList(new String[]{"教育", "信息技术", "大数据", "云计算", "互联网"});
        List a = articleSearchService.searchArticleByKeyWord(keyWordList, 34);
        System.out.println(JSONObject.toJSONString(a, true));
        System.out.println(a.size());
        a = articleSearchService.searchArticleByKeyWord(keyWordList, 3);
        System.out.println(JSONObject.toJSONString(a, true));
        System.out.println(a.size());

    }

} 
