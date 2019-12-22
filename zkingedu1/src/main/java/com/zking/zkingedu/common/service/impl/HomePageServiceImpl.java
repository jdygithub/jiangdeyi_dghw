package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.HomePageMapper;
import com.zking.zkingedu.common.model.Echarts;
import com.zking.zkingedu.common.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    private  HomePageMapper homePageMapper;
    @Override
    public List<Map<String, Object>> SelectType() {
        List<Map<String,Object>> list= homePageMapper.SelectType();
        return list;
    }

    @Override
    public List<Echarts> SelectList() {
        List<Echarts> list= homePageMapper.SelectList();
        return list;
    }
}
