package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 区位表
 */
@Data
@Component
public class Quwei implements Serializable {
    private static final long serialVersionUID = -7023651035970912389L;
    private Integer quweiid;//区位ID
    private Integer storeroomid;//库房ID
    private String quweiname;//区位名称
    private Integer nimber;//备注
    private String remark;//藏品数量
}
