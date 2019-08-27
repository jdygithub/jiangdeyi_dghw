package com.zking.zkingedu.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -1740896798844984592L;
    private Integer uid;
    private String uname;
    private String upwd;
    private String salt;
    private List<Role> roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getCredentialsSalt() {
        return uname + salt + salt;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
