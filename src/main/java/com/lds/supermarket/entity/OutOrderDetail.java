package com.lds.supermarket.entity;

/**
 * 订单详情类
 */
public class OutOrderDetail {
    private Integer id;//订单详情id
    private Integer commodityId;//所属商品
    private Integer outOrderId;//所属订单id
    private Integer price;//本次单价
    private Integer num;//本次数量
    private Float sumPrice;//总价
    private Commodity commodity;//商品

    @Override
    public String toString() {
        return "OutOrderDetail{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", outOrderId=" + outOrderId +
                ", price=" + price +
                ", num=" + num +
                ", sumPrice=" + sumPrice +
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(Integer outOrderId) {
        this.outOrderId = outOrderId;
    }
}
