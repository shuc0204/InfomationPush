package com.info.model;

/**
 * 用户实体类
 * @author Administrator
 *
 */
public class User {
	//用户ID
    private Integer uid;
    //用户账号
    private String username;
    //用户密码
    private String password;
    //用户昵称	
    private String name;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}