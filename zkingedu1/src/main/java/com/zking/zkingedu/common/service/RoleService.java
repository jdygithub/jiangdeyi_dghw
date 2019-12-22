package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    int deleteObject(Integer id);

    int saveObject(Role entity, String menuIds);

    Map<String,Object> findObjectById(Integer id);

    int updateObject(Role entity, String menuIds);

}
