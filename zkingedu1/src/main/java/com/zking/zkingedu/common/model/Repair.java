package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 修复申请表
 */
@Data
@Component
public class Repair implements Serializable {

    private static final long serialVersionUID = 1926663816085039221L;
    private Integer repairableid;//修复申请表ID
    private Integer collectionid;//申请藏品ID
    private String timelong;//修复预计时长
    private String repairaddress;//修复地点
    private Integer empid;//经手人
    private String date;//申请记录时间
    private String oktime;//修复完成时间
    private Integer repairstate;//当前修复状态
    private String remark;//备注
}
