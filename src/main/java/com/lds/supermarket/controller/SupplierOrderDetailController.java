package com.lds.supermarket.controller;

import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.entity.SupplierOrderDetail;
import com.lds.supermarket.service.SupplierOrderDetailService;
import com.lds.supermarket.service.SupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/supplierOrderDetail")
@CrossOrigin//解决跨域请求无响应头问题
public class SupplierOrderDetailController {

    @Autowired
    private SupplierOrderDetailService supplierOrderDetailService;

    /**
     * 根据id获取单个供应商信息
     * @param supplierOrderId
     * @return
     */
    @RequestMapping("/getSupplierOrderDetailBySupplierOrderId")
    public Map<String,Object> getSupplierOrderDetailBySupplierOrderId(Integer supplierOrderId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        List<SupplierOrderDetail> list = supplierOrderDetailService.getSupplierOrderDetailBySupplierOrderId(supplierOrderId);
        map.put("result",list);
        return  map;
    }

    /**
     * 保存订单详情列表
     * @param
     * @return
     */
    @RequestMapping("/saveSupplierOrderDetail")
    //
    public Map<String,Object> save(@RequestBody Param param){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",-1);
        map.put("request","ERROR");
        if(supplierOrderDetailService.save(param.details,param.supplierOrder)){
            map.put("code",1);
            map.put("request","SUCCESS");
        }
        return  map;
    }

}
class  Param{
    public SupplierOrder supplierOrder;
    public List<SupplierOrderDetail> details;
}
