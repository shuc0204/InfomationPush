package com.info.model;

/**
 * �û�ʵ����
 * @author Administrator
 *
 */
public class User {
	//�û�ID
    private Integer uid;
    //�û��˺�
    private String username;
    //�û�����
    private String password;
    //�û��ǳ�	
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