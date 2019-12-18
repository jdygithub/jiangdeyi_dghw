package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CollectionsDao;
import com.zking.zkingedu.common.dao.QuweicollectionsDao;
import com.zking.zkingedu.common.service.QuweicollectionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("quweicollectionsService")
public class QuweicollectionsServiceImpl implements QuweicollectionsService {
    @Resource
    private QuweicollectionsDao quweicollectionsDao;
    @Resource
    private CollectionsDao collectionsDao;

    @Override
    public List<Map> getquweicollections(Map map) {

        List<Map> getquweicollections = quweicollectionsDao.getquweicollections(map);
        List<Map> getimages = collectionsDao.getimages(null);
        for (Map getcollection : getquweicollections) {
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
        return getquweicollections;
    }

    @Override
    public int delquweicollections(Map map) {
        return quweicollectionsDao.delquweicollections(map);
    }

    @Override
    public int updatequweicollections(Map map) {
        return quweicollectionsDao.updatequweicollections(map);
    }

    @Override
    public int addquweicollections(Map map) {
        return quweicollectionsDao.addquweicollections(map);
    }
}
