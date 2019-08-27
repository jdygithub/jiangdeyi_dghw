package com.zking.zkingedu.common.dao;


import com.zking.zkingedu.common.model.ScheduleTriggerParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleTriggerParamMapper {
    List<ScheduleTriggerParam> queryScheduleParam(Integer triggerId);
}