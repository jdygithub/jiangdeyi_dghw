package com.zking.zkingedu.common.controller;


import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/")
    public String test(HttpServletRequest request){
        int a = 0/9;
        System.out.println(a);
        System.out.println("来了");
        List<User> allUser = userService.getAllUser();
        System.out.println(allUser.size());
        request.setAttribute("users",allUser);
        request.setAttribute("welcome","yadaze");
        return "text";
    }


    @RequestMapping("/test")
    public String test() {

        redisUtil.set("username","柠泽");

        Object username = redisUtil.get("username");
        System.out.println(username);
        return "text";
    }

    /**
     * 无需权限请求
     * @return
     */
    @RequestMapping(value = "/login")
    public String testShiro1(){
        System.out.println("来了login");
        return "login";
    }

    @RequestMapping(value = "index")
    public String testShiro2(){
        System.out.println("来了index");
        return "index";
    }

    @RequestMapping(value = "loginCheck")
    public String login(User user){
        System.out.println("验证");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(),user.getUpwd());
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

    @RequestMapping(value = "/admin")
    public String testt(){
        System.out.println("来了index");
        return "admin";
    }

}
