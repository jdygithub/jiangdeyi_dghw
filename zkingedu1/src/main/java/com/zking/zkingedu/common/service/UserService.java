package com.zking.zkingedu.common.service;


import com.zking.zkingedu.common.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();

    public User getUserByname(String uname);
}
