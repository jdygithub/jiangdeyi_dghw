package com.zking.zkingedu.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户真实的id地址
 * @param - -request
 * @return
 */
public class IpAddressUtil {
     public  static String getIpAddress(HttpServletRequest request){
         String ip=null;

         //X-Forwarded-For:Squid 服务代理
         String ipAddress=request.getHeader("X-Forwarded-For");
         String unknown = "unknown";
         if (ipAddress==null||ipAddress.length()==0||unknown.equalsIgnoreCase(ipAddress)) {
             //Proxy-Client-IP：apache 服务代理
             ipAddress = request.getHeader("Proxy-Client-IP");
         }
         if (ipAddress==null||ipAddress.length()==0||unknown.equalsIgnoreCase(ipAddress)){
             //WL-Proxy-Client-IP：weblogic 服务代理
             ipAddress=request.getHeader("WL-Proxy-Client-IP");
         }
         if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
             //HTTP_CLIENT_IP：有些代理服务器
             ipAddress = request.getHeader("HTTP_CLIENT_IP");
         }

         if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
             //X-Real-IP：nginx服务代理
             ipAddress = request.getHeader("X-Real-IP");
         }

         //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
         if (ipAddress != null && ipAddress.length() != 0) {
             ip = ipAddress.split(",")[0];
         }

         //还是不能获取到，最后再通过request.getRemoteAddr();获取
         if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
             ip = request.getRemoteAddr();
         }
         return ip;
     }
}
