package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Employee;
import com.zking.zkingedu.common.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer empid);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer empid);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    Employee findEmpByUsername(String username);

    /**
     * 根据用户id查找用户权限标识信息
     * 列如方法的路径 sys:role:view,sys:role:add
     * @param username
     * @return
     */
    List<Map<String,Object>> findEmpPermissions(String username);

    List<Menu> findPermissions(Integer empid);

    int getrowCount(@Param("username") String username);

    int StateById(@Param("id") Integer id,
                  @Param("state") Integer state
    );

    Map<String,Object> findObjectById(Integer id);

    List<Employee> findAllEmpUser();
    //拿到所有菜单
    List<Menu> AllMessions();
}