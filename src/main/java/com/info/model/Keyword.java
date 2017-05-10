package com.info.model;

public class Keyword {
    private Integer kid;

    private String keyword;

    private Integer keycount;

    private Integer uid;

    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getKeycount() {
        return keycount;
    }

    public void setKeycount(Integer keycount) {
        this.keycount = keycount;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}