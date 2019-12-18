package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.service.QuweiService;
import com.zking.zkingedu.common.service.QuweicollectionsService;
import com.zking.zkingedu.common.utils.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quwei")
public class QuweiController extends SuperController {
    @Autowired
    private QuweiService quweiService;
    @Autowired
    private QuweicollectionsService quweicollectionsService;

    /**
     * 查询区域信息A 区域展示
     * @param request
     * @return
     */
    @RequestMapping("/getquweis")
    @ResponseBody
    public Map getquweis(HttpServletRequest request){
        try {
            Map map = getMap(request);
            List<Map> getquwei = quweiService.getquwei(map);
            Map map1 = new HashMap();
            map1.put("quweis",getquwei);
            return map1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查询区域信息B 区域管理
     * @param request
     * @return
     */
    @RequestMapping("/getquwei")
    @ResponseBody
    public Map getquwei(HttpServletRequest request){
        try {
            Map map = getMap(request);
            List<Map> getquwei = quweiService.getquwei(map);
            Map map1 = new HashMap();
            map1.put("code","0");
            map1.put("msg","");
            map1.put("data",getquwei);
            return map1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 添加区位
     * @param request
     * @return
     */
    @RequestMapping("/addquwei")
    @ResponseBody
    public String addquwei(HttpServletRequest request){
        try {
            Map map = getMap(request);
            quweiService.addquwei(map);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

    /**
     * 修改区位信息
     * @param request
     * @return
     */
    @RequestMapping("/updatequwei")
    @ResponseBody
    public String updatequwei(HttpServletRequest request){
        try {
            Map map = getMap(request);
            quweiService.updatequwei(map);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

    /**
     * 删除区位
     * @param request
     * @return
     */
    @RequestMapping("/delquwei")
    @ResponseBody
    public String delquwei(HttpServletRequest request){
        try {
            Map map = getMap(request);
            quweiService.delquwei(map);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

    /**
     * 刷新数量
     * @param request
     * @return
     */
    @RequestMapping("/nunbersx")
    @ResponseBody
    public String sx(HttpServletRequest request){
        try {
            Map map = getMap(request);
            int nunbersx = quweiService.nunbersx(map);
            map.put("number", nunbersx);
            int updatequwei=0;
            if(nunbersx>0){
                updatequwei = quweiService.updatequwei(map);
            }
            if(updatequwei>0){
                return "1";
            }else{
                return "0";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }






}
