package com.zking.zkingedu.common.service.impl;


import com.zking.zkingedu.common.dao.UserDao;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUserByname(String uname) {
        return userDao.getUserByname(uname);
    }
}
