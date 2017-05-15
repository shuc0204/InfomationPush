package com.info.model;

/**
 * ArticleFull为文章有关的关键字类
 * 继承于Article类
 * Created by wayne on 2017/5/10.
 */
public class ArticleFull extends  Article {
	//关键字
    private String[] keyWords;

    public String[] getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String[] keyWords) {
        this.keyWords = keyWords;
    }
}
