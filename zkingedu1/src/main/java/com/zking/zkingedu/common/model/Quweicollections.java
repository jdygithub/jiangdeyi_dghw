package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 区位藏品表
 */
@Data
@Component
public class Quweicollections implements Serializable {
    private static final long serialVersionUID = -6556123719398535572L;
    private Integer quweicollectionsid;//区位藏品表ID
    private Integer quweiid;//区位ID
    private Integer collectionid;//藏品ID
}
