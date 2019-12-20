package com.zking.zkingedu.common.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface Emp_RoleMapper {

    List<Integer> findRoleIdsByUserId(Integer userId);

    int insertObject(
            @Param("userId") Integer userId,
            @Param("roleIds") String[] roleIds);

    int deleteObjectByRoleId(@Param("roleid") Integer roleid);
    int deleteObjectByUserId(@Param("empid") Integer userId);

}