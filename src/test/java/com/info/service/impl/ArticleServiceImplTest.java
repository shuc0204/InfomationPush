//package com.info.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.info.model.Article;
//import com.info.model.ArticleResultList;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Arrays;
//import java.util.List;
//
///** 
//* ArticleServiceImpl Tester. 
//* 
//* @author <Authors name> 
//* @version 1.0
//*/ 
//public class ArticleServiceImplTest { 
//
//    ArticleServiceImpl articleService = new ArticleServiceImpl();
//ArticleSearchServiceImpl articleSearchService = new ArticleSearchServiceImpl();
//
//@Before
//public void before() throws Exception { 
//} 
//
//@After
//public void after() throws Exception { 
//} 
//
//@Test
//public void test(){
//    List<String> keyWordList = Arrays.asList(new String[]{"鏁欒偛", "淇℃伅鎶�湳", "澶ф暟鎹�, "浜戣绠�, "浜掕仈缃�});
//    List<Article> a2 = articleSearchService.searchArticleByKeyWord(keyWordList, 3);
//    System.out.println(JSONObject.toJSONString(a2, true));
//    for (Article a:
//         a2) {
//        String[] keyWords = articleService.getKeyWords(a);
//        System.out.println(Arrays.asList(keyWords));
//    }
//    System.out.println(a2.size());
//}
//
//} 
