package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.QuweiDao;
import com.zking.zkingedu.common.service.QuweiService;
import com.zking.zkingedu.common.service.QuweicollectionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("quweiService")
public class QuweiServiceImpl implements QuweiService {
    @Resource
    private QuweiDao quweiDao;
    @Resource
    private QuweicollectionsService quweicollectionsService;

    @Override
    public List<Map> getquwei(Map map) {
        List<Map> getquwei = quweiDao.getquwei(map);
        if(getquwei!=null&&getquwei.size()!=0){
            for (Map map1 : getquwei) {
                //遍历区域把该区域的藏品放入该集合中
                List<Map> getquweicollections = quweicollectionsService.getquweicollections(map1);
                map1.put("quweicollections",getquweicollections);
            }
        }
        return getquwei;
    }

    @Override
    public int delquwei(Map map) {
        return quweiDao.delquwei(map);
    }

    @Override
    public int updatequwei(Map map) {
        return quweiDao.updatequwei(map);
    }

    @Override
    public int addquwei(Map map) {
        return quweiDao.addquwei(map);
    }

    @Override
    public Map getquweibyid(Map map) {
        return quweiDao.getquweibyid(map);
    }

    @Override
    public int nunbersx(Map map) {
        return quweiDao.nunbersx(map);
    }
}
