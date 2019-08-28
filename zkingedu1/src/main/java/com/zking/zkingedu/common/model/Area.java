package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 库房区域表
 */
@Data
@Component
public class Area implements Serializable {

    private static final long serialVersionUID = 6422193577689312235L;
    private Integer areaid;//区域ID
    private Integer storeroomid;//库房ID
    private String areaname;//区域名称
    private String remark;//备注
}
