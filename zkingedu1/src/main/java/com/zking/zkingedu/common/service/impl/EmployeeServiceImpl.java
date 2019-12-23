package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.Emp_RoleMapper;
import com.zking.zkingedu.common.dao.EmployeeMapper;
import com.zking.zkingedu.common.dao.Menu_RoleMapper;
import com.zking.zkingedu.common.dao.RoleMapper;
import com.zking.zkingedu.common.model.Employee;
import com.zking.zkingedu.common.service.EmployeeService;
import com.zking.zkingedu.common.utils.ServiceException;
import com.zking.zkingedu.common.vo.CheckBox;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
   @Autowired
   private EmployeeMapper employeeMapper;
   @Autowired
   private Emp_RoleMapper emp_roleMapper;
   @Autowired
   private RoleMapper roleMapper;
    @Autowired
    private Menu_RoleMapper menu_roleMapper;

    @Override
    public void login(String username, String password) {
    System.out.println(employeeMapper.getClass().getName());
    System.out.println("service.login");
    if (StringUtils.isEmpty(username))
        throw  new ServiceException("用户名不能为空");
    if (StringUtils.isEmpty(password))
        throw  new ServiceException("密码不能为空");
    //获取主体对象
        Subject subject= SecurityUtils.getSubject();
    //封装用户名密码
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        System.out.println("对用户"+username+"进行登录验证..验证开始 ");
    //执行身份认证
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
            throw  new ServiceException("用户名或密码不正确");
        }
        Session session=SecurityUtils.getSubject().getSession();
        Employee employee=employeeMapper.findEmpByUsername(username);
        session.setAttribute("user",employee);
        session.setAttribute("empid",employee.getEmpid());
        System.out.println("user的所有信息 "+employee.toString());
    }

    @Override
    public Map<String, Object> findObjectById(Integer id) {
        //1.合法性验证
        if(id==null||id<1)
            throw new ServiceException("数据不合法,id="+id);
        //2.查询用户信息
        Map<String,Object> emp=employeeMapper.findObjectById(id);
        System.out.println("impl的user="+emp);
        if(emp==null)
            throw new ServiceException("用户已经不存在");

        //3.查询用户角色
        List<Integer> roleIds=
                emp_roleMapper.findRoleIdsByUserId(id);
        System.out.println("impl的roleids信息为"+roleIds);
        //4.封装数据
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("user", emp);
        map.put("roleIds", roleIds);

        return map;
    }

    @Override
    public void updateObject(Employee entity, String roleIds) {
         //验证数据合法性
        if (entity==null)
            throw  new ServiceException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getEmpname()))
            throw  new ServiceException("用户名不能为空");
        if (StringUtils.isEmpty(roleIds))
            throw  new ServiceException("必须要选择一个角色");
        //更新数据
        //1.设置新密码
        System.out.println("entity.getPassword()="+entity.getEmppassword());
        if (!StringUtils.isEmpty(entity.getEmppassword())){
            String pwd=entity.getEmppassword();
            SimpleHash simpleHash=new SimpleHash("MD5",pwd);
            String newPwd=simpleHash.toString();
            entity.setEmppassword(newPwd);
        }
        //2.2 更新数据
        try {
            employeeMapper.updateByPrimaryKey(entity);
            System.out.println("id为"+entity.getEmpid());
            emp_roleMapper.deleteObjectByUserId(entity.getEmpid());
            emp_roleMapper.insertObject(entity.getEmpid(),roleIds.split(","));
        }catch (Exception e){
             e.printStackTrace();
             throw  new ServiceException("系统维护中");
        }

    }

    @Override
    public int deleteObject(Integer id) {
        //参数合法性验证
        if (StringUtils.isEmpty(id))
            throw  new ServiceException("必须选中才可删除");
        //删除
        int rows=employeeMapper.deleteByPrimaryKey(id);
        if (rows==0)
            throw  new ServiceException("数据已经不存在");
        emp_roleMapper.deleteObjectByUserId(Integer.valueOf(id));
        return  rows;
    }

    @Override
    public List<CheckBox> findRoles() {
        return roleMapper.findObjects();
    }

    @Override
    public int saveObject(Employee entity, String roleIds) {
        if (entity==null)
        throw  new ServiceException("保存信息不能为空");
        if (StringUtils.isEmpty(entity.getEmpname()))
            throw  new ServiceException("用户名不能为空");
        if (StringUtils.isEmpty(entity.getEmppassword()))
            throw  new ServiceException("用户名密码不能为空");
        if (StringUtils.isEmpty(roleIds))
            throw  new ServiceException("必须选择一个角色");
        //保存数据
        String pwd=entity.getEmppassword();
        //对密码进行md5加密
        SimpleHash simpleHash=new SimpleHash("MD5",pwd);
        String newpwd=simpleHash.toString();
        entity.setEmppassword(newpwd);
        int rows;
        //存储用户信息
        try {
            rows=employeeMapper.insert(entity);
            //拿到当前插入的对象的id,这里用entity是拿不到的,因为entity是前台的数据，后台插入返回id
        }catch (Exception e){
            e.printStackTrace();
            if (e instanceof DuplicateKeyException){
                throw new ServiceException("此用户已经存在");
            }
            throw new ServiceException("系统维护中");
        }
        //保存用户和角色的数据
        try {
            System.out.println("empid数据为"+entity.getEmpid());
            emp_roleMapper.insertObject(entity.getEmpid(),roleIds.split(","));
        }catch (Exception e){
            e.printStackTrace();
            throw  new ServiceException("保存失败");
        }
        return rows;
    }

    @Override
    public int StateById(Integer id, Integer state)
    {
        if (id==null||id<=0)
            throw new ServiceException("id的值不合法");
        if (state==null)
            throw new ServiceException("状态值不能为空");
        if (state!=0&&state!=1)
            throw new ServiceException("状态值不正确,state="+state);
        //执行更行操作
        int rows;
        try {
            rows=employeeMapper.StateById(id,state);
        }catch (Exception e){
            e.printStackTrace();
            //报警
            throw  new ServiceException("保存失败");
        }
        if (rows==0)
            throw  new ServiceException("数据可能已经不存在");
        return rows;
    }
}
