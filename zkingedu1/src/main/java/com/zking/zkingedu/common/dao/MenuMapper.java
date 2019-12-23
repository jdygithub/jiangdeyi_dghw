package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 查询当前菜单对应的子菜单个数
     * @return int
     */
    int getChildCount(Integer id);

    /**
     * 根据id查找当前菜单以及上级菜单相关信息
     * @param id
     * @return
     */
    List<Map<String,Object>> findObjectById(Integer id);

    /**获取菜单信息及对应的上级菜单的名称
     * Map<String,Object>：一行记录对应一个map
     * key:为字段名
     * value:为字段对应值
     * 多行记录对应多个map，多个map存储到list集合
     * */
    List<Map<String,Object>> findObjects();


    List<Menu> getMenuList(int id);

    List<Menu> getChildlist(Integer id);

    List<Menu> getmenus(@Param(value="empid")Integer empid);
}