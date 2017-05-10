package com.info.service.impl;

import com.info.model.Article;
import com.info.model.ArticleResultList;
import com.info.service.ArticleSearchService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wayne on 2017/5/10.
 */
public class ArticleSearchServiceImpl implements ArticleSearchService {
    static final String ArticleUrlPrefix = "http://kns.cnki.net/KCMS/detail/detail.aspx?dbcode=CJFQ&dbname=CJFD2014&filename=";


    @Override
    public ArticleResultList queryByCategoryCode(String categoryCode) {
        return getArticleByCategoryCode(null,categoryCode,1,20);
    }

    @Override
    public ArticleResultList queryByKeyWords(List<String> keyWordList) {
        return getArticleByCategoryCode(keyWordList,null,1,20);
    }

    private static String getUrlFileName(String attr) {

        Pattern pattern = Pattern.compile("FileName=(\\w*)");
        Matcher matcher = pattern.matcher(attr);
        if(matcher.find())
            return (matcher.group(1));
        return null;
    }


    public ArticleResultList getArticleByCategoryCode(List<String> keyWord, String categoryCode,Integer curPage,Integer pageSize) {

        String string=null;
        if(curPage == null){
            curPage=1;
        }
        if(pageSize == null){
            pageSize= 20;
        }

        List<Article> articleList = new ArrayList<Article>();



//        action:
//        NaviCode:*
//        ua:1.21
//        PageName:ASP.brief_result_aspx
//        DbPrefix:SCDB
//        DbCatalog:中国学术文献网络出版总库
//        ConfigFile:SCDB.xml       SCDBINDEX.xml
//        db_opt:CJFQ,CDFD,CMFD,CPFD,IPFD,CCND
//        txt_1_sel:SU
//        txt_1_value1:淀粉
//        txt_1_value2:阿
//        txt_1_relation:#CNKI_OR
//        txt_1_special1:=
//        txt_2_sel:TI
//        txt_2_value1:asdf
//        txt_2_value2:gasd
//        txt_2_logical:or
//        txt_2_relation:#CNKI_OR
//        txt_2_special1:=

        String target = "http://kns.cnki.net/kns/request/SearchHandler.ashx?action=&ua=1.15" +
                "&PageName=ASP.brief_default_result_aspx" +
                "&DbPrefix=SCDB" +
                "&DbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93" +
                "&ConfigFile=SCDB.xml" +
                "&db_opt=CJFQ%2CCDFD%2CCMFD%2CCPFD%2CIPFD%2CCCND" +
                "&his=0";

        try {
            target += "&__="+URLEncoder.encode(new Date().toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(categoryCode!=null){
            target += "&NaviCode="+categoryCode;
        }

        if(keyWord!=null){
            target += keyWordListToQueryString(keyWord,"11222");
        }

        String target2 = "http://kns.cnki.net/kns/brief/brief.aspx?t="+new Date().getTime()+"&pagename=";

        Document content = null;
        try {
            Connection connect =  Jsoup.connect(target);
            connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");
            Connection.Response response = connect.timeout(30000).execute();
            if(curPage==1){
                string = target2+response.body();
                string += "&curpage="+curPage+"&RecordsPerPage="+pageSize;
            }else{
                string="http://kns.cnki.net/kns/brief/brief.aspx?t="+new Date().getTime()+"&curpage="+curPage+"&RecordsPerPage="+pageSize+"&QueryID=3&ID=&turnpage=1&tpagemode=L&dbPrefix=SCDB&Fields=&DisplayMode=listmode&PageName=ASP.brief_default_result_aspx#J_ORDER&";
            }
            content = connect.url(string).cookies(response.cookies()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links=content.select("[class=fz14]");
        for (Element element : links) {
            Article article=new Article();
            article.setTitle(element.text());
            String fileCode = getUrlFileName(element.attr("href"));
            article.setCode(fileCode);
            article.setUrl(ArticleUrlPrefix + fileCode);
            articleList.add(article);
//            cache.put(fileCode,article);
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

    private static String urlEncode(String appendTarget,String key,String txt){
        try {
            return appendTarget + "&"+key+"="+URLEncoder.encode(txt, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return appendTarget;
        }
    }

    private static StringBuilder urlEncode(StringBuilder appendTarget,String key,String txt){
        try {
            return appendTarget.append( "&"+key+"="+URLEncoder.encode(txt, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            return appendTarget;
        }
    }

    private String keyWordListToQueryString(List<String> keyWords ,String relaTion ) {
//        txt_1_sel:SU
//        txt_1_value1:淀粉
//        txt_1_value2:阿
//        txt_1_freq2:5
//        txt_1_relation:#CNKI_OR
//        txt_1_special1:=
//        txt_2_sel:TI
//        txt_2_value1:asdf
//        txt_2_value2:gasd
//        txt_2_logical:or
//        txt_2_freq1:5
//        txt_2_relation:#CNKI_AND
//        txt_2_special1:%
//        txt_3_sel:KY
//        txt_3_value1:d
//        txt_3_value2:f
//        txt_3_logical:not
//        txt_3_relation:#CNKI_NOT
//        txt_3_special1:=
//        txt_4_sel:AB
//        txt_4_value1:f
//        txt_4_value2:da
//        txt_4_logical:and
//        txt_4_freq1:2
//        txt_4_freq2:2
//        txt_4_relation:#CNKI_OR
//        txt_4_special1:%


        StringBuilder sb = new StringBuilder();
        int i=1;
        Iterator<String> it = keyWords.iterator();
        while (it.hasNext()){
            String key = it.next();
            urlEncode(sb,"txt_"+i+"_sel","SU");
            urlEncode(sb,"txt_"+i+"_value1",key);
            urlEncode(sb,"txt_"+i+"_special1","%");
            if(it.hasNext()){
                urlEncode(sb,"txt_"+i+"_value2",key);
                urlEncode(sb,"txt_"+i+"_relation","#CNKI_OR");
                if(i>1){
                    urlEncode(sb,"txt_"+i+"_logical","or");
                }
            }
            i++;
        }
        return sb.toString();
    }

    String getRelationFromString(String s,short p,short p2){
        if(s.length() >= p*2+p2-2  ){
            char c = s.charAt(p * 2 + p2 - 1);
            if(c =='0'){
                return p2==1?"#CNKI_AND":"and";
            }else if(c =='3'){
                return p2==1?"#CNKI_NOT":"not";
            }else {
                return p2==1?"#CNKI_OR":"or";
            }
        }else{
            return p2==1?"#CNKI_OR":"or";
        }

    }


}
