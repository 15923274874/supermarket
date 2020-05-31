package com.lds.supermarket.controller;

import com.lds.supermarket.entity.OutOrder;
import com.lds.supermarket.entity.OutOrderDetail;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.entity.SupplierOrderDetail;
import com.lds.supermarket.service.OutOrderDetailService;
import com.lds.supermarket.service.SupplierOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/outOrderDetail")
@CrossOrigin//解决跨域请求无响应头问题
public class OutOrderDetailController {

    @Autowired
    private OutOrderDetailService outOrderDetailService;

    /**
     * 根据id获取单个供应商信息
     * @param outOrderId
     * @return
     */
    @RequestMapping("/getOutOrderDetailByOutOrderId")
    public Map<String,Object> getSupplierOrderDetailBySupplierOrderId(Integer outOrderId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        List<OutOrderDetail> list = outOrderDetailService.getOutOrderDetailByOutOrderId(outOrderId);
        map.put("result",list);
        return  map;
    }

    /**
     * 保存订单详情列表
     * @param
     * @return
     */
    @RequestMapping("/saveOutOrderDetail")
    //
    public Map<String,Object> save(@RequestBody OutParam outParam){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",-1);
        map.put("request","ERROR");
        if(outOrderDetailService.save(outParam.details,outParam.outOrder)){
            map.put("code",1);
            map.put("request","SUCCESS");
        }
        return  map;
    }

}

class  OutParam{
    public OutOrder outOrder;
    public List<OutOrderDetail> details;

    @Override
    public String toString() {
        return "OutParam{" +
                "outOrder=" + outOrder +
                ", details=" + details +
                '}';
    }
}
