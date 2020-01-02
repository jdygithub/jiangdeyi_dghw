package com.zking.zkingedu.common.controller;


import com.zking.zkingedu.common.model.Employee;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.service.EmployeeService;
import com.zking.zkingedu.common.service.MenuService;
import com.zking.zkingedu.common.service.SystemServiceLog;
import com.zking.zkingedu.common.utils.RedisUtil;
import com.zking.zkingedu.common.utils.SuperController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class PageController extends SuperController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MenuService menuService;
    /**
     * 无需权限请求
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        System.out.println("来了login");
        return "lyb/login";
    }

//    @RequestMapping(value = "index")
//    public String index(){
//        System.out.println("来了index");
//        return "lyb/index";
//    }

    @RequestMapping(value = "main")
    public String main(){
        System.out.println("来了main");
        return "lyb/main";
    }

    @SystemServiceLog
    @RequestMapping(value = "homepage")
    public String homepage(){
        System.out.println("homepage");
        return "lyb/homepage";
    }
    @SystemServiceLog
    @RequestMapping(value = "log")
    public String log(){
        System.out.println("log");
        return "lyb/log";
    }

    @RequestMapping(value = "login1")
    public String login1(){
        System.out.println("login1");
        return "lyb/login1";
    }
    @SystemServiceLog
    @RequestMapping(value = "user")
    public String user(){
        System.out.println("user");
        return "lyb/user";
    }
    @SystemServiceLog
    @RequestMapping(value = "adduser")
    public String adduser(){
        System.out.println("adduser");
        return "lyb/adduser";
    }
    @SystemServiceLog
    @RequestMapping(value = "edituser")
    public ModelAndView edituser(ModelAndView modelAndView,HttpServletRequest request){
        Map map1 = getMap(request);
        String empid = map1.get("empid").toString();
        Map<String, Object> emp = employeeService.findObjectById(Integer.parseInt(empid));
        modelAndView.addObject("emp",emp.get("user"));
        modelAndView.setViewName("lyb/edituser");
        return  modelAndView;
    }

    @RequestMapping(value = "err")
    public  String err(){
        System.out.println("来了err");
        return "err";
    }

    @RequestMapping(value = "loginCheck")
    public String login(Employee user){
        System.out.println("验证");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getEmpname(),user.getEmppassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("不存在此账户");
            return "login";
        }catch (AuthenticationException e){
            System.out.println("密码错误");
            return "login";
        }
        System.out.println("密码正确");
        return "redirect:index";
    }
    @SystemServiceLog
    @RequestMapping(value = "/role")
    public  String role(){
        System.out.println("来了role");
        return  "lyb/role";
    }

    @RequestMapping(value = "/addrole")
    public  String addrole(){
        System.out.println("来了addrole");
        return  "lyb/addrole";
    }

    @RequestMapping(value = "/editrole")
    public  String editrole(){
        System.out.println("来了editrole");
        return  "lyb/editrole";
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        //获得菜单集合
        int empid= (int) request.getSession().getAttribute("empid");
        List<Menu> menus = menuService.getmenus(empid);
        modelAndView.addObject("menus",menus);
        modelAndView.setViewName("lyb/admin-index");
        return  modelAndView;
    }
}
