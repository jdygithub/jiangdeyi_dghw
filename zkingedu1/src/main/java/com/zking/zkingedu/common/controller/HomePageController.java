package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.zking.zkingedu.common.model.Echarts;
import com.zking.zkingedu.common.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("home")
public class HomePageController {
    @Autowired
    private HomePageService homePageService;

    @RequestMapping("testdata")
    @ResponseBody
    public List<Map<String,Object>> SelectType(){
        List<Map<String, Object>> list = homePageService.SelectType();
        System.out.println("取出来的数据为"+list);
        return list;
    }

    @ResponseBody
    @RequestMapping("testlist")
    public Map<String, Object> selectList(){
        List<Echarts> list = homePageService.SelectList();
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        return map;
    }
}
