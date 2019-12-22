package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.model.Employee;
import com.zking.zkingedu.common.model.Loginfo;
import com.zking.zkingedu.common.service.EmployeeService;
import com.zking.zkingedu.common.service.LogService;
import com.zking.zkingedu.common.service.SystemServiceLog;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leon
 * @createDate 2019年9月30日
 * @version v1.0
 * @classRemarks 日志切面类
 */
@Aspect
@Component
public class SystemLogAspect  {


 private static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

 private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

 private static final ThreadLocal<Loginfo> logThreadLocal = new NamedThreadLocal<Loginfo>("ThreadLocal log");

 private static final ThreadLocal<Employee> currentUser=new NamedThreadLocal<>("ThreadLocal user");

 @Autowired(required = false)
 HttpServletRequest request;

 @Autowired
 ThreadPoolTaskExecutor threadPoolTaskExecutor;

 @Autowired
 private LogService logService;

 @Autowired
 private  RedisUtil redisUtil;

 //定义切点@Pointcut
 //在注解的位置切入代码

 /**
  * service切点
  */
 @Pointcut("@annotation(com.zking.zkingedu.common.service.SystemServiceLog)")
 public void controllerAspect(){}


       /*@Pointcut("execution(* com.zking.zkingedu.common.controller.*.*(..))")
    public void controllerPointerCut(){}*/

        /**
      * 前置通知 用于拦截Controller层记录用户的操作的开始时间
      * @param joinPoint 切点
      * @throws InterruptedException
      */
        @Before("controllerAspect()")
  public void doBefore(JoinPoint joinPoint){
            try {
                logger.info("进入日志切面前置通知");
                //读取session中的用户
        /*HttpSession session = request.getSession();       
        Users user = (Users) session.getAttribute("ims_user"); */
                Employee employee= (Employee) SecurityUtils .getSubject().getSession().getAttribute("user");
                System.out.println("数据为"+employee.toString());
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
                String title="info";
                String remoteAddr=IpAddressUtil.getIpAddress(request);//请求的ip
                String requestUri=request.getRequestURI();//请求的uri
                // 请求的方法参数
                String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
                logger.info("设置日志信息存储到表中！");
                Loginfo log=new Loginfo();
                log.setEmpid(employee.getEmpid());
                //请求方式
                log.setMethod(method);

                Date date = new Date();//获得系统时间
                SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
                String nowTime = sdf.format(date);
                Date time = sdf.parse(nowTime);

                System.out.println("当前时间为"+time);
                log.setOperationdate(time);

                //从切面织入点处通过反射机制获取织入点处的方法
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                //获取切入点所在的方法
                Method meth = signature.getMethod();
                //获取请求的类名
                String classname=joinPoint.getTarget().getClass().getName();
                //获取请求的方法名
                String methodname=meth.getName();
                log.setOperationtext(classname+"."+methodname);

                //获取请求的参数
                Object[] args = joinPoint.getArgs();
                //将参数所在的数组转换成json
                String params = null;
                try {
                    params = JacksonUtil.obj2json(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.setParams(params);
                log.setRemoteAddr(remoteAddr);
                log.setRequestUri(requestUri);
                log.setTitle(title);
                System.out.println("log的所有信息"+log.toString());
                logService.savelog(log);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
 /**
       * @Description 获取注解中对方法的描述信息 用于Controller层注解
       */
public static String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();// 目标方法名
    Object[] arguments = joinPoint.getArgs();
    Class targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    String description = "";
    for (Method method : methods) {
    if (method.getName().equals(methodName)) {
    Class[] clazzs = method.getParameterTypes();
    if (clazzs.length == arguments.length) {
    description = method.getAnnotation(SystemServiceLog.class).description();
    break;
     }
  }
}
      return description;
}


}
