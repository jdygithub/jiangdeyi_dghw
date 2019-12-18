package com.zking.zkingedu.common.service;

import java.util.List;
import java.util.Map;

/**
 * 库房信息模块
 */
public interface StoreroomService {
    //添加库房表
    int addstoreroom(Map map);
    //查询库房表
    List<Map> getstorerooms(Map map);
    //修改库房信息
    int updatestoreroom(Map map);
    //删除库房
    int delstoreroom(Map map);
    //查询单个库房信息
    Map getstoreroom(Map map);
    //查询库房和以下的区位
    List<Map> getstoreroomandquwei();
}
