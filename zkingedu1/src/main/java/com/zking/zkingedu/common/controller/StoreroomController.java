package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.service.StoreroomService;
import com.zking.zkingedu.common.utils.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/storeroom")
public class StoreroomController extends SuperController {
    @Autowired
    private StoreroomService storeroomService;
    ;

    /**
     * 添加库房信息
     */
    @RequestMapping("/addstoreroom")
    @ResponseBody
    @Transactional//事物
    public String addstoreroom(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            storeroomService.addstoreroom(map1);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

    /**
     * 修改库房详细信息
     * @param request
     * @return
     */
    @RequestMapping("/updatestoreroom")
    @ResponseBody
    @Transactional//事物
    public String updatestoreroom(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            storeroomService.updatestoreroom(map1);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }


    /**
     *  库房信息展示
      * @param request
     * @return
     */
    @RequestMapping("/getstorerooms")
    @ResponseBody
    public Map getstorerooms(HttpServletRequest request){
        Map map1 = getMap(request);
        Map<String,Object> map = new HashMap<>();
        List<Map> getstorerooms = storeroomService.getstorerooms(map1);
        map.put("code","0");
        map.put("msg","");
        map.put("data",getstorerooms);
        return map;
    }

    /**
     * 删除库房信息
     * @param request
     * @return
     */
    @RequestMapping("/delstoreroom")
    @ResponseBody
    public String delstoreroom(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            storeroomService.delstoreroom(map1);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

}
