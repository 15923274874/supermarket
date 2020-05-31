package com.lds.supermarket.entity;

public class User {
    private Integer id;//用户id
    private String account;//账号
    private String passWord;//密码
    private Integer jurisdiction;//权限等级
    private String jurisdictionName;//权限等级
    private Integer supplierId;//如果是供货商，绑定的id
    private Supplier supplier;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                ", jurisdiction=" + jurisdiction +
                ", jurisdictionName='" + jurisdictionName + '\'' +
                ", supplierId=" + supplierId +
                ", supplier=" + supplier +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Integer jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }
}
