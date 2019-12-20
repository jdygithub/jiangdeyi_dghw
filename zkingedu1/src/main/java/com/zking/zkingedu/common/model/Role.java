package com.zking.zkingedu.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 6985644619420539431L;
    private Integer roleid;
    private String rolename;


    public  Role(){}

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }


}
