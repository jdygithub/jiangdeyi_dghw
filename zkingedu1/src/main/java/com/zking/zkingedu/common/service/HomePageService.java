package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Echarts;

import java.util.List;
import java.util.Map;

public interface HomePageService {
    List<Map<String,Object>> SelectType();

    List<Echarts> SelectList();
}
