package com.zking.zkingedu.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限
 */
@Data
public class Permission implements Serializable{
    private static final long serialVersionUID = 4163868342370604646L;
    private Integer pid;
    private String pname;
    private List<Role> roles;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
