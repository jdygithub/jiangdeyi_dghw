package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Collectiontype;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 藏品类别表
 */
public interface CollectiontypeDao {
    //查询类别
    List<Collectiontype> getalltypes();
    //增加类别
    int addtype(Collectiontype collectiontype);
    //删除类别
    int deltype(@Param("collectiontypeid")Integer collectiontypeid);
    //修改类别
    int updatetype(Collectiontype collectiontype);
    //查询父类还有没有子类
    int exitid(@Param("collectiontypeid")Integer collectiontypeid);
    //查询所有的父类
    List<Collectiontype> gettypeo();
}
