package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 出库表
 */
@Data
@Component
public class Cwarehouse implements Serializable {

    private static final long serialVersionUID = 459745710260771499L;
    private String cwarehouseid;//出库单号ID
    private String cwarehousetext;//出库用途详细信息
    private Integer cwarehousenumber;//出库数量
    private String returndate;//拟还日期
    private Integer empid;//经办人
    private String date;//出库日期
//    private Integer comegocollectionid;//出库产品表ID  一次出库多件藏品
    private String remark;//备注
}
