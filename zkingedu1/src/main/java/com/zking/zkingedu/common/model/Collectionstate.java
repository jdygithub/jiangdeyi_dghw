package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 藏品完好度状态表
 */
@Component
@Data
public class Collectionstate implements Serializable {

    private static final long serialVersionUID = 4786662593634907709L;
    private Integer collectionstateid;//藏品状态ID
    private String collectiondegree;//状态程度
}
