package com.lds.supermarket.controller;

import com.lds.supermarket.entity.OutOrder;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.service.OutOrderService;
import com.lds.supermarket.service.SupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/outOrder")
@CrossOrigin//解决跨域请求无响应头问题
public class OutOrderController {

    @Autowired
    private OutOrderService outOrderService;
    /**
     * 获取供货商信息
     * nowPage:查询页码
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    @RequestMapping("/getAllOutOrder")
    public Map<String,Object> getAllSupplier(Integer nowPage,Integer size){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<OutOrder> page = null;
        map.put("code",1);
        map.put("request","SUCCESS");
        if(nowPage == null || size == null){
            page = outOrderService.getAllOutOrder();
        }else {
            page = outOrderService.getAllOutOrder(nowPage,size);
        }
        map.put("result",page);
        return map;
    }

    /**
     * 根据id获取单个供应商订单
     * @param id
     * @return
     */
    @RequestMapping("/getOutOrderById")
    public Map<String,Object> getSupplierOrderById(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        OutOrder outOrder = outOrderService.getOutOrderById(id);
        map.put("result",outOrder);
        return  map;
    }

    /**
     * 根据outOrder修改和添加对象
     * @param outOrder
     * @return
     */
    @RequestMapping("/save")
    public Map<String,Object> update(OutOrder outOrder){
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,String> saveState = outOrderService.save(outOrder);
        if("success".equals(saveState.get("state"))){
            map.put("code",1);
            map.put("request","SUCCESS");
        }else {
            map.put("code",-1);
            map.put("request","ERROR");
            map.put("info",saveState.get("info"));
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
        boolean flag = outOrderService.del(id);
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
