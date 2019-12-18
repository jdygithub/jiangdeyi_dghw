package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.ComegocollectionDao;
import com.zking.zkingedu.common.dao.RwarehouseDao;
import com.zking.zkingedu.common.service.RwarehouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("rwarehouseService")
public class RwarehouseServiceImpl implements RwarehouseService {
    @Resource
    private RwarehouseDao rwarehouseDaowarehouseDao;
    @Resource
    private ComegocollectionDao comegocollectionDao;

    //添加入库表
    @Override
    public int addrwarehouse(Map map) {
        //入库后修改出入库藏品的 出入状态
        comegocollectionDao.updatecomego(map);
        return rwarehouseDaowarehouseDao.addrwarehouse(map);
    }
    //查询入库表
    @Override
    public List<Map> getrwarehouse(Map map) {
        return rwarehouseDaowarehouseDao.getrwarehouse(map);
    }

}
