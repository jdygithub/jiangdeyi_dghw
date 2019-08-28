package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 */
@Data
@Component
public class Role implements Serializable {
    private static final long serialVersionUID = 6985644619420539431L;
    private Integer rid;
    private String role;
    private List<Permission> permissions;
//    private List<User> users;


}
