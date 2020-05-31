package com.lds.supermarket.entity;

/**
 * 商品类型类
 */
public class CommodityType {

    private Integer id;//类型Id
    private String commodityType;//商品类型
    private String personNo;//负责人工号
    private String personName;//负责人姓名
    private String personPhone;//负责人手机

    @Override
    public String toString() {
        return "CommodityType{" +
                "id=" + id +
                ", commodityType='" + commodityType + '\'' +
                ", personNo='" + personNo + '\'' +
                ", personName='" + personName + '\'' +
                ", personPhone='" + personPhone + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }
}
