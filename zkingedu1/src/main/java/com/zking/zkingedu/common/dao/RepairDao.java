package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Collections;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 藏品表接口
 */
public interface RepairDao {
    //添加修复表
    int addrepair(Map map);
    //查询修复表
    List<Map> getrepairs(Map map);
    //删除修复表单个数据
    int delrepair(Map map);
    //修改
    int updaterepair(Map map);
}
