package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Loginfo;

import java.util.List;

public interface LogService {
    int savelog(Loginfo loginfo);

    List<Loginfo> AllLog();
}
