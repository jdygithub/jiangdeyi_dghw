package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 藏品图片表
 */
@Data
@Component
public class Collectionimage implements Serializable {
    private static final long serialVersionUID = 4425439099631618205L;
    private Integer imageid;//图片ID
    private Integer collectionid;//藏品ID
    private String collectionimage;//藏品图片
}
