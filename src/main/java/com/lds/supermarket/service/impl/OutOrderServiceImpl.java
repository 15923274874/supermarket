package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.*;
import com.lds.supermarket.entity.*;
import com.lds.supermarket.service.OutOrderService;
import com.lds.supermarket.service.SupplierOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OutOrderServiceImpl implements OutOrderService {

    @Resource
    private OutOrderDao outOrderDao;

    @Resource
    private OutOrderDetailDao outOrderDetailDao;

    @Resource
    private CommodityDao commodityDao;

    @Override
    @Transactional
    public Map<String,String> save(OutOrder outOrder) {
            Map<String,String> map = new HashMap<>();
            if (outOrder.getId() != null){
                OutOrder order = outOrderDao.getOutOrderById(outOrder.getId());
                if(outOrder.getTime() == null){outOrder.setTime(order.getTime());}
                if(outOrder.getTitle() == null){outOrder.setTitle(order.getTitle());}
                if(outOrder.getUserId() == null){outOrder.setUserId(order.getUserId());}
                if(outOrder.getTypeNum() == null){outOrder.setTypeNum(order.getTypeNum());}
                if(outOrder.getCommodityNum() == null){outOrder.setCommodityNum(order.getCommodityNum());}
                if(outOrder.getPrice() == 0){outOrder.setPrice(order.getPrice());}
                if(outOrder.getState() == null){outOrder.setState(order.getState());}
                if(outOrder.getInfo() == null){outOrder.setInfo(order.getInfo());}
                List<OutOrderDetail> list = outOrderDetailDao.getOutOrderDetailByOutOrderId(outOrder.getId());
                for(OutOrderDetail outOrderDetail :list){
                    Commodity commodity = commodityDao.getCommodityById(outOrderDetail.getCommodityId());
                        //判断库存是否充足
                    if(commodity.getCommodityStock() < outOrderDetail.getNum()){
                        map.put("state","error");
                        map.put("info",commodity.getCommodityName()+"库存不足");
                        return map;
                    }
                  }

                outOrderDao.update(outOrder);
                if("已出库".equals(outOrder.getState())){
                     //根据商品列表循环出库
                    for(OutOrderDetail outOrderDetail :list){
                        commodityDao.updateCommodityStockOnOut(outOrderDetail.getCommodityId(),outOrderDetail.getNum());
                    }
                }
                map.put("state","success");
            }else{
                map.put("state","error");
                map.put("info","订单不存在");
            }

            return map;
    }

    @Override
    public Page<OutOrder> getAllOutOrder() {

        Page<OutOrder> page = new Page<OutOrder>();
        page.setCountSum(outOrderDao.getoutOrderCount());//设置总记录条数
        page.setNowPage(1);//设置当前页码
        page.setCountNum(page.getCountSum());//设置显示记录条数
        page.setPageSum();//设置总页码
        page.setList(outOrderDao.getAllOutOrder());
        return page;
    }

    @Override
    public Page<OutOrder> getAllOutOrder(Integer nowPage, Integer size) {

        Page<OutOrder> page = new Page<OutOrder>();
        page.setCountSum(outOrderDao.getoutOrderCount());//设置总记录条数
        page.setNowPage(nowPage);//设置当前页码
        page.setCountNum(size);//设置显示记录条数
        page.setPageSum();//设置总页码
        Integer countStart = (page.getNowPage()-1) * size;//设置开始查询记录数
        page.setList(outOrderDao.getAllOutOrderByPage(countStart,size));
        return page;
    }

    @Override
    @Transactional
    public boolean del(Integer id) {
        try {
             if(id != null){
                 outOrderDao.delete(id);
                 outOrderDetailDao.deleteByOutOrderId(id);
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
    public OutOrder getOutOrderById(Integer id) {
        return outOrderDao.getOutOrderById(id);
    }
}
