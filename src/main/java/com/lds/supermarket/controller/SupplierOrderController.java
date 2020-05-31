package com.lds.supermarket.controller;

import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.service.SupplierOrderService;
import com.lds.supermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/supplierOrder")
@CrossOrigin//解决跨域请求无响应头问题
public class SupplierOrderController {

    @Autowired
    private SupplierOrderService supplierOrderService;
    /**
     * 获取供货商信息
     * nowPage:查询页码
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    @RequestMapping("/getAllSupplierOrder")
    public Map<String,Object> getAllSupplier(Integer nowPage,Integer size){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<SupplierOrder> page = null;
        map.put("code",1);
        map.put("request","SUCCESS");
        if(nowPage == null || size == null){
            page = supplierOrderService.getAllSupplierOrder();
        }else {
            page = supplierOrderService.getAllSupplierOrder(nowPage,size);
        }
        map.put("result",page);
        return map;
    }

    /**
     * 根据id获取单个供应商订单
     * @param id
     * @return
     */
    @RequestMapping("/getSupplierOrderById")
    public Map<String,Object> getSupplierOrderById(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        SupplierOrder supplierOrder = supplierOrderService.getSupplierOrderById(id);
        map.put("result",supplierOrder);
        return  map;
    }

    /**
     * 根据supplierOrder修改和添加对象
     * @param supplierOrder
     * @return
     */
    @RequestMapping("/save")
    public Map<String,Object> update(SupplierOrder supplierOrder){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = supplierOrderService.save(supplierOrder);
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
        System.out.println(id);
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = supplierOrderService.del(id);
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
