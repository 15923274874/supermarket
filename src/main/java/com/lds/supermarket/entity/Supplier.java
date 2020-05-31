package com.lds.supermarket.entity;

import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 供货商类
 */
public class Supplier {
    private Integer id;//供货商id
    private String supplierName;//供货商名
    private String supplierEdit;//供货商简介
    private String  supplierPeople;//联系人
    private String supplierPhone;//联系电话
    private String email;//联系邮箱
    private String address;//联系地址
    private List<Commodity> commodityList;

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", supplierEdit='" + supplierEdit + '\'' +
                ", supplierPeople='" + supplierPeople + '\'' +
                ", supplierPhone='" + supplierPhone + '\'' +
                ", emaile='" + email + '\'' +
                ", address='" + address + '\'' +
                ", commodityList=" + commodityList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEdit() {
        return supplierEdit;
    }

    public void setSupplierEdit(String supplierEdit) {
        this.supplierEdit = supplierEdit;
    }

    public String getSupplierPeople() {
        return supplierPeople;
    }

    public void setSupplierPeople(String supplierPeople) {
        this.supplierPeople = supplierPeople;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
