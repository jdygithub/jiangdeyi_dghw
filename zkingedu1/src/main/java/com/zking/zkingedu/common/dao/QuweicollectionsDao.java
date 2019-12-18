package com.zking.zkingedu.common.dao;

        import java.util.List;
        import java.util.Map;

/**
 * 区位表接口
 */
public interface QuweicollectionsDao {
    //查询各个区位的藏品信息
    List<Map> getquweicollections(Map map);
    //删除该区位藏品信息
    int delquweicollections(Map map);
    //修改该区位藏品信息
    int updatequweicollections(Map map);
    //添加该区位藏品信息
    int addquweicollections(Map map);
}
