package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.service.CollectionsService;
import com.zking.zkingedu.common.service.RepairService;
import com.zking.zkingedu.common.utils.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/repair")
public class RepairController extends SuperController {
    @Autowired
    private RepairService repairService;
    @Autowired
    private CollectionsService collectionsService;

    /**
     * 添加藏品
     */
    @RequestMapping("/addrepair")
    @ResponseBody
    @Transactional//事物
    public String addcollection(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            map1.put("date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            map1.put("repairstate","0");
            map1.put("collectionpreservation","修复中");
            repairService.addrepair(map1);
            collectionsService.updatecollection(map1);
            return "修复记录添加成功!";
        }catch (Exception e){
            e.printStackTrace();
            return "修复记录添加失败!";
        }
    }


    /**
     * 点击修复完成
     */
    @RequestMapping("/updaterepair")
    @ResponseBody
    @Transactional//事物
    public String repairok(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            map1.put("oktime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            map1.put("repairstate","1");
            repairService.updaterepair(map1);
            collectionsService.updatecollection(map1);
            return "修复成功!";
        }catch (Exception e){
            e.printStackTrace();
            return "修复失败!";
        }
    }

    /**
     * 修复申请展示
     *
     *
     * @return @Param("page") String page,@Param("limit") String limit,String str
     */
    @RequestMapping("/getrepairs")
    @ResponseBody
    public Map getrepairs(HttpServletRequest request){
        Map map1 = getMap(request);
        String page = (String)map1.get("page");
        String limit = (String)map1.get("limit");
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Map<String,Object> map = new HashMap<>();
        List<Map> getrepairs = repairService.getrepairs(map1);
        map.put("code","0");
        map.put("msg","");
        map.put("count",objects.getTotal());
        map.put("data",getrepairs);
        return map;
    }

    /**
     * 删除申请记录
     * @param request
     * @return
     */
    @RequestMapping("/delrepair")
    @ResponseBody
    public String delrepair(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            String collectionid = (String)map1.get("collectionid");
            System.err.println(collectionid);
            if(collectionid!="" && collectionid!=null){
//            System.err.println("我是未修复的");
                repairService.delrepair(map1);
                map1.put("collectionpreservation","可修复");
                collectionsService.updatecollection(map1);
            }
            else{
//            System.err.println("我是修复过后的");
                repairService.delrepair(map1);
            }
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }

    }

}
