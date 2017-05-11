package com.info.service.impl;

import com.info.model.Article;
import com.info.model.ArticleResultList;
import com.info.service.ArticleSearchService;
import com.info.service.ArticleService;
import com.info.util.QueryStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wayne on 2017/5/10.
 */
@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {

    static final CacheManager cacheManager = new ConcurrentMapCacheManager();


    @Override
    public ArticleResultList queryByCategoryCode(String categoryCode) {
        try {
            return getArticleList(null, categoryCode, 1, 20);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArticleResultList();
        }
    }

    @Override
    public ArticleResultList queryByKeyWords(List<String> keyWordList) {
        try {
            return getArticleList(keyWordList, null, 1, 20);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArticleResultList();
        }
    }

    @Override
    public ArticleResultList queryByKeyWords(List<String> keyWordList, int paggSize, int curPage) {
        try {
            return getArticleList(keyWordList, null, curPage, paggSize);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return getArticleList(null, null, curPage, paggSize);
            } catch (IOException e1) {
                e1.printStackTrace();
                return  new ArticleResultList();
            }
        }
    }

    @Override
    public List<Article> searchArticleByKeyWord(List keywords, int queryCount) {
        // TODO 通过关键字 搜索指定 数目 文章
        if(keywords.size()==0){
            return  new ArrayList<>();
        }
        ArticleResultList articleResultList = queryByKeyWords(keywords, 20, 1);
        if (articleResultList.getData().size() >= queryCount) {
            return articleResultList.getData().subList(0, queryCount);
        } else {
            List<Article> list = articleResultList.getData();
            int i = 2;
            do {
                list.addAll(queryByKeyWords(keywords, 20, i).getData());
                i++;
            } while (list.size() < queryCount);
            return list.subList(0, queryCount);
        }
    }

    public <T> T getFromCache(Object key) {
        Cache.ValueWrapper c1 = cacheManager.getCache("c").get(key);
        if(c1 == null){
            return null;
        }
        Object c = c1.get();
        return (T) c;
    }

    public <T> T putToCache(Object key, T v) {
        cacheManager.getCache("c").put(key, v);
        return v;
    }

    public ArticleResultList getArticleByCategoryCode(String categoryCode, Integer curPage, Integer pageSize) {
        try {
            return getArticleList(null, categoryCode, curPage, pageSize);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArticleResultList();
        }
    }

    public String convertArgumentToKey(List<String> keyWord, String categoryCode, Integer curPage, Integer pageSize) {
        StringBuilder sb = new StringBuilder();

        if (keyWord != null) {
//            Arrays.sort((String[])keyWord.toArray());
            ArrayList<String> sortList = new ArrayList<>(keyWord);
            Collections.sort(sortList);
            String kws = StringUtils.join(sortList, ",");
            sb.append(kws).append(":");
        }
        if (categoryCode != null) {
            sb.append(categoryCode).append(":");
        }
        return sb.toString();
    }

    public ArticleResultList getArticleList(List<String> keyWord, String categoryCode, Integer curPage, Integer pageSize) throws IOException {
        if (curPage == null) {
            curPage = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
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
        Connection connect;
        String searchSession;
        Map<String, String> searchSessionCookie;
        String cacheKey = convertArgumentToKey(keyWord, categoryCode, curPage, pageSize);
        String cacheCookieKey = cacheKey + "_cookie";
        String cacheConnectKey = cacheKey + "_connect";

        if (getFromCache(cacheKey) == null) {
            String target = "http://kns.cnki.net/kns/request/SearchHandler.ashx?action=&ua=1.15" +
                    "&PageName=ASP.brief_default_result_aspx" +
                    "&DbPrefix=SCDB" +
                    "&DbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93" +
                    "&ConfigFile=SCDB.xml" +
                    "&db_opt=CJFQ%2CCDFD%2CCMFD%2CCPFD%2CIPFD%2CCCND" +
                    "&his=0";
            try {
                target += "&__=" + URLEncoder.encode(new Date().toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (categoryCode != null) {
                target += "&NaviCode=" + categoryCode;
            }
            if (keyWord != null) {
                target += keyWordListToQueryString(keyWord, "11222");
            }
            connect = Jsoup.connect(target);
            connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");
            Connection.Response execute = connect.timeout(30000).execute();
            searchSession = execute.body();
            searchSessionCookie = execute.cookies();
            putToCache(cacheKey, execute.body());
            putToCache(cacheCookieKey, searchSessionCookie);
            putToCache(cacheConnectKey, connect);
        } else {
            searchSession = getFromCache(cacheKey);
            searchSessionCookie = getFromCache(cacheCookieKey);
            connect = getFromCache(cacheConnectKey);
        }

        String target2 = "http://kns.cnki.net/kns/brief/brief.aspx?t=" + new Date().getTime() + "&pagename=" + searchSession;
        target2 += "&curpage=" + curPage + "&RecordsPerPage=" + pageSize;
        Document content = connect.url(target2).cookies(searchSessionCookie).get();
        Elements links = content.select("[class=fz14]");
        for (Element element : links) {
            Article article = new Article();
            article.setTitle(element.text());
            String href = element.attr("href");
            ArticleServiceImpl.setArticleCode(article,href);
            articleList.add(article);
        }
        String text = content.select(".TitleLeftCell > div:nth-child(1)").text();
        String regex = "((\\d+,*)+)";
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(text);
        if (mMatcher.find()) {
            text = mMatcher.group(1).replace(",", "");
        } else {
            text = "0";
        }
        ArticleResultList articleResultList = new ArticleResultList();
        articleResultList.setData(articleList);
        articleResultList.setTotal(Long.parseLong(text));
        articleResultList.setPageSize(pageSize);
        articleResultList.setCurrentPage(curPage);
        if (Long.parseLong(text) % pageSize == 0) {
            articleResultList.setPageCount(Long.parseLong(text) / pageSize);
        } else {
            articleResultList.setPageCount(Long.parseLong(text) / pageSize + 1);
        }
        return articleResultList;
    }

    private static StringBuilder urlEncode(StringBuilder appendTarget, String key, String txt) {
        try {
            return appendTarget.append("&" + key + "=" + URLEncoder.encode(txt, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            return appendTarget;
        }
    }

    private String keyWordListToQueryString(List<String> keyWords, String relaTion) {
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
        int i = 1;
        Iterator<String> it = keyWords.iterator();
        while (it.hasNext()) {
            String key = it.next();
            urlEncode(sb, "txt_" + i + "_sel", "SU");
            urlEncode(sb, "txt_" + i + "_value1", key);
            urlEncode(sb, "txt_" + i + "_special1", "%");
            if (it.hasNext()) {
                urlEncode(sb, "txt_" + i + "_value2", key);
                urlEncode(sb, "txt_" + i + "_relation", getRelationFromString(relaTion,i,1));
                if (i > 1) {
                    urlEncode(sb, "txt_" + i + "_logical", getRelationFromString(relaTion,i,2));
                }
            }
            i++;
        }
        return sb.toString();
    }

    public String getRelationFromString(String s, short p, short p2) {
        if (s.length() >= p * 2 + p2 - 2) {
            char c = s.charAt(p * 2 + p2 - 3);
            if (c == '1') {
                return p2 == 1 ? "#CNKI_AND" : "and";
            } else if (c == '3') {
                return p2 == 1 ? "#CNKI_NOT" : "not";
            } else {
                return p2 == 1 ? "#CNKI_OR" : "or";
            }
        } else {
            return p2 == 1 ? "#CNKI_OR" : "or";
        }

    }

    public String getRelationFromString(String s, int p, int p2) {
        return getRelationFromString(s, (short) p, (short) p2);
    }
}
