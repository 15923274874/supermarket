package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.CommodityDao;
import com.lds.supermarket.dao.SupplierDao;
import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.CommodityService;
import com.lds.supermarket.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityDao commodityDao;

    @Override
    public Page<Commodity> getAllCommodity(User user) {

        Page<Commodity> page = new Page<Commodity>();
        if(user.getJurisdiction() < 3){
            page.setCountSum(commodityDao.getCommodityCount());//设置总记录条数
        }else {
            page.setCountSum(commodityDao.getCommodityCountBySupplierId(user.getSupplierId()));//设置总记录条数
        }
        page.setNowPage(1);//设置当前页码
        page.setCountNum(page.getCountSum());//设置显示记录条数
        page.setPageSum();//设置总页码
        if(user.getJurisdiction() < 3){
            page.setList(commodityDao.getAllCommodity());
        }else {
            page.setList(commodityDao.getAllCommodityBySupplierId(user.getSupplierId()));
        }

        return page;
    }

    @Override
    public Page<Commodity> getAllCommodity(Integer nowPage, Integer size,User user) {

        Page<Commodity> page = new Page<Commodity>();
        if(user.getJurisdiction() < 3){
            page.setCountSum(commodityDao.getCommodityCount());//设置总记录条数
        }else {
            page.setCountSum(commodityDao.getCommodityCountBySupplierId(user.getSupplierId()));//设置总记录条数
        }


        page.setNowPage(nowPage);//设置当前页码
        page.setCountNum(size);//设置显示记录条数
        page.setPageSum();//设置总页码
        Integer countStart = (page.getNowPage()-1) * size;//设置开始查询记录数

        if(user.getJurisdiction() < 3){
            page.setList(commodityDao.getAllCommodityByPage(countStart,size));
        }else {
            page.setList(commodityDao.getAllCommodityByPageAndSupplierId(countStart,size,user.getSupplierId()));
        }

        return page;
    }

    @Override
    public boolean save(Commodity commodity) {
        try {
            if(commodity.getId() != null){
                commodityDao.update(commodity);
            }else{
                commodityDao.insert(commodity);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean del(Integer id) {
        try {
             if(id != null){
                 commodityDao.delete(id);
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
    public Commodity getCommodityById(Integer id) {
        return commodityDao.getCommodityById(id);
    }
}
