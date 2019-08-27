package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> getAllUser();
    User getUserByname(String uname);
}
