package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Employee;
import com.zking.zkingedu.common.vo.CheckBox;

import java.util.List;
import java.util.Map;


public interface EmployeeService {
    /**
     * 执行处理业务处理
     * @param username
     * @param password
     */
    void  login(String username, String password);
    /**
     * 根据用户id查找用户信息以及对应的角色
     * @param id
     * @return 封装用户以及用户对应的角色ID
     */
    Map<String,Object> findObjectById(Integer id);

    void updateObject(Employee entity, String roleIds);

    int deleteObject(Integer id);

    List<CheckBox> findRoles();

    int saveObject(Employee entity, String roleIds);

    int StateById(Integer id, Integer state);


}
