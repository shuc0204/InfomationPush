package com.info.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wayne on 2017/5/11.
 */
public class QueryStringUtil {

    public static String getAttrFrom(String queryString, String attr) {
//        dbcode=CJFQ&dbname=CJFD2014&filename=
        Pattern pattern = Pattern.compile("[?&]" + attr + "=([^&]*)(?=&|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(queryString);
        if (matcher.find())
            return (matcher.group(1));
        return null;
    }
}
