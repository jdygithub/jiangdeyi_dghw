package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.vo.CheckBox;
import com.zking.zkingedu.common.vo.Role_Menu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<CheckBox> findObjects();

    //角色的所有菜单信息
    List<Role_Menu> findMenuByRole(Integer id);

    List<Menu> getChildlist(Integer id);
    //所有角色
    List<Role> AllRole();

    //所有角色的信息
    List<Role_Menu> AllRoleByMenu();
}