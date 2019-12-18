package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CwarehouseDao;
import com.zking.zkingedu.common.service.CwarehouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("cwarehouseService")
public class CwarehouseServiceImpl implements CwarehouseService {
    @Resource
    private CwarehouseDao cwarehouseDao;

    //添加出库表
    public int addcwarehouse(Map map){
        return cwarehouseDao.addcwarehouse(map);
    };

    //查询出库表
    public  List<Map> getcwarehouse(Map map){
        return cwarehouseDao.getcwarehouse(map);
    };

}
