package com.info.service.impl;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import com.info.model.ArticleFull;
import com.info.util.FileCacheUtil;
import com.info.util.QueryStringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.info.model.Article;
import com.info.model.ArticleResultList;
import com.info.service.ArticleService;


@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

	/**
	 * 文章地址链接的前缀
	 *  文章的地址用过前缀加  文章的3个属性 来标识唯一的文章地址
	 */
	static final String ArticleUrlPrefix = "http://kns.cnki.net/KCMS/detail/detail.aspx";
	/**
	 * 缓存对象  ，每次请求的数据缓存到Map里面 第二次访问时可以直接从缓存里面取，而不是每次从网上爬
	 */
	static private ConcurrentHashMap<String,Article> cache = new ConcurrentHashMap();

	/**
	 * 文章搜索服务对象 提供文章搜索功能
	 */
	ArticleSearchServiceImpl articleSearchService = new ArticleSearchServiceImpl();

	@Override
	public ArticleResultList getArticleByCategoryCode(String categoryCode,Integer curPage,Integer pageSize) {
		return  articleSearchService.getArticleByCategoryCode(categoryCode,curPage,pageSize);
	}

    /**
     * 通过文章属性获取文章对象
     * @param dbName
     * @param dbCode
     * @param fileName
     * @return
     */
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
	public String[] getKeyWords(Article article) {
        ArticleFull articleFullInfo = getArticleFullInfo(article);
        if(articleFullInfo == null){
            return new String[]{};
        }
        return articleFullInfo.getKeyWords();
	}

    /**
     * 根据文章的URL设置文章的属性
     * @param article 文章对象
     * @param href 文章链接
     * @return 设置后的文章链接
     */
	public static Article setArticleCode(Article article, String href) {
		String filename = QueryStringUtil.getAttrFrom(href, "filename");
		String dbcode = QueryStringUtil.getAttrFrom(href, "dbcode");
		String dbname = QueryStringUtil.getAttrFrom(href, "dbname");
		setArticleCode(article,dbname,dbcode,filename);
		return  article;
	}

    /**
     *  通过文章对象获取 文章扩展对象 扩展后的对象会有关键字信息
     * @param article 简单文章对象
     * @return  扩展后的文章对象
     */
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

    /**
     * 根据文章对象属性 生成设置文章对象 并构建文章的Url信息
     * @param article  空文章对象
     * @param dbName 文章的 dbName
     * @param dbCode 文章的 dbCode
     * @param fileName 文章的 fileName
     * @return 设置后的文章对象
     */
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
