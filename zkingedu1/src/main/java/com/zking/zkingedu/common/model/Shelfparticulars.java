package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 架子详情表
 */
@Data
@Component
public class Shelfparticulars implements Serializable {

    private static final long serialVersionUID = 5405237536990540900L;
    private String abcdname;//架位名称 库房门+区域名+架子名+位名
    private Integer shelfid;//架子ID
    private Integer noempty;//是否为空
    private Integer collectionid;//藏品ID 为空的话藏品ID也为空
}
