package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.RepairDao;
import com.zking.zkingedu.common.service.RepairService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 藏品表接口  实现层
 */
@Service("repairService")
public class RepairServiceImpl implements RepairService {
    @Resource
    private RepairDao repairDao;


    @Override
    public int addrepair(Map map) {
        return repairDao.addrepair(map);
    }

    @Override
    public List<Map> getrepairs(Map map) {
        return repairDao.getrepairs(map);
    }

    @Override
    public int delrepair(Map map) {
        return repairDao.delrepair(map);
    }

    @Override
    public int updaterepair(Map map) {
        return repairDao.updaterepair(map);
    }
}
