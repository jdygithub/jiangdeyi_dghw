package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CollectionsDao;
import com.zking.zkingedu.common.model.Collections;
import com.zking.zkingedu.common.service.CollectionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 藏品表接口  实现层
 */
@Service("collectionsService")
public class CollectionsServiceImpl implements CollectionsService {
    @Resource
    private CollectionsDao collectionsDao;

    //添加藏品表
   public int addcollection(Collections collections){
       return collectionsDao.addcollection(collections);
   }

    @Override
    public int delcollection(Map map) {
        return collectionsDao.delcollection(map);
    }

    @Override
    public int updatecollection(Map map) {
        return collectionsDao.updatecollection(map);
    }

    @Override
    public List<Map> getcollections(Map map) {
        List<Map> getcollections = collectionsDao.getcollections(map);
        List<Map> getimages = collectionsDao.getimages(null);
        for (Map getcollection : getcollections) {
            for (Map getimage : getimages) {
                if(getcollection.get("collectionid").equals(getimage.get("collectionid"))){
                    getcollection.put("image",getimage.get("image"));
                    break;
                }
                else{
                    getcollection.put("image",null);
                }
            }
        }
        return getcollections;
    }

    @Override
    public Map getcollection(Map map) {
        Map getcollection = collectionsDao.getcollection(map);
        List<Map> getimages = collectionsDao.getimages(map);
        getcollection.put("getimages",getimages);
        return getcollection;
    }
    @Override
    public List<Map> getimages(Map map) {
        return collectionsDao.getimages(map);
    }


}
