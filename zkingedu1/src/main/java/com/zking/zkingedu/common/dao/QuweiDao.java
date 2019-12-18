package com.zking.zkingedu.common.dao;

import java.util.List;
import java.util.Map;

/**
 * 区位表接口
 */
public interface QuweiDao {
    //查询各个库房的区位信息
    List<Map> getquwei(Map map);
    //查询单个区位的信息
    Map getquweibyid(Map map);
    //删除该库房的区位信息
    int delquwei(Map map);
    //修改该库房的区位信息
    int updatequwei(Map map);
    //添加该库房的区位信息
    int addquwei(Map map);
    //刷新数量
    int nunbersx(Map map);
}
