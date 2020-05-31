package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.CommodityDao;
import com.lds.supermarket.dao.CommodityTypeDao;
import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.CommodityType;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.service.CommodityService;
import com.lds.supermarket.service.CommodityTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommodityTypeServiceImpl implements CommodityTypeService {

    @Resource
    private CommodityTypeDao commodityTypeDao;

    @Override
    public Page<CommodityType> getAllCommodityType() {

        Page<CommodityType> page = new Page<CommodityType>();
        page.setCountSum(commodityTypeDao.getCommodityTypeCount());//设置总记录条数
        page.setNowPage(1);//设置当前页码
        page.setCountNum(page.getCountSum());//设置显示记录条数
        page.setPageSum();//设置总页码

        page.setList(commodityTypeDao.getAllCommodityType());
        return page;
    }

    @Override
    public Page<CommodityType> getAllCommodityType(Integer nowPage, Integer size) {

        Page<CommodityType> page = new Page<CommodityType>();
        page.setCountSum(commodityTypeDao.getCommodityTypeCount());//设置总记录条数
        page.setNowPage(nowPage);//设置当前页码
        page.setCountNum(size);//设置显示记录条数
        page.setPageSum();//设置总页码
        Integer countStart = (page.getNowPage()-1) * size;//设置开始查询记录数
        page.setList(commodityTypeDao.getAllCommodityTypeByPage(countStart,size));
        return page;
    }

    @Override
    public boolean save(CommodityType commodityType) {
        try {
            if(commodityType.getId() != null){
                commodityTypeDao.update(commodityType);
            }else{
                commodityTypeDao.insert(commodityType);
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
                 commodityTypeDao.delete(id);
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
    public CommodityType getCommodityTypeById(Integer id) {
        return commodityTypeDao.getCommodityTypeById(id);
    }
}
