package com.timeday.time.pojo;

import java.util.Date;

public class UserCode {
    private Integer id;

    private String phone;

    private Integer code;

    private Integer codeType;

    private Date startDate;

    public UserCode(Integer id, String phone, Integer code, Integer codeType, Date startDate) {
        this.id = id;
        this.phone = phone;
        this.code = code;
        this.codeType = codeType;
        this.startDate = startDate;
    }

    public UserCode() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}