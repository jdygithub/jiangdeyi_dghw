//package com.zking.zkingedu.common.shiro;
//
//
//import com.zking.zkingedu.common.model.Permission;
//import com.zking.zkingedu.common.model.Role;
//import com.zking.zkingedu.common.model.User;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class MyRealm extends AuthorizingRealm {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("开始授权");
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        String username = principalCollection.getPrimaryPrincipal().toString();
//        User user1 = userService.getUserByname(username);
//
//        List<Role> roles = user1.getRoles();
//        for (Role role : roles) {
//            simpleAuthorizationInfo.addRole(role.getRole());
//            List<Permission> permissions = role.getPermissions();
//            for (Permission permission : permissions) {
//                System.out.println(permission.getPname());
//                simpleAuthorizationInfo.addStringPermission(permission.getPname());
//            }
//        }
//        return simpleAuthorizationInfo;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("来认证");
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
//        String username = usernamePasswordToken.getPrincipal().toString();
//
//        User user = userService.getUserByname(username);
//
//        if(user==null){
//            return null;
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUname(), user.getUpwd(),getName());
//        return authenticationInfo;
//    }
//}
