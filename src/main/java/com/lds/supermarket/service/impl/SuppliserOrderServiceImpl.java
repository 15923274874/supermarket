package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.CommodityDao;
import com.lds.supermarket.dao.SupplierDao;
import com.lds.supermarket.dao.SupplierOrderDao;
import com.lds.supermarket.dao.SupplierOrderDetailDao;
import com.lds.supermarket.entity.*;
import com.lds.supermarket.service.SupplierOrderService;
import com.lds.supermarket.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SuppliserOrderServiceImpl implements SupplierOrderService {

    @Resource
    private SupplierOrderDao supplierOrderDao;

    @Resource
    private SupplierOrderDetailDao supplierOrderDetailDao;

    @Resource
    private CommodityDao commodityDao;

    @Override
    @Transactional
    public boolean save(SupplierOrder supplierOrder) {
            if (supplierOrder.getId() != null){
                SupplierOrder order = supplierOrderDao.getSupplierOrderById(supplierOrder.getId());
                if(supplierOrder.getTime() == null){supplierOrder.setTime(order.getTime());}
                if(supplierOrder.getTitle() == null){supplierOrder.setTitle(order.getTitle());}
                if(supplierOrder.getSupplierId() == null){supplierOrder.setSupplierId(order.getSupplierId());}
                if(supplierOrder.getTypeNum() == null){supplierOrder.setTypeNum(order.getTypeNum());}
                if(supplierOrder.getCommodityNum() == null){supplierOrder.setCommodityNum(order.getCommodityNum());}
                if(supplierOrder.getPrice() == 0){supplierOrder.setPrice(order.getPrice());}
                if(supplierOrder.getState() == null){supplierOrder.setState(order.getState());}
                if(supplierOrder.getEdit() == null){supplierOrder.setEdit(order.getEdit());}
                if(supplierOrder.getInfo() == null){supplierOrder.setInfo(order.getInfo());}
                supplierOrderDao.update(supplierOrder);
                if("已确认".equals(supplierOrder.getState())){
                    //获取订单详情列表
                    List<SupplierOrderDetail> list = supplierOrderDetailDao.getSupplierOrderDetailBySupplierOrderId(supplierOrder.getId());
                     //根据商品列表循环更新库存
                    for(SupplierOrderDetail supplierOrderDetail :list){
                        commodityDao.updateCommodityStock(supplierOrderDetail.getCommodityId(),supplierOrderDetail.getNum());
                    }
                }
            }
            return true;
    }

    @Override
    public Page<SupplierOrder> getAllSupplierOrder(User user) {

        Page<SupplierOrder> page = new Page<SupplierOrder>();
        page.setCountSum(supplierOrderDao.getSupplierOrderCount());//设置总记录条数
        page.setNowPage(1);//设置当前页码
        page.setCountNum(page.getCountSum());//设置显示记录条数
        page.setPageSum();//设置总页码
        if(user.getJurisdiction() <  3){
            page.setList(supplierOrderDao.getAllSupplierOrder());
        }else {
            page.setList(supplierOrderDao.getAllSupplierOrderBySupplierId(user.getSupplierId()));
        }
        return page;
    }

    @Override
    public Page<SupplierOrder> getAllSupplierOrder(User user, Integer nowPage, Integer size) {

        Page<SupplierOrder> page = new Page<SupplierOrder>();
        page.setCountSum(supplierOrderDao.getSupplierOrderCount());//设置总记录条数
        page.setNowPage(nowPage);//设置当前页码
        page.setCountNum(size);//设置显示记录条数
        page.setPageSum();//设置总页码
        Integer countStart = (page.getNowPage()-1) * size;//设置开始查询记录数
       if(user.getJurisdiction() < 3){
           page.setList(supplierOrderDao.getAllSupplierOrderByPage(countStart,size));
       }else {
           page.setList(supplierOrderDao.getAllSupplierOrderByPageAndSupplierId(user.getSupplierId(),countStart,size));
       }
        return page;
    }

    @Override
    @Transactional
    public boolean del(Integer id) {
        try {
             if(id != null){
                 supplierOrderDao.delete(id);
                 supplierOrderDetailDao.deleteBySupplierOrderId(id);
                 return true;
             }else{
                 return false;
             }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SupplierOrder getSupplierOrderById(Integer id) {
        return supplierOrderDao.getSupplierOrderById(id);
    }
}
