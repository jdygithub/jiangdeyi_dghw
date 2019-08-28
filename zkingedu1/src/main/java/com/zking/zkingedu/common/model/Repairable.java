package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 修复申请表
 */
@Data
@Component
public class Repairable implements Serializable {

    private static final long serialVersionUID = 1926663816085039221L;
    private Integer repairableid;//修复申请表ID
    private Integer collectionid;//申请藏品ID
    private String beforeimage;//修复前藏品图片
    private String repairdate;//所需时间
    private Integer empid;//经手人
    private String date;//申请记录时间
    private Integer collectionstateid;//当前藏品状态
    private String remark;//备注
}
