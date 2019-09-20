package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 藏品类别表
 */
@Data
@Component
public class Collectiontype implements Serializable {
    private static final long serialVersionUID = -4112338268061549658L;
    private Integer collectiontypeid;//藏品类别ID
    private String collectiontypename;//藏品类别名称 画/玉器/铜器
    private Integer collectionparenttypeid;//藏品父类别ID
    private List<Collectiontype> types;  //子类别

}
