package com.lds.supermarket.controller;

import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
@CrossOrigin//解决跨域请求无响应头问题
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    /**
     * 获取供货商信息
     * nowPage:查询页码
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    @RequestMapping("/getAllSupplier")
    public Map<String,Object> getAllSupplier(HttpSession session, Integer nowPage, Integer size){
        Map<String,Object> map = new HashMap<String,Object>();
        User user = (User) session.getAttribute("user");
        Page<Supplier> page = null;
        map.put("code",1);
        map.put("request","SUCCESS");
        if(nowPage == null || size == null){
            page = supplierService.getAllSupplier(user);
        }else {
            page = supplierService.getAllSupplier(nowPage,size,user);
        }
        map.put("result",page);
        return map;
    }

    /**
     * 根据id获取单个供应商信息
     * @param id
     * @return
     */
    @RequestMapping("/getSupplierById")
    public Map<String,Object> getSupplierById(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        Supplier supplier = supplierService.getSupplierById(id);
        map.put("result",supplier);
        return  map;
    }

    @RequestMapping("/save")
    public Map<String,Object> update(Supplier supplier){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = supplierService.save(supplier);
        if(flag){
            map.put("code",1);
            map.put("request","SUCCESS");
        }else {
            map.put("code",-1);
            map.put("request","ERROR");
        }
        return  map;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public Map<String,Object> delete(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = supplierService.del(id);
        if(flag){
            map.put("code",1);
            map.put("request","SUCCESS");
        }else {
            map.put("code",-1);
            map.put("request","ERROR");
        }
        return  map;
    }
}
