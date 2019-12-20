package com.zking.zkingedu.common.service.impl;

import com.sun.rowset.internal.Row;
import com.zking.zkingedu.common.dao.MenuMapper;
import com.zking.zkingedu.common.dao.Menu_RoleMapper;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.service.MenuService;
import com.zking.zkingedu.common.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private Menu_RoleMapper menu_roleMapper;

    @Override
    public int saveObject(Menu entity) {
        if (entity==null)
            throw new ServiceException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getMenuname()))
            throw  new ServiceException("菜单名字不能为空");
        int rows;
        //更新数据
        try {
            rows=menuMapper.insert(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw  new ServiceException("保存失败");
        }
        return rows;
    }

    @Override
    public int updateObject(Menu entity) {
        if (entity==null)
            throw new ServiceException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getMenuname()))
            throw  new ServiceException("菜单名字不能为空");
        int rows;
        //更新数据
        try {
            rows=menuMapper.updateByPrimaryKey(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw  new ServiceException("更新失败");
        }
        return rows;
    }

    @Override
    public List<Map<String, Object>> findObjects() {
        return menuMapper.findObjects();
    }

    @Override
    public List<Map<String, Object>> findObjectById(Integer id) {
        if (id==null||id<=0)
            throw  new ServiceException("数据不合法="+id);
        List<Map<String,Object>> map=menuMapper.findObjectById(id);
        if (map==null||map.size()==0)
            throw  new ServiceException("此记录不存在");
        return map;
    }

    @Override
    public int deleteObject(Integer id) {
        if (id==null||id<=0)
            throw  new ServiceException("数据不合法="+id);
        //执行删除操作
        //判断该节点有没有子节点
        int count=menuMapper.getChildCount(id);
        if (count>0)
            throw  new ServiceException("此元素有子元素不允许删除");
        int rows=menuMapper.deleteByPrimaryKey(id);
        //删除角色菜单表中绑定的菜单记录
        menu_roleMapper.deleteObjectByMenuId(id);
        if (rows==0)
            throw  new ServiceException("此菜单在数据库已经不存在");

        return rows;
    }
}
