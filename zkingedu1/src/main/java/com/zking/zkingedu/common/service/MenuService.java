package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    int saveObject(Menu entity);

    int updateObject(Menu entity);

    List<Map<String,Object>> findObjects();

    List<Map<String,Object>> findObjectById(Integer id);

    int deleteObject(Integer id);

    List<Menu> getmenus(Integer empid);



}
