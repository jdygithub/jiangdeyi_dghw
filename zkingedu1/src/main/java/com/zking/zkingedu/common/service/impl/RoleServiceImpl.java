package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.Emp_RoleMapper;
import com.zking.zkingedu.common.dao.MenuMapper;
import com.zking.zkingedu.common.dao.Menu_RoleMapper;
import com.zking.zkingedu.common.dao.RoleMapper;
import com.zking.zkingedu.common.model.Menu;
import org.apache.shiro.SecurityUtils;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.RoleService;
import com.zking.zkingedu.common.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private Emp_RoleMapper emp_roleMapper;
    @Autowired
    private Menu_RoleMapper menu_roleMapper;
    @Autowired
    private  MenuMapper menuMapper;
    @Override
    public int deleteObject(Integer id) {
        //参数合法性验证
        if (StringUtils.isEmpty(id))
            throw  new ServiceException("必须选中才可删除");
        //删除
        int rows=roleMapper.deleteByPrimaryKey(id);
        if (rows==0)
            throw  new ServiceException("数据已经不存在");
         System.out.println("rows为"+rows);
         emp_roleMapper.deleteObjectByRoleId(Integer.valueOf(id));
         menu_roleMapper.deleteObjectByRoleId(Integer.valueOf(id));
         return  rows;
    }

    @Override
    public int saveObject(Role entity, String menuid) {
        if (entity==null)
            throw  new ServiceException("保存信息不能为空");
        if (StringUtils.isEmpty(entity.getRolename()))
            throw  new ServiceException("角色名字不能为空");
        if (StringUtils.isEmpty(menuid))
            throw new ServiceException("您的权限不能为空");
        //保存数据
        int rows;
        try {
/*           String loginAccount=SecurityUtils.getSubject().toString();
            System.out.println("当前登录的用户名为="+loginAccount);*/
            rows=roleMapper.insert(entity);
            System.out.println("role类为"+entity);
            System.out.println("roid为"+entity.getRoleid());
            menu_roleMapper.insertObject(entity.getRoleid(),menuid.split(","));
        }catch (Exception e){
             e.printStackTrace();
             throw  new ServiceException("保存失败");
        }
        return rows;
    }

    @Override
    public Map<String, Object> findObjectById(Integer id) {
        if (id==null||id<=0)
        throw  new ServiceException("id的值不合法");
        //数据查询
        Role role=roleMapper.selectByPrimaryKey(id);
        List<Integer> menuIds=menu_roleMapper.findMenuIdsByRoleId(id);
        if (role==null)
            throw  new ServiceException("不存在该用户");
        if (menuIds==null)
            throw new ServiceException("没有该用户的角色");
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("role",role);
        map.put("menuIds",menuIds);
        return map;
    }

    @Override
    public int updateObject(Role entity, String menuIds) {
        if (entity==null)
            throw  new ServiceException("更新信息不能为空");
        if (entity.getRoleid()==null)
            throw  new ServiceException("id的值不能为空");
        if (StringUtils.isEmpty(entity.getRolename()))
            throw  new ServiceException("角色名字不能为空");
        if (StringUtils.isEmpty(menuIds))
        throw  new ServiceException("必须给该角色赋予权限");
         //判断对象是否存在
        menuIds.toString();
        Role role=
        roleMapper.selectByPrimaryKey(entity.getRoleid());
        if (role==null)
            throw  new ServiceException("该用户可能不存在");
        int rows;
        //更新数据
        try {
            rows=roleMapper.updateByPrimaryKey(entity);
            //只需要删除菜单,在重新插入,用户表不需要动
            menu_roleMapper.deleteObjectByRoleId(entity.getRoleid());
            menu_roleMapper.insertObject(entity.getRoleid(),menuIds.split(","));
        }catch (Exception e){
              e.printStackTrace();
              throw  new ServiceException("更新失败");
        }
        return  rows;
    }

}
