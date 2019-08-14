package com.timeday.time.pojo;

public class UserInfo {

    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String phone;

    private String registerMode;

    private String thirdPartyId;

    public UserInfo(Integer id, String name, Byte gender, Integer age,
                    String phone, String registerMode, String thirdPartyId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.registerMode = registerMode;
        this.thirdPartyId = thirdPartyId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode == null ? null : registerMode.trim();
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }
}