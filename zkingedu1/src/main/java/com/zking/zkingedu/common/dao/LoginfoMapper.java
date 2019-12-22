package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Loginfo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface LoginfoMapper {
    int deleteByPrimaryKey(Integer logid);

    int insert(Loginfo record);

    int insertSelective(Loginfo record);

    Loginfo selectByPrimaryKey(Integer logid);

    int updateByPrimaryKeySelective(Loginfo record);

    int updateByPrimaryKey(Loginfo record);

    List<Loginfo> AllLog();
}