package com.info.model;

public class Keyword extends KeywordKey {
    private Long updatetime;

    private Integer keycount;

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getKeycount() {
        return keycount;
    }

    public void setKeycount(Integer keycount) {
        this.keycount = keycount;
    }
}