package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.*;
import com.lds.supermarket.entity.OutOrder;
import com.lds.supermarket.entity.OutOrderDetail;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.entity.SupplierOrderDetail;
import com.lds.supermarket.service.OutOrderDetailService;
import com.lds.supermarket.service.OutOrderService;
import com.lds.supermarket.service.SupplierOrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OutOrderDetailServiceImpl implements OutOrderDetailService {

    @Resource
    private OutOrderDetailDao outOrderDetailDao;

    @Resource
    private OutOrderDao outOrderDao;

    @Resource
    private CommodityDao commodityDao;

    @Transactional()
    @Override
    public boolean save(List<OutOrderDetail> details, OutOrder outOrder){

        /**
         * 新建时间并插入数据
         */
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
           outOrder.setTime(sdf.format(d));
            //插入数据
            if(outOrder.getId() == null){
                outOrderDao.insert(outOrder);
                for(OutOrderDetail outOrderDetail : details){
                    outOrderDetail.setOutOrderId(outOrder.getId());
                    System.out.println("订单id========"+outOrder.getId());
                    //插入具体入库商品
                    outOrderDetailDao.insert(outOrderDetail);
                }
            }else{
                //删除数据
                outOrderDao.update(outOrder);
                //删除以前的订单列表重新加载
                outOrderDetailDao.deleteByOutOrderId(outOrder.getId());
                for(OutOrderDetail outOrderDetail : details){
                    outOrderDetail.setOutOrderId(outOrder.getId());
                    outOrderDetailDao.insert(outOrderDetail);
                }
            }
            return  true;
    }
    @Override
    public List<OutOrderDetail> getOutOrderDetailByOutOrderId(Integer outOrderId){
        List<OutOrderDetail> list = outOrderDetailDao.getOutOrderDetailByOutOrderId(outOrderId);

        /**
         * 循环计算每个总价
         */
        for (OutOrderDetail outOrderDetail : list){
            float sumPrice = outOrderDetail.getPrice() * outOrderDetail.getNum();
            outOrderDetail.setSumPrice(sumPrice);
        }
        return list;
    }
    @Override
    public boolean delByOutOrderId(Integer outOrderId) {
        try {
            outOrderDetailDao.deleteByOutOrderId(outOrderId);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

}
