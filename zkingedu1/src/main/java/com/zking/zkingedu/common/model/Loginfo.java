package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 日志记录表
 */
@Data
@Component
public class Loginfo implements Serializable {

    private static final long serialVersionUID = -3557983717050588807L;
    private Integer logid;//日志ID
    private Integer empid;//用户ID
    private String operationtext;//操作详情
    private String operationdate;//操作时间
}
