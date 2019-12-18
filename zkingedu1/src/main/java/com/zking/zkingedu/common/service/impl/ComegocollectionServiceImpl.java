package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CollectionsDao;
import com.zking.zkingedu.common.dao.ComegocollectionDao;
import com.zking.zkingedu.common.service.ComegocollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("comegocollectionService")
public class ComegocollectionServiceImpl implements ComegocollectionService {
    @Resource
    private ComegocollectionDao comegocollectionDao;
    @Resource
    private CollectionsDao collectionsDao;

    //添加出库表
    public int addcomego(Map map){
        return comegocollectionDao.addcomego(map);
    }
    //查询出库表
    public List<Map> getcomego(Map map){
        List<Map> getcomego = comegocollectionDao.getcomego(map);
        if(map.get("collectionid")!=null||map.get("collectionid")!=""){
            List<Map> getimages = collectionsDao.getimages(null);
            for (Map getcomego1 : getcomego) {
                for (Map getimage : getimages) {
                    if(getcomego1.get("collectionid").equals(getimage.get("collectionid"))){
                        getcomego1.put("image",getimage.get("image"));
                        break;
                    }
                    else{
                        getcomego1.put("image",null);
                    }
                }
            }
        }
        return getcomego;
    }
    //删除出入库单个藏品信息
    public int delcomego(Map map){
        return comegocollectionDao.delcomego(map);
    }
    //    <!--入库后修改对应藏品的 出入库状态-->
    @Override
    public int updatecomego(Map map) {
        return comegocollectionDao.updatecomego(map);
    }

}
