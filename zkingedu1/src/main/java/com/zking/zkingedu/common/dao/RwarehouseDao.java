package com.zking.zkingedu.common.dao;

import java.util.List;
import java.util.Map;

/**
 * 入库模块
 */
public interface RwarehouseDao {

    //添加入库表
    int addrwarehouse(Map map);
    //查询入库表
    List<Map> getrwarehouse(Map map);


}
