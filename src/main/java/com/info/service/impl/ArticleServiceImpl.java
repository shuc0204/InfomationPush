package com.info.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Override
	public ArticleResultList getArticleByCategoryCode(String categoryCode,Integer curPage,Integer pageSize) {
		
		String string=null;
		if(curPage == null){
			curPage=1;			
		}
		if(pageSize == null){
			pageSize= 20;
		}
		
		// TODO Auto-generated method stub
		List<Article> articleList = new ArrayList<Article>();
		//A002_2
		String target = "http://kns.cnki.net/kns/request/SearchHandler.ashx?action=&NaviCode="+categoryCode+"&ua=1.15&PageName=ASP.brief_default_result_aspx&DbPrefix=SCDB&DbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93&ConfigFile=SCDBINDEX.xml&his=0&__=Sat%20May%2006%202017%2010%3A48%3A53%20GMT%2B0800";
		String target2 = "http://kns.cnki.net/kns/brief/brief.aspx?t="+new Date().getTime()+"&pagename=";

		Document content = null;
		try {
			Connection connect =  Jsoup.connect(target);
			connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");
			Response response = connect.timeout(30000).execute();
			if(curPage==1){
				string = target2+response.body();
				string += "&curpage="+curPage+"&RecordsPerPage="+pageSize;
			}else{
				string="http://kns.cnki.net/kns/brief/brief.aspx?t="+new Date().getTime()+"&curpage="+curPage+"&RecordsPerPage="+pageSize+"&QueryID=3&ID=&turnpage=1&tpagemode=L&dbPrefix=SCDB&Fields=&DisplayMode=listmode&PageName=ASP.brief_default_result_aspx#J_ORDER&";
			}						
			content = connect.url(string).cookies(response.cookies()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements links=content.select("[class=fz14]");	
		for (Element element : links) {
			Article article=new Article();
			article.setTitle(element.text());
			article.setUrl("http://kns.cnki.net/KCMS/detail/detail.aspx?dbcode=CJFQ&dbname=CJFD2014&filename="+getUrlFileName(element.attr("href")));
			articleList.add(article);
		}
		//          /((\d+,*)+)/
		//System.out.println(content.outerHtml());
		String text = content.select(".TitleLeftCell > div:nth-child(1)").text();
		String regex = "((\\d+,*)+)";
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(text);
        if(mMatcher.find()){
        	text = mMatcher.group(1).replace(",", "");
        }else{
        	text = "0";
        }
        
        ArticleResultList articleResultList = new ArticleResultList();
       
        articleResultList.setData(articleList);
        articleResultList.setTotal(Long.parseLong(text));
        articleResultList.setPageSize(pageSize);
        articleResultList.setCurrentPage(curPage);
        
        if(Long.parseLong(text)%pageSize==0){
        	articleResultList.setPageCount(Long.parseLong(text)/pageSize);
        }else{
        	articleResultList.setPageCount(Long.parseLong(text)/pageSize+1);
        }       		
		return articleResultList;
	}
	
	private static String getUrlFileName(String attr) {
		// TODO Auto-generated method stub
		
		Pattern pattern = Pattern.compile("FileName=(\\w*)");
		Matcher matcher = pattern.matcher(attr);
		if(matcher.find()) 
			return (matcher.group(1));
		return null;
	}

	@Override
	public ArticleResultList getArticleByCategoryCode(String categoryCode) {
		// TODO Auto-generated method stub
		return getArticleByCategoryCode(categoryCode,null,null);
	}
	

}