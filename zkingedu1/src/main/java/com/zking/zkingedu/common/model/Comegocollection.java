package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 出入库藏品表
 */
@Data
@Component
public class Comegocollection implements Serializable {

    private static final long serialVersionUID = 4644596414324545707L;
    private String comegocollectionid;//出入藏品表ID
    private Integer collectionid;//藏品ID
    private Integer comego;//出/入     0出 1入
    private Integer okandno;//出库归还状态 入库为null 0 未还 1已还
}
