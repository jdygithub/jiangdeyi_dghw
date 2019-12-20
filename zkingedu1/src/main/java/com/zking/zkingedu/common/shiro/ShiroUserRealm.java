
package com.zking.zkingedu.common.shiro;//package com.zking.zkingedu.common.shiro;

import com.zking.zkingedu.common.dao.EmployeeMapper;
import com.zking.zkingedu.common.model.Employee;
import com.zking.zkingedu.common.service.EmployeeService;
import com.zking.zkingedu.common.utils.ServiceException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class ShiroUserRealm extends AuthorizingRealm {
    @Resource
   private EmployeeMapper mapper;
    //授权认证
   @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

       return null;
   }

    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("realm.登录操作");
        //获取用户身份信息
        UsernamePasswordToken utoken;
        utoken = (UsernamePasswordToken) token;
        String username = utoken.getUsername();
        String password=new String((char[])utoken.getPassword());
        System.out.println("usernmae为"+username);

        //在数据库查询用户信息
        Employee employee = mapper.findEmpByUsername(username);
        String md5Pwd=new Md5Hash(password).toHex();
        System.out.println("加密后的密码是"+md5Pwd);
        System.out.println("——————>>用户信息为"+employee);
        if (employee==null){
            return  null;
        }
        if (md5Pwd.equals(employee.getEmppassword())){
            System.out.println("密码一致");
        }else {
            System.out.println("密码不一致");
            //throw  new ServiceException("密码错误");
        }
        //对用户信息进行封装返回
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                employee.getEmpname(),//主身份
                employee.getEmppassword(),//已加密的密码
                getName()//realm的名字
        );
        return info;
    }
}

