package com.lds.supermarket.controller;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.CommodityService;
import com.lds.supermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/commodity")
@CrossOrigin//解决跨域请求无响应头问题
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    /**
     * 获取供货商信息
     * nowPage:查询页码
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    @RequestMapping("/getAllCommodity")
    public Map<String,Object> getAllCommodity(HttpSession session, Integer nowPage, Integer size){
        Map<String,Object> map = new HashMap<String,Object>();
        User user = (User) session.getAttribute("user");
        Page<Commodity> page = null;
        map.put("code",1);
        map.put("request","SUCCESS");
        if(nowPage == null || size == null){
            page = commodityService.getAllCommodity(user);
        }else {
            page = commodityService.getAllCommodity(nowPage,size,user);
        }

        map.put("result",page);
        return map;
    }

    /**
     * 根据id获取单个供应商信息
     * @param id
     * @return
     */
    @RequestMapping("/getCommodityById")
    public Map<String,Object> getCommodityById(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        Commodity commodity = commodityService.getCommodityById(id);
        map.put("result",commodity);
        return  map;
    }

    @RequestMapping("/save")
    public Map<String,Object> update(Commodity commodity){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = commodityService.save(commodity);
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
        boolean flag = commodityService.del(id);
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
