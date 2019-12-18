package com.zking.zkingedu.common.service;

import java.util.List;
import java.util.Map;
/**
 * 出入库藏品信息
 */
public interface ComegocollectionService {
    //添加出库表
    int addcomego(Map map);
    //查询出库表
    List<Map> getcomego(Map map);
    //删除出入库单个藏品信息
    int delcomego(Map map);
    //入库后修改出入库藏品的 出入状态
    int updatecomego(Map map);
}
