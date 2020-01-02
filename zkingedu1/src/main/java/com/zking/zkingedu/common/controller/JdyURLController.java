package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Collectiontype;
import com.zking.zkingedu.common.service.CollectionsService;
import com.zking.zkingedu.common.service.CollectiontypeService;
import com.zking.zkingedu.common.service.QuweiService;
import com.zking.zkingedu.common.service.StoreroomService;
import com.zking.zkingedu.common.utils.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("jdy")
public class JdyURLController extends SuperController {
    @Autowired
    private CollectiontypeService collectiontypeService;
    @Autowired
    private CollectionsService collectionsService;
    @Autowired
    private StoreroomService storeroomService;
    @Autowired
    private QuweiService quweiService;




    /**
     * 跳转到添加藏品界面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/addcollections")
    public ModelAndView addcollections(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        if(session.getAttribute("storerooms")==null){
            List<Map> getstorerooms = storeroomService.getstoreroomandquwei();
            session.setAttribute("storerooms",getstorerooms);
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
        if(session.getAttribute("storerooms")==null){
            List<Map> getstorerooms = storeroomService.getstoreroomandquwei();
            session.setAttribute("storerooms",getstorerooms);
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
        if(session.getAttribute("storerooms")==null){
            List<Map> getstorerooms = storeroomService.getstoreroomandquwei();
            session.setAttribute("storerooms",getstorerooms);
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
    public ModelAndView addrepair(ModelAndView modelAndView,String collectionid,String collectionname){
        modelAndView.setViewName("jdy/addrepair");
        modelAndView.addObject("collectionid",collectionid);
        modelAndView.addObject("collectionname",collectionname);
        return modelAndView;
    }
    /**
     * 跳转到修复完成
     * @param modelAndView
     * @return
     */
    @RequestMapping("/repairok")
    public ModelAndView repairok(ModelAndView modelAndView,String collectionid,String collectionname,String repairableid){
        modelAndView.setViewName("jdy/repairok");
        modelAndView.addObject("collectionid",collectionid);
        modelAndView.addObject("collectionname",collectionname);
        modelAndView.addObject("repairableid",repairableid);
        return modelAndView;
    }

    /**
     * 跳转到出库列表
     */
    @RequestMapping("/cwarehouse")
    public ModelAndView cwarehouse(ModelAndView modelAndView){
        modelAndView.setViewName("jdy/cwarehouse");
        return modelAndView;
    }

    /**
     * 跳转到入库列表
     */
    @RequestMapping("/rwarehouse")
    public ModelAndView rwarehouse(ModelAndView modelAndView){
        modelAndView.setViewName("jdy/rwarehouse");
        return modelAndView;
    }

    /**
     * 跳转到出库选择藏品列表
     * @param modelAndView
     * @return
     */
    @RequestMapping("/ckcollections")
    public ModelAndView ckcollections(ModelAndView modelAndView,HttpSession session){
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        modelAndView.setViewName("jdy/ckcollections");
        return modelAndView;
    }
    /**
     * 跳转到出库选择藏品列表
     * @param modelAndView
     * @return
     */
    @RequestMapping("/rkcollections")
    public ModelAndView rkcollections(ModelAndView modelAndView,HttpSession session){
        if(session.getAttribute("typelist")==null){//如果session中没有 就放一个进去
            List<Collectiontype> getalltypes = collectiontypeService.getalltypes();
            session.setAttribute("typelist",getalltypes);
        }
        modelAndView.setViewName("jdy/rkcollections");
        return modelAndView;
    }
    /**
     * 跳转到出入库藏品表
     */
    @RequestMapping("/comegocollection")
    public ModelAndView comegocollection(ModelAndView modelAndView,String comegoid){
        modelAndView.addObject("comegoid",comegoid);
        modelAndView.setViewName("jdy/comegocollection");
        return modelAndView;
    }

    /**
     * 跳转到区域展示
     * @param modelAndView
     * @return
     */
    @RequestMapping("/quweishow")
    public ModelAndView warehouseroom(ModelAndView modelAndView,HttpServletRequest request){
        Map map1 = getMap(request);
        List<Map> getquweis = quweiService.getquwei(map1);
        modelAndView.addObject("quweishows",getquweis);
        modelAndView.setViewName("jdy/quweishow");
        return modelAndView;
    }

    /**
     * 跳转到库房信息
     * @param modelAndView
     * @return
     */
    @RequestMapping("/storeroom")
    public ModelAndView storeroom(ModelAndView modelAndView){
        modelAndView.setViewName("jdy/storeroom");
        return modelAndView;
    }

    /**
     * 跳转到添加库房
     * @param modelAndView
     * @return
     */
    @RequestMapping("/addstoreroom")
    public ModelAndView addstoreroom(ModelAndView modelAndView,HttpSession session){
        if(session.getAttribute("emplist")==null){//如果session中没有 就放一个进去
            List<Map> getemps = collectionsService.getemps();
            session.setAttribute("emplist",getemps);
        }
        modelAndView.setViewName("jdy/addstoreroom");
        return modelAndView;
    }
    /**
     * 跳转到修改库房
     * @param modelAndView
     * @return
     */
    @RequestMapping("/updatestoreroom")
    public ModelAndView updatestoreroom(ModelAndView modelAndView, HttpSession session, HttpServletRequest request){
        Map map1 = getMap(request);
        if(session.getAttribute("emplist")==null){//如果session中没有 就放一个进去
            List<Map> getemps = collectionsService.getemps();
            session.setAttribute("emplist",getemps);
        }
        Map getstoreroom = storeroomService.getstoreroom(map1);
        modelAndView.addObject("getstoreroom",getstoreroom);
        modelAndView.setViewName("jdy/updatestoreroom");
        return modelAndView;
    }

    /**
     * 跳转到管理区位
     * @param modelAndView
     * @return
     */
    @RequestMapping("/quwei")
    public ModelAndView quwei(ModelAndView modelAndView){
        List<Map> getstorerooms = storeroomService.getstorerooms(null);
        modelAndView.addObject("storerooms",getstorerooms);//库房目录
        modelAndView.setViewName("jdy/quwei");
        return modelAndView;
    }

    /**
     * 跳转到修改区位
     * @param modelAndView
     * @return
     */
    @RequestMapping("/updatequwei")
    public ModelAndView updatequwei(ModelAndView modelAndView, HttpServletRequest request){
        Map map1 = getMap(request);
        List<Map> getstorerooms = storeroomService.getstorerooms(null);
        modelAndView.addObject("storerooms",getstorerooms);//库房目录
        Map getquweibyid = quweiService.getquweibyid(map1);
        modelAndView.addObject("getquwei",getquweibyid);
        modelAndView.setViewName("jdy/updatequwei");
        return modelAndView;
    }

    /**
     * 跳转到修改区位
     * @param modelAndView
     * @return
     */
    @RequestMapping("/addquwei")
    public ModelAndView addquwei(ModelAndView modelAndView){
        List<Map> getstorerooms = storeroomService.getstorerooms(null);
        modelAndView.addObject("storerooms",getstorerooms);//库房目录
        modelAndView.setViewName("jdy/addquwei");
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

    /**
     * 测试time
     * @param
     * @return
     */
    @RequestMapping("/time")
    public String time(){
        return "jdy/time";
    }

    @RequestMapping("/textadd")
    @ResponseBody
    public List add(){
        List<Map> getstorerooms = storeroomService.getstoreroomandquwei();
        return getstorerooms;
    }
}
