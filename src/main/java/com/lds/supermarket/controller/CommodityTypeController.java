package com.lds.supermarket.controller;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.CommodityType;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.service.CommodityService;
import com.lds.supermarket.service.CommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/commodityType")
@CrossOrigin//解决跨域请求无响应头问题
public class CommodityTypeController {

    @Autowired
    private CommodityTypeService commodityTypeService;
    /**
     * 获取供货商信息
     * nowPage:查询页码
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    @RequestMapping("/getAllCommodityType")
    public Map<String,Object> getAllCommodityType(Integer nowPage,Integer size){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<CommodityType> page = null;
        map.put("code",1);
        map.put("request","SUCCESS");
        if(nowPage == null || size == null){
            page = commodityTypeService.getAllCommodityType();
        }else {
            page = commodityTypeService.getAllCommodityType(nowPage,size);
        }
        map.put("result",page);
        return map;
    }

    /**
     * 根据id获取单个供应商信息
     * @param id
     * @return
     */
    @RequestMapping("/getCommodityTypeById")
    public Map<String,Object> getCommodityTypeById(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        CommodityType commodityType = commodityTypeService.getCommodityTypeById(id);
        map.put("result",commodityType);
        return  map;
    }

    @RequestMapping("/save")
    public Map<String,Object> update(CommodityType commodityType){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = commodityTypeService.save(commodityType);
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
        boolean flag = commodityTypeService.del(id);
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
