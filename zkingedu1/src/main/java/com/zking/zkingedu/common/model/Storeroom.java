package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 库房表
 */
@Component
@Data
public class Storeroom implements Serializable{

    private static final long serialVersionUID = 9064150226496273534L;
    private Integer storeroomid;//库房ID
    private String storeroomname;//库房名称
    private String storeroomtext;//库房信息
    private String storeroomtemperature;//库房温度
    private String storeroomhumidness;//库房湿度
    private Integer storeroomclerk;//库房管理员  员工ID
    private String remark;//备注
}
