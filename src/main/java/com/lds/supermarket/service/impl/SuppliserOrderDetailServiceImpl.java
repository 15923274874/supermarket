package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.CommodityDao;
import com.lds.supermarket.dao.SupplierOrderDao;
import com.lds.supermarket.dao.SupplierOrderDetailDao;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.entity.SupplierOrderDetail;
import com.lds.supermarket.service.SupplierOrderDetailService;
import com.lds.supermarket.service.SupplierOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SuppliserOrderDetailServiceImpl implements SupplierOrderDetailService {

    @Resource
    private SupplierOrderDetailDao supplierOrderDetailDao;

    @Resource
    private SupplierOrderDao supplierOrderDao;

    @Resource
    private CommodityDao commodityDao;

    @Transactional()
    @Override
    public boolean save(List<SupplierOrderDetail> details,SupplierOrder supplierOrder){

        /**
         * 新建时间并插入数据
         */
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
            supplierOrder.setTime(sdf.format(d));
            //插入数据
            if(supplierOrder.getId() == null){
                supplierOrderDao.insert(supplierOrder);
                for(SupplierOrderDetail supplierOrderDetail : details){
                    supplierOrderDetail.setSupplierOrderId(supplierOrder.getId());
                    //插入具体入库商品
                    supplierOrderDetailDao.insert(supplierOrderDetail);
                }
            }else{
                //删除数据
                supplierOrderDao.update(supplierOrder);
                //删除以前的订单列表重新加载
                supplierOrderDetailDao.deleteBySupplierOrderId(supplierOrder.getId());
                for(SupplierOrderDetail supplierOrderDetail : details){
                    supplierOrderDetail.setSupplierOrderId(supplierOrder.getId());
                    supplierOrderDetailDao.insert(supplierOrderDetail);
                }
            }
            return  true;
    }

    @Override
    public List<SupplierOrderDetail> getSupplierOrderDetailBySupplierOrderId(Integer supplierOrderId) {
        List<SupplierOrderDetail> list = supplierOrderDetailDao.getSupplierOrderDetailBySupplierOrderId(supplierOrderId);

        /**
         * 循环计算每个总价
         */
        for (SupplierOrderDetail supplierOrderDetail : list){
            float sumPrice = supplierOrderDetail.getPrice() * supplierOrderDetail.getNum();
            supplierOrderDetail.setSumPrice(sumPrice);
        }
        return list;
    }

    @Override
    public boolean delBySupplierOrderId(Integer supplierOrderId) {
        try {
            supplierOrderDetailDao.deleteBySupplierOrderId(supplierOrderId);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

}
