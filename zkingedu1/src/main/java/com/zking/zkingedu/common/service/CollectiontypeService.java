package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Collectiontype;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 藏品类别表
 */
public interface CollectiontypeService {
    //查询类别(藏品添加,下拉框的值)
    List<Collectiontype> getalltypes();
    //查询类别(类别管理,treetable的值)
    List<Collectiontype> gettypes();
    //查询父类别
    List<Collectiontype> gettypeo();
    //增加类别
    int addtype(@Param("collectiontype") Collectiontype collectiontype);
    //删除类别
    int deltype(@Param("collectiontypeid")Integer collectiontypeid);
    //修改类别
    int updatetype(@Param("collectiontype")Collectiontype collectiontype);
    //查询父类还有没有子类
    int exitid(@Param("collectiontypeid")Integer collectiontypeid);
}
