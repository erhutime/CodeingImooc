package com.timeday.time.pojo;

public class UserPassWord {
    private Integer id;

    private String encryptionPassword;

    private Integer userId;

    public UserPassWord(Integer id, String encryptionPassword, Integer userId) {
        this.id = id;
        this.encryptionPassword = encryptionPassword;
        this.userId = userId;
    }

    public UserPassWord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEncryptionPassword() {
        return encryptionPassword;
    }

    public void setEncryptionPassword(String encryptionPassword) {
        this.encryptionPassword = encryptionPassword == null ? null : encryptionPassword.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}