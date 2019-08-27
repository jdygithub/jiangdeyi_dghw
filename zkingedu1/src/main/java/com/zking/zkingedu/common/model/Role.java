package com.zking.zkingedu.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 6985644619420539431L;
    private Integer rid;
    private String role;
    private List<Permission> permissions;
    private List<User> users;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
