package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 入库表
 */
@Component
@Data
public class Rwarehouse implements Serializable {

    private static final long serialVersionUID = -5154449130767453471L;
    private Integer rwarehouseid;//入库表ID
    private String rwarehousedate;//入库日期
    private Integer empid;//经办人
    private Integer comegocollectionid;//入库产品表ID 多个
    private Integer rwarehouseidnumber;//入库数量
    private String remark;//备注
}
