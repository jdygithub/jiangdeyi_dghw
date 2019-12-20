package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.LoginfoMapper;
import com.zking.zkingedu.common.model.Loginfo;
import com.zking.zkingedu.common.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LoginfoMapper loginfoMapper;
    @Override
    public int savelog(Loginfo loginfo) {
        System.out.println("数据为"+loginfo);
        return loginfoMapper.insert(loginfo);
    }

    @Override
    public List<Loginfo> AllLog() {
        List<Loginfo> list=loginfoMapper.AllLog();
        return list;
    }
}
