package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Echarts;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface HomePageMapper {
    List<Map<String,Object>> SelectType();

    List<Echarts> SelectList();

}
