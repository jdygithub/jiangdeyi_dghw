package com.zking.zkingedu.common.dao;

import java.util.List;
import java.util.Map;

/**
 * 出库模块
 */
public interface CwarehouseDao {
    //添加出库表
    int addcwarehouse(Map map);
    //查询出库表
    List<Map> getcwarehouse(Map map);
}
