package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.QuweiDao;
import com.zking.zkingedu.common.dao.StoreroomDao;
import com.zking.zkingedu.common.service.StoreroomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("storeroomService")
public class StoreroomServiceImpl implements StoreroomService {
    @Resource
    private StoreroomDao storeroomDao;
    @Resource
    private QuweiDao quweiDao;

    @Override
    public int addstoreroom(Map map) {
        return storeroomDao.addstoreroom(map);
    }

    @Override
    public List<Map> getstorerooms(Map map) {
        return storeroomDao.getstorerooms(map);
    }

    @Override
    public int updatestoreroom(Map map) {
        return storeroomDao.updatestoreroom(map);
    }

    @Override
    public int delstoreroom(Map map) {
        return storeroomDao.delstoreroom(map);
    }

    @Override
    public Map getstoreroom(Map map) {
        return storeroomDao.getstoreroom(map);
    }

    @Override
    public List<Map> getstoreroomandquwei() {
        List<Map> getstorerooms = storeroomDao.getstorerooms(null);
        for (Map getstoreroom : getstorerooms) {//遍历库房集合
            Map map1 = new HashMap();
            map1.put("storeroomid",getstoreroom.get("storeroomid"));//创建一个集合 把遍历的ID放入
            List<Map> getquwei = quweiDao.getquwei(map1);//根据仓库的ID查询一个一个的区位  区位集合
            getstoreroom.put("getquwei",getquwei);//放入该库房中
        }
        return getstorerooms;
    }

}
