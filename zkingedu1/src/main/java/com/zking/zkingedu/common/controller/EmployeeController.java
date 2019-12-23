package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.dao.Emp_RoleMapper;
import com.zking.zkingedu.common.dao.EmployeeMapper;
import com.zking.zkingedu.common.dao.MenuMapper;
import com.zking.zkingedu.common.dao.RoleMapper;
import com.zking.zkingedu.common.model.Employee;
import com.zking.zkingedu.common.utils.SuperController;
import com.zking.zkingedu.common.model.Loginfo;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.*;
import com.zking.zkingedu.common.vo.Role_Menu;
import com.zking.zkingedu.common.vo.Tree;
import com.zking.zkingedu.common.vo.TreeNode;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController extends SuperController{
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private  Emp_RoleMapper emp_roleMapper;
    @Autowired
    private  RoleMapper roleMapper;
    @Autowired
    private  MenuMapper menuMapper;
    @Autowired
    private LogService logService;
    /**
     * 查询单个用户和他拥有的角色id
     * @param id
     * @return
     */
 @RequestMapping("query")
 @ResponseBody
    public Map<String,Object> query(@RequestParam("id")Integer id){
     System.out.println("进入emp,id="+id);
     Map<String,Object> map = employeeService.findObjectById(id);
     return map;
 }

 @RequestMapping("getPermessions")
 @ResponseBody
 public List<Map<String,Object>>  getPermessions(@RequestParam("empname")String empname){
     List<Map<String,Object>> list=employeeMapper.findEmpPermissions(empname);
     System.out.println("用户所有信息为"+list);
      return  list;
 }


    @RequestMapping("dologin")
    public String dologin(String empname,String empassword){
    System.out.println("进入dologin");
    System.out.println("前台获取的值为"+empname+"  "+empassword);
    employeeService.login(empname,empassword);
    ModelAndView mv=new ModelAndView();
    System.out.println("开始跳转页面");
    return "redirect:/indexa";
}

/*    public List<Collectiontype> getalltypes() {
        List<Collectiontype> results = new ArrayList<>();
        List<Collectiontype> types = collectiontypeDao.getalltypes();
        if(types!=null&&types.size()!=0){
            for (int i = 0; i < types.size(); ++i) {
                //如果类别父ID==0
                if(((Collectiontype)types.get(i)).getCollectionparenttypeid()==0){
                    Collectiontype type1 = new Collectiontype();
                    type1.setCollectiontypeid(((Collectiontype)types.get(i)).getCollectiontypeid());
                    type1.setCollectiontypename(((Collectiontype)types.get(i)).getCollectiontypename());
                    type1.setCollectionparenttypeid(((Collectiontype)types.get(i)).getCollectionparenttypeid());
                    List<Collectiontype> typeslist = new ArrayList<>();
                    for (int j = 0; j < types.size(); ++j) {
                        //所有类别的父ID等于顶级类别的ID
                        if(((Collectiontype)types.get(i)).getCollectiontypeid()==((Collectiontype)types.get(j)).getCollectionparenttypeid()){
                            Collectiontype type2 = new Collectiontype();
                            type2.setCollectiontypeid(((Collectiontype)types.get(j)).getCollectiontypeid());
                            type2.setCollectiontypename(((Collectiontype)types.get(j)).getCollectiontypename());
                            type2.setCollectionparenttypeid(((Collectiontype)types.get(j)).getCollectionparenttypeid());
                            typeslist.add(type2);
                        }
                    }
                    type1.setTypes(typeslist);
                    results.add(type1);
                }
            }
        }
        return results;
    }*/

@RequestMapping("AllLog")
@ResponseBody
@Transactional
public JSONObject findAllLog(HttpServletRequest request){
    Map map1 = getMap(request);
    String page = (String)map1.get("page");
    String limit = (String)map1.get("limit");
    Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
    List<Loginfo> list= logService.AllLog();
    System.err.println(map1);
    JSONObject json=new JSONObject();
    json.put("code",0);
    json.put("count",objects.getTotal());
    json.put("msg", "");
    json.put("data",list);
    return  json;
}

@RequestMapping("findMessions")
@ResponseBody
public List<Menu> findMessions(int id){
    System.out.println("来了controller");
    List<Menu> results=new ArrayList<>();
    List<Menu> menus=employeeMapper.findPermissions(id);
    System.out.println("empid为"+id);
    if (menus!=null&&menus.size()>0){
        for (int i=0;i<menus.size();i++){
            //父类别id=0
            if (((Menu)menus.get(i)).getMenuparentid()==0){
                Menu menu1=new Menu();
                menu1.setMenuid(((Menu)menus.get(i)).getMenuid());
                menu1.setMenuimage(((Menu)menus.get(i)).getMenuimage());
                menu1.setMenucode(((Menu)menus.get(i)).getMenucode());
                menu1.setMenuname(((Menu)menus.get(i)).getMenuname());
                menu1.setMenuparentid(((Menu)menus.get(i)).getMenuparentid());
                menu1.setMenustate(((Menu)menus.get(i)).getMenustate());
                menu1.setMenutype(((Menu)menus.get(i)).getMenutype());
                menu1.setMenuurl(((Menu)menus.get(i)).getMenuurl());
                //子菜单   所有类别的父ID等于顶级类别的ID
                List<Menu> childlist=new ArrayList<>();
                for (int j=0;j<menus.size();j++){
                    if (((Menu)menus.get(i)).getMenuid()==((Menu)menus.get(j)).getMenuparentid()){
                        Menu menu2=new Menu();
                        menu2.setMenuid(((Menu)menus.get(j)).getMenuid());
                        menu2.setMenuimage(((Menu)menus.get(j)).getMenuimage());
                        menu2.setMenucode(((Menu)menus.get(j)).getMenucode());
                        menu2.setMenuname(((Menu)menus.get(j)).getMenuname());
                        menu2.setMenuparentid(((Menu)menus.get(j)).getMenuparentid());
                        menu2.setMenustate(((Menu)menus.get(j)).getMenustate());
                        menu2.setMenutype(((Menu)menus.get(j)).getMenutype());
                        menu2.setMenuurl(((Menu)menus.get(j)).getMenuurl());
                        childlist.add(menu2);
                    }
                }
                menu1.setChildlist(childlist);
                results.add(menu1);
            }
        }
    }
return  results;
}

@RequestMapping("AllRoleMessions")
@ResponseBody
 public List<TreeNode> AllRoleMessions(){
    System.out.println("来了controller");
    List<TreeNode> results=new ArrayList<>();
    List<Menu> menus=employeeMapper.AllMessions();
    for (int i=0;i<menus.size();i++){
        TreeNode node=new TreeNode();
        node.setId(menus.get(i).getMenuid());
        node.setPid(menus.get(i).getMenuparentid());
        node.setTitle(menus.get(i).getMenuname());
        results.add(node);
    }
    return  results;
}


    @RequestMapping("tree")
    @ResponseBody
    public List<Tree> tree1(){
        System.out.println("来了controller");
        List<Tree> results=new ArrayList<>();
        List<Menu> menus=employeeMapper.AllMessions();
        if (menus!=null&&menus.size()>0){
            for (int i=0;i<menus.size();i++){
                //父类别id=0
                if (((Menu)menus.get(i)).getMenuparentid()==0){
                    Tree tree=new Tree();
                    tree.setId(((Menu)menus.get(i)).getMenuid());
                    tree.setTitle(((Menu)menus.get(i)).getMenuname());
                    tree.setHref(((Menu)menus.get(i)).getMenuurl());
                    //子菜单   所有类别的父ID等于顶级类别的ID
                    List<Tree> childlist=new ArrayList<>();
                    for (int j=0;j<menus.size();j++){
                        if (((Menu)menus.get(i)).getMenuid()==((Menu)menus.get(j)).getMenuparentid()){
                            Tree tree1=new Tree();
                            tree1.setId(((Menu)menus.get(j)).getMenuid());
                            tree1.setTitle(((Menu)menus.get(j)).getMenuname());
                            tree1.setHref(((Menu)menus.get(j)).getMenuurl());
                            childlist.add(tree1);
                        }
                    }
                    tree.setChildren(childlist);
                    results.add(tree);
                }
            }
        }
        return  results;
    }

    @RequestMapping("AllMessions")
    @ResponseBody
    public List<Menu> AllMenuMessions(){
        System.out.println("来了controller");
        List<Menu> results=new ArrayList<>();
        List<Menu> menus=employeeMapper.AllMessions();
        if (menus!=null&&menus.size()>0){
            for (int i=0;i<menus.size();i++){
                //父类别id=0
                if (((Menu)menus.get(i)).getMenuparentid()==0){
                    Menu menu1=new Menu();
                    menu1.setMenuid(((Menu)menus.get(i)).getMenuid());
                    menu1.setMenuimage(((Menu)menus.get(i)).getMenuimage());
                    menu1.setMenucode(((Menu)menus.get(i)).getMenucode());
                    menu1.setMenuname(((Menu)menus.get(i)).getMenuname());
                    menu1.setMenuparentid(((Menu)menus.get(i)).getMenuparentid());
                    menu1.setMenustate(((Menu)menus.get(i)).getMenustate());
                    menu1.setMenutype(((Menu)menus.get(i)).getMenutype());
                    menu1.setMenuurl(((Menu)menus.get(i)).getMenuurl());
                    //子菜单   所有类别的父ID等于顶级类别的ID
                    List<Menu> childlist=new ArrayList<>();
                    for (int j=0;j<menus.size();j++){
                        if (((Menu)menus.get(i)).getMenuid()==((Menu)menus.get(j)).getMenuparentid()){
                            Menu menu2=new Menu();
                            menu2.setMenuid(((Menu)menus.get(j)).getMenuid());
                            menu2.setMenuimage(((Menu)menus.get(j)).getMenuimage());
                            menu2.setMenucode(((Menu)menus.get(j)).getMenucode());
                            menu2.setMenuname(((Menu)menus.get(j)).getMenuname());
                            menu2.setMenuparentid(((Menu)menus.get(j)).getMenuparentid());
                            menu2.setMenustate(((Menu)menus.get(j)).getMenustate());
                            menu2.setMenutype(((Menu)menus.get(j)).getMenutype());
                            menu2.setMenuurl(((Menu)menus.get(j)).getMenuurl());
                            childlist.add(menu2);
                        }
                    }
                    menu1.setChildlist(childlist);
                    results.add(menu1);
                }
            }
        }
        return  results;
    }


    @RequestMapping("findMenuByRole")
    @ResponseBody
    public  List<Menu> findMenuByRole(Integer id){
        //查出所有父菜单
        List<Menu> menulist= menuMapper.getMenuList(id);
        for (Menu menu:menulist){
            //循环查询子菜单
            List<Menu> childlist=menuMapper.getChildlist(menu.getMenuid());
            System.out.println("menuid为"+menu.getMenuid());
            menu.setChildlist(childlist);
        }
        System.out.println("进入方法role"+menulist);

     return  menulist;
}

@RequestMapping("findAllEmpUser")
    @ResponseBody
    public JSONObject findAllEmpUser(HttpServletRequest request){
    Map map1 = getMap(request);
    String page = (String)map1.get("page");
    String limit = (String)map1.get("limit");
    Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
    List<Employee>list=employeeMapper.findAllEmpUser();
    JSONObject json=new JSONObject();
    json.put("code",0);
    json.put("msg", "");
    json.put("count",objects.getTotal());
    json.put("data",list);
    return  json;
}

    @RequestMapping("insertEmp")
    @ResponseBody
    public String insertEmp(Employee entity,@Param(value = "roldids") String roleids){
        System.out.println("进入后台,数据为"+entity+"角色id为"+roleids);
        int row=employeeService.saveObject(entity,roleids);
        System.out.println("所有信息为"+row);
        return "Save OK";
    }

    @RequestMapping("AllRole")
    @ResponseBody
    public JSONObject AllRole(HttpServletRequest request){
        Map map1 = getMap(request);
        String page = (String)map1.get("page");
        String limit = (String)map1.get("limit");
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        System.out.println("进入后台");
        System.err.println(map1);
        List<Role> row=roleMapper.AllRole();
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg", "");
        json.put("count",objects.getTotal());
        json.put("data",row);
        return json;
    }

    @RequestMapping("SelectRole")
    @ResponseBody
    public List<Role> SelectRole(){
        System.out.println("进入后台");
        List<Role> row=roleMapper.AllRole();
        return row;
    }

    @RequestMapping("AllRoleByMenu")
    public JSONObject AllRoleByMenu(HttpServletRequest request){
        System.out.println("进入后台");
        Map map1 = getMap(request);
        String page = (String)map1.get("page");
        String limit = (String)map1.get("limit");
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<Role_Menu> row=roleMapper.AllRoleByMenu();
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg", "");
        json.put("count",objects.getTotal());
        json.put("data",row);
        return json;
    }

    @RequestMapping("updateRole")
    @ResponseBody
    public  String updateRole(Role entity,String menuid){
        //由于layui的checked 无法获取id,所以把结果处理一下
        List<Map<String,Object>> listObjectFir = (List<Map<String,Object>>) JSONArray.parse(menuid);
        //利用JSONArray中的parse方法来解析json数组字符串
        for(Map<String,Object> mapList : listObjectFir){
            for (Map.Entry entry : mapList.entrySet()){
                System.out.println( entry.getKey()  + "  " +entry.getValue());
            }
        }
        List<String> id=new ArrayList<>();
        for (Map<String,Object>map:listObjectFir){
            id.add(map.get("id").toString());
            JSONArray jsonArray= (JSONArray) map.get("children");
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                id.add(jsonObject.get("id").toString());
                System.out.println("数据为"+jsonObject.get("id"));
            }

        }
        String iid=id.toString();
        //因为list转成string的话 会有[]，replaceall去掉多余的东西,然后变成 num,num,num,方便后台添加
        String menuidd=iid.replaceAll("(?:\\[|null|\\]| +)", "");
        System.out.println("最后的数据为"+menuidd+"entity为"+entity);
        roleService.updateObject(entity,menuidd);
        return "update Ok";
    }

    @RequestMapping("addRole")
    @ResponseBody
    public  String addRole(Role entity,String menuid){
        //由于layui的checked 无法获取id,所以把结果处理一下
        List<Map<String,Object>> listObjectFir = (List<Map<String,Object>>) JSONArray.parse(menuid);
        //利用JSONArray中的parse方法来解析json数组字符串
        for(Map<String,Object> mapList : listObjectFir){
            for (Map.Entry entry : mapList.entrySet()){
                System.out.println( entry.getKey()  + "  " +entry.getValue());
            }
        }
          List<String> id=new ArrayList<>();
          for (Map<String,Object>map:listObjectFir){
                 id.add(map.get("id").toString());
              JSONArray jsonArray= (JSONArray) map.get("children");
              for (int i=0;i<jsonArray.size();i++){
                  JSONObject jsonObject= jsonArray.getJSONObject(i);
                  id.add(jsonObject.get("id").toString());
                  System.out.println("数据为"+jsonObject.get("id"));
              }

        }
        String iid=id.toString();
          //因为list转成string的话 会有[]，replaceall去掉多余的东西,然后变成 num,num,num,方便后台添加
        String menuidd=iid.replaceAll("(?:\\[|null|\\]| +)", "");
        System.out.println("最后的数据为"+menuidd+"entity为"+entity);
        roleService.saveObject(entity,menuidd);
        return "Save Ok";
    }

    @RequestMapping("deleteRole")
    @ResponseBody
    public String deleteRole(Integer id){
        System.out.println("进入delete方法，id为"+id);
        int row=roleService.deleteObject(id);
        return  "Delete Ok";
    }

    @RequestMapping("updateEmp")
    @ResponseBody
    public  String  updateEmp(Employee entity,@Param(value = "roldids") String roleids){
        System.out.println("entity为"+entity.getEmpid());
    employeeService.updateObject(entity,roleids);
    return "update OK";
}

@RequestMapping("deleteEmp")
@ResponseBody
public String deleteEmp(Integer id){
    System.out.println("进入delete方法，id为"+id);
    int row=employeeService.deleteObject(id);
    return  "Delete OK";
}
}
