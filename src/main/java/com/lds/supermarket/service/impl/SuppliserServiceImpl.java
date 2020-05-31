package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.SupplierDao;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SuppliserServiceImpl implements SupplierService {

    @Resource
    private SupplierDao supplierDao;

    @Override
    public Page<Supplier> getAllSupplier() {

        Page<Supplier> page = new Page<Supplier>();
        page.setCountSum(supplierDao.getSupplierCount());//设置总记录条数
        page.setNowPage(1);//设置当前页码
        page.setCountNum(page.getCountSum());//设置显示记录条数
        page.setPageSum();//设置总页码

        page.setList(supplierDao.getAllSupplier());
        return page;
    }

    @Override
    public Page<Supplier> getAllSupplier(Integer nowPage, Integer size) {

        Page<Supplier> page = new Page<Supplier>();
        page.setCountSum(supplierDao.getSupplierCount());//设置总记录条数
        page.setNowPage(nowPage);//设置当前页码
        page.setCountNum(size);//设置显示记录条数
        page.setPageSum();//设置总页码
        Integer countStart = (page.getNowPage()-1) * size;//设置开始查询记录数
        page.setList(supplierDao.getAllSupplierByPage(countStart,size));
        return page;
    }

    @Override
    public boolean save(Supplier supplier) {
        try {
            if(supplier.getId() != null){
                supplierDao.update(supplier);
            }else{
                supplierDao.insert(supplier);
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
                 supplierDao.delete(id);
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
    public Supplier getSupplierById(Integer id) {
        return supplierDao.getSupplierById(id);
    }
}
