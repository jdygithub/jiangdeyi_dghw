package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Collectiontype;
import com.zking.zkingedu.common.service.CollectionsService;
import com.zking.zkingedu.common.service.CollectiontypeService;
import com.zking.zkingedu.common.utils.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class JdyURLController extends SuperController {
    @Autowired
    private CollectiontypeService collectiontypeService;
    @Autowired
    private CollectionsService collectionsService;

    /**
     * 跳转到添加藏品界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/addcollections")
    public ModelAndView addcollections(ModelAndView modelAndView, HttpSession session){
//        List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
//        modelAndView.addObject("typelist",getalltypes);
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        modelAndView.setViewName("jdy/addcollection");
        return modelAndView;
    }

    /**
     * 跳转到藏品界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/collections")
    public ModelAndView collections(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        modelAndView.setViewName("jdy/collections");
        return modelAndView;
    }

    /**
     * 跳转到需修复藏品界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/repairindex")
    public ModelAndView repairindex(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        modelAndView.setViewName("jdy/repairindex");
        return modelAndView;
    }

    /**
     * 跳转到需修复藏品界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/repair")
    public ModelAndView repair(ModelAndView modelAndView){
        modelAndView.setViewName("jdy/repair");
        return modelAndView;
    }
    /**
     * 跳转到审核藏品界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/auditcollections")
    public ModelAndView auditcollections(ModelAndView modelAndView){
        modelAndView.setViewName("jdy/auditcollections");
        return modelAndView;
    }

    /**
     * 跳转到藏品类别管理页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/collectiontype")
    public ModelAndView collectiontype(ModelAndView modelAndView){
        List<Collectiontype> gettypeo = collectiontypeService.gettypeo();
        modelAndView.addObject("gettypeo",gettypeo);
        modelAndView.setViewName("jdy/typetree");
        return modelAndView;
    }

    /**
     * 跳转到单个藏品展示界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/getcollection")
    public ModelAndView getcollection(ModelAndView modelAndView,String collectionid, HttpSession session){
//        List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
//        modelAndView.addObject("typelist",getalltypes);
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        modelAndView.addObject("collectionid",collectionid);
        modelAndView.setViewName("jdy/getcollection");
        return modelAndView;
    }
    /**
     * 跳转到修改藏品展示界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/updatecollection")
    public ModelAndView updatecollection(ModelAndView modelAndView,String collectionid, HttpSession session){
//        List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
//        modelAndView.addObject("typelist",getalltypes);
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        modelAndView.addObject("collectionid",collectionid);
        modelAndView.setViewName("jdy/updatecollection");
        return modelAndView;
    }

    /**
     * 跳转到添加修复申请界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/addrepair")
    public ModelAndView addrepair(ModelAndView modelAndView,String collectionid){
        modelAndView.setViewName("jdy/addrepair");
        modelAndView.addObject("collectionid",collectionid);
        return modelAndView;
    }

    /**
     * 测试index
     * @param modelAndView
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
