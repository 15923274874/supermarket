package com.lds.supermarket.entity;



/**
 * 商品信息类
 */
public class Commodity {
    private Integer id;//商品id
    private String commodityName;//商品名
    private String commodityEdit;//商品简介
    private Float  commodityPrice;//商品单价
    private Integer commodityCales;//商品销量
    private Integer commodityStock;//商品库存
    private Integer supplierId;//供应商id
    private Supplier supplier;//商品供应商
    private Integer typeId;//类型id
    private CommodityType commodityType;//商品类型

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", commodityName='" + commodityName + '\'' +
                ", commodityEdit='" + commodityEdit + '\'' +
                ", commodityPrice=" + commodityPrice +
                ", commodityCales=" + commodityCales +
                ", commodityStock=" + commodityStock +
                ", supplierId=" + supplierId +
                ", supplier=" + supplier +
                ", typeId=" + typeId +
                ", commodityType=" + commodityType +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityEdit() {
        return commodityEdit;
    }

    public void setCommodityEdit(String commodityEdit) {
        this.commodityEdit = commodityEdit;
    }

    public Float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Float commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getCommodityCales() {
        return commodityCales;
    }

    public void setCommodityCales(Integer commodityCales) {
        this.commodityCales = commodityCales;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCommodityStock() {
        return commodityStock;
    }

    public void setCommodityStock(Integer commodityStock) {
        this.commodityStock = commodityStock;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public CommodityType getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(CommodityType commodityType) {
        this.commodityType = commodityType;
    }
}
