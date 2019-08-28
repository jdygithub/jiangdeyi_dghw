package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 架子表
 */
@Data
@Component
public class Shelf implements Serializable {

    private static final long serialVersionUID = -7975889386442331027L;
    private Integer shelfid;//架子ID
    private Integer areaid;//区域ID
    private String shelfname;//架子名称
    private Integer tiernumber;//层数
    private Integer knobnumber;//节数

}
