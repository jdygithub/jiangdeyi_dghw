package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Collections;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 藏品表接口
 */
public interface CollectionsDao {
    //添加藏品表
    int addcollection(@Param("Collections")Collections collections);
    //删除藏品表
    int delcollection(Map map);
    //修改藏品信息
    int updatecollection(Map map);
    //查询藏品表信息
    List<Map> getcollections(Map map);
    //查询单个藏品信息
    Map getcollection(Map map);
    //查询图片
    List<Map> getimages(Map map);
    //查询藏品表信息#出入库
    List<Map> getcollectionsbycr(Map map);
    //查询所有管理员
    List<Map> getemps();
    //添加藏品图片表
    int addcollectionimage(Map map);

}
