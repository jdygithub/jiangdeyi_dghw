package com.zking.zkingedu.common.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Collections;
import com.zking.zkingedu.common.model.Collectiontype;
import com.zking.zkingedu.common.service.CollectionsService;
import com.zking.zkingedu.common.service.CollectiontypeService;
import com.zking.zkingedu.common.utils.SuperController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/collections")
public class CollectionsController extends SuperController {
    @Autowired
    private CollectionsService collectionsService;
    @Autowired
    private CollectiontypeService collectiontypeService;

    /**
     * 添加藏品
     */
    @RequestMapping("/addcollection")
    @ResponseBody
    @Transactional//事物
    public String addcollection(@ModelAttribute Collections collections){
        collections.setCreatedtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//添加创建时间
        collectionsService.addcollection(collections);
        return collections.getCollectionid()+"";//返回主键给添加图片使用
    }
    /**
     * 藏品展示
     *
     *
     * @return @Param("page") String page,@Param("limit") String limit,String str
     */
    @RequestMapping("/getcollections")
    @ResponseBody
    public Map getcollections(HttpServletRequest request){
        Map map1 = getMap(request);
        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        String page = (String)map1.get("page");
        String limit = (String)map1.get("limit");
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Map<String,Object> map = new HashMap<>();
        List<Map> getcollections = collectionsService.getcollections(map1);
        map.put("code","0");
        map.put("msg","");
        map.put("count",objects.getTotal());
        map.put("data",getcollections);
        return map;
    }

    /**
     * 修改藏品状态
     * @param request
     * @return
     */
    @RequestMapping("/editstatus")
    @ResponseBody
    @Transactional
    public String editstatus(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            String state = (String)map1.get("state");
            if("true".equals(state)){
                map1.put("collectionstatus","0");
            }
            else if("false".equals(state)){
                map1.put("collectionstatus","1");
            }
            collectionsService.updatecollection(map1);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

    /**
     * 删除藏品
     * @param request
     * @return
     */
    @RequestMapping("/delcollection")
    @ResponseBody
    @Transactional
    public String delcollection(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            collectionsService.delcollection(map1);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

    /**
     * 查询单个藏品
     * @param request
     * @return
     */
    @RequestMapping("/getcollection")
    @ResponseBody
    public Map getcollection(HttpServletRequest request){
        Map map1 = getMap(request);
        Map getcollection = collectionsService.getcollection(map1);
        return getcollection;
    }

    /**
     * 修改藏品
     * @param request
     * @return
     */
    @RequestMapping("/updatecollection")
    @ResponseBody
    @Transactional
    public String updatecollection(HttpServletRequest request){
        try {
            Map map1 = getMap(request);
            collectionsService.updatecollection(map1);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }
    /**--------------------------------------------------------------------------藏品类别----------------------------------------------------------------------------------
     * 查寻所有类别
     * @return
     */
    @RequestMapping("/gettypes")
    @ResponseBody
   public List<Map> getypes(){
        List<Collectiontype> gettypes = collectiontypeService.gettypes();
        List<Map> results = new ArrayList<>();//结果集
        if(gettypes!=null&&gettypes.size()!=0){
            for (int i = 0; i < gettypes.size(); ++i) {
                Map<String,Object> typemap = new HashMap<>();//一个一个的对象
                typemap.put("id",((Collectiontype)gettypes.get(i)).getCollectiontypeid());
                typemap.put("pid",((Collectiontype)gettypes.get(i)).getCollectionparenttypeid());
                typemap.put("title",((Collectiontype)gettypes.get(i)).getCollectiontypename());
                results.add(typemap);
            }
        }
        return results;
   }

    /**
     * 修改类别名称
     * @param collectiontype
     * @return
     */
    @RequestMapping("/updatetypename")
    @ResponseBody
   public String updatetypename(@ModelAttribute Collectiontype collectiontype){
        System.err.println(collectiontype);
       int updatetype = collectiontypeService.updatetype(collectiontype);
       if(updatetype!=1){
           return "error";
       }
       return "success";
   }

    /**
     * 删除类别
     * @param collectiontypeid
     * @return
     */
    @RequestMapping("/deltype")
    @ResponseBody
   public String deltype(@Param("collectiontypeid")Integer collectiontypeid,@Param("pid") Integer pid){
       if(pid==0){
           int exitid = collectiontypeService.exitid(collectiontypeid);
           if(exitid>0){//还有子类
               return "exist";
           }
       }
       int deltype = collectiontypeService.deltype(collectiontypeid);
       if(deltype!=1){
           return "error";
       }
       return "success";
   }

    /**
     * 添加类别
     * @param collectiontype
     * @return
     */
    @RequestMapping("/addtype")
    @ResponseBody
   public String addtype(@ModelAttribute Collectiontype collectiontype){
       System.err.println(collectiontype);
       int addtype = collectiontypeService.addtype(collectiontype);
       if(addtype!=1){
           return "error";
       }
       return "success";
   }




}
