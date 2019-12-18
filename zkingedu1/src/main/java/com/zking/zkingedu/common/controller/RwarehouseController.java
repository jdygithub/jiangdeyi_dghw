package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.service.CollectionsService;
import com.zking.zkingedu.common.service.ComegocollectionService;
import com.zking.zkingedu.common.service.RwarehouseService;
import com.zking.zkingedu.common.utils.SuperController;
import com.zking.zkingedu.common.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("rwarehouse")
public class RwarehouseController extends SuperController {
    @Autowired
    private RwarehouseService rwarehouseService;
    @Autowired
    private ComegocollectionService comegocollectionService;
    @Autowired
    private CollectionsService collectionsService;

    /**
     * 入库记录展示
     *
     *
     * @return @Param("page") String page,@Param("limit") String limit,String str
     */
    @RequestMapping("/getrwarehouse")
    @ResponseBody
    public Map getcwarehouse(HttpServletRequest request){
        Map map1 = getMap(request);
        String page = (String)map1.get("page");
        String limit = (String)map1.get("limit");
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Map<String,Object> map = new HashMap<>();
        List<Map> getrwarehouse = rwarehouseService.getrwarehouse(map1);
        map.put("code","0");
        map.put("msg","");
        map.put("count",objects.getTotal());
        map.put("data",getrwarehouse);
        return map;
    }
    /**
     * 入库藏品展示
     */
    @RequestMapping("/rcshow")
    @ResponseBody
    public Map<String, Object> ccshow(HttpServletRequest request){
        Map map1 = getMap(request);
        Map<String,Object> map = new HashMap<>();
        List<Map> getcomego = comegocollectionService.getcomego(map1);
        map.put("code","0");
        map.put("msg","");
        map.put("data",getcomego);
        return map;
    }

    /**
     * 删除出入库藏品信息
     * @param request
     * @return
     */
    @RequestMapping("delcomego")
    @ResponseBody
    public String delcomego(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            comegocollectionService.delcomego(map1);
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
        return "ok";
    }

    /**
     * 添加出库信息
     * @param request
     * @return
     */
    @RequestMapping("addrwarehouse")
    @ResponseBody
    @Transactional
    public String addrwarehouse(HttpServletRequest request){
        Map map1 = getMap(request);
        try {
            String str = map1.get("str").toString();
            String uuid = UUID.randomUUID().toString();
            Map map2 = new HashMap();
            map2.put("collectionid",str);
            map2.put("existing","1");
            String[] split = str.split(",");
            int size = split.length;//藏品数量
            map1.put("rwarehouseid",uuid);
            map1.put("rwarehouseidnumber",size);
            map1.put("rwarehousedate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//添加创建时间
            rwarehouseService.addrwarehouse(map1);//添加入库表返回主键到map1集合中
            collectionsService.updatecollection(map2);//出库后修改藏品表的existing(是否在库)列为0 未在库
            System.err.println(map1);
            System.err.println(map2);
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < split.length; i++){//(comegoid,collectionid,comego,okandno)
                sb.append("('"+uuid+"',"+split[i]+",1,1),");
            }
            Map m1 = new HashMap();
            m1.put("str",sb.substring(0,sb.length()-1));
            comegocollectionService.addcomego(m1);
            System.err.println(m1);
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
        return "ok";
    }
}
