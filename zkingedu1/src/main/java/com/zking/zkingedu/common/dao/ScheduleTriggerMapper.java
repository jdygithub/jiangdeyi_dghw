package com.zking.zkingedu.common.dao;


import com.zking.zkingedu.common.model.ScheduleTrigger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleTriggerMapper {
    List<ScheduleTrigger> queryScheduleTrigger();
}