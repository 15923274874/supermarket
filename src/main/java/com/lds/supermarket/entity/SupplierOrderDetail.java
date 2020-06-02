package com.lds.supermarket.entity;

/**
 * 订单详情类
 */
public class SupplierOrderDetail {
    private Integer id;//订单详情id
    private Integer commodityId;//所属商品
    private String commodityName;//所属商品名
    private Integer supplierOrderId;//所属订单id
    private Integer price;//本次单价
    private Integer num;//本次数量
    private Float sumPrice;//总价
    private String edit;//备注
    private Commodity commodity;//商品

    @Override
    public String toString() {
        return "SupplierOrderDetail{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", commodityName=" + commodityName +
                ", supplierOrderId=" + supplierOrderId +
                ", price=" + price +
                ", num=" + num +
                ", sumPrice=" + sumPrice +
                ", edit='" + edit + '\'' +
                ", commodity=" + commodity +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getSupplierOrderId() {
        return supplierOrderId;
    }

    public void setSupplierOrderId(Integer supplierOrderId) {
        this.supplierOrderId = supplierOrderId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Float sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
