package com.info.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * QueryStringUtil工具类
 * 为查询相近关键字的正则匹配
 * Created by wayne on 2017/5/11.
 */
public class QueryStringUtil {
	
	//传人需要查询的整段符串，实现返回相关的关键字
    public static String getAttrFrom(String queryString, String attr) {
//        dbcode=CJFQ&dbname=CJFD2014&filename=
        Pattern pattern = Pattern.compile("[?&]" + attr + "=([^&]*)(?=&|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(queryString);
        if (matcher.find())
            return (matcher.group(1));
        return null;
    }
}
