package com.zking.zkingedu.common.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface Menu_RoleMapper {

    /**
     * 判定关系表中菜单是否被角色使用
     * @param menuId
     * @return
     */
    int getMenuCount(Integer menuId);

    int insertObject(
            @Param("roleId") Integer roleId,
            @Param("menuIds") String[] menuIds);

    List<Integer> findMenuIdsByRoleId(
            Integer roleId);

    int deleteObjectByRoleId(Integer roleId);
    int deleteObjectByMenuId(Integer menuId);
    int deleteObjectByUserId(Integer userId);

}