package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 修复记录表
 */
@Data
@Component
public class Afterrepair implements Serializable {

    private static final long serialVersionUID = 7220012881590400248L;
    private Integer afterrepairid;//修复记录ID
    private Integer collectionid;//修复藏品ID
    private String afterimage;//修复后图片
    private String repairdate;//修复时长
    private Integer empid;//修复负责人
    private Integer collectionstateid;//当前藏品状态
    private String date;//记录表生成时间
    private String remark;//备注
}
