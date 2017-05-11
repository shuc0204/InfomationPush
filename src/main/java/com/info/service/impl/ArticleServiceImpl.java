package com.info.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.info.model.ArticleFull;
import com.info.util.FileCacheUtil;
import com.info.util.QueryStringUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.info.model.Article;
import com.info.model.ArticleResultList;
import com.info.service.ArticleService;


@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

	static final String ArticleUrlPrefix = "http://kns.cnki.net/KCMS/detail/detail.aspx";
	static private ConcurrentHashMap<String,Article> cache = new ConcurrentHashMap();
	ArticleSearchServiceImpl articleSearchService = new ArticleSearchServiceImpl();

	@Override
	public ArticleResultList getArticleByCategoryCode(String categoryCode,Integer curPage,Integer pageSize) {
		return  articleSearchService.getArticleByCategoryCode(categoryCode,curPage,pageSize);
	}

	@Override
	public Article getArticleByCode(String dbName,String dbCode,String fileName) {
		String key = dbName + dbCode + fileName;
		Article article = cache.get(key);
        if(article ==null)
        {
			Article modelArticle = new Article();
			modelArticle.setDbName(dbName);
			modelArticle.setDbCode(dbCode);
			modelArticle.setFileName(fileName);
            article = getArticleFullInfo(modelArticle);
            cache.put(key,article);
        }
        return article;
	}


	@Override
	public ArticleResultList getArticleByCategoryCode(String categoryCode) {
		return getArticleByCategoryCode(categoryCode,null,null);
	}

	@Override
	public String[] getKeyWords(Article article) {
        ArticleFull articleFullInfo = getArticleFullInfo(article);
        if(articleFullInfo == null){
            return new String[]{};
        }
        return articleFullInfo.getKeyWords();
	}

	public static Article setArticleCode(Article article, String href) {
		String filename = QueryStringUtil.getAttrFrom(href, "filename");
		String dbcode = QueryStringUtil.getAttrFrom(href, "dbcode");
		String dbname = QueryStringUtil.getAttrFrom(href, "dbname");
		setArticleCode(article,dbname,dbcode,filename);
		return  article;
	}


	private ArticleFull getArticleFullInfo(Article article){
		String dbName = article.getDbName();
		String dbCode = article.getDbCode();
		String fileName = article.getFileName();
		String key = dbName + dbCode
				+ fileName;
		Article cacheArticle = cache.get(key);

        if(cacheArticle!=null && cacheArticle instanceof  ArticleFull){
            return (ArticleFull) cacheArticle;
        }
        ArticleFull articleFull = new ArticleFull();
        String articleUrl = setArticleCode(articleFull,dbName,dbCode,fileName).getUrl();
        try {
            Document document =null;

            final String tempFileName = "article_"+key + ".html";
            String tempContent = FileCacheUtil.getTempContent(tempFileName);
            if(tempContent==null){
                tempContent =  Jsoup.connect(articleUrl).timeout(6000).execute().body();
                FileCacheUtil.writeTempCacheOnce(tempFileName,tempContent);
            }
            document = Jsoup.parse(tempContent);
            Elements elements = document.select("#catalog_KEYWORD~a");
            String[] kws = new String[elements.size()];
            for (int i = 0; i < kws.length; i++) {
                String text = elements.get(i).text();

                kws[i] = text.trim().replaceFirst(";","");
            }
            articleFull.setKeyWords(kws);
            String title = document.select("#mainArea > div.wxmain > div.wxTitle > h2").text();
            articleFull.setTitle(title);
            articleFull.setUrl(articleUrl);
            cache.put(key,articleFull);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  articleFull;
    }

	private static Article setArticleCode(Article article, String dbName, String dbCode, String fileName) {
		article.setFileName(fileName);
		article.setDbCode(dbCode);
		article.setDbName(dbName);
		String articlePara = "";
		if (fileName != null) {
			articlePara += "&filename=" + fileName;
		}
		if (dbCode != null) {
			articlePara += "&dbcode=" + dbCode;
		}
		if (dbName != null) {
			articlePara += "&dbname=" + dbName;
		}
		String articleUrl = ArticleUrlPrefix + "?" + articlePara.substring(1);
		article.setUrl(articleUrl);
		return  article;
	}


}
