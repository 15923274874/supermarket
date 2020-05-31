package com.lds.supermarket.entity;

/**
 * 订单类
 */
public class SupplierOrder {
    private Integer id;//订单ID
    private String time;//订单时间
    private String title;//订单标题
    private Integer supplierId;//供应商id
    private Integer typeNum;//类型数量
    private Integer commodityNum;//商品数量
    private float price;//总价
    private String state;//订单状态
    private String edit;//备注
    private String info;//操作信息
    private Supplier supplier;//具体供货商

    @Override
    public String toString() {
        return "SupplierOrder{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", supplierId=" + supplierId +
                ", typeNum=" + typeNum +
                ", commodityNum=" + commodityNum +
                ", price=" + price +
                ", state='" + state + '\'' +
                ", edit='" + edit + '\'' +
                ", info='" + info + '\'' +
                ", supplier=" + supplier +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
