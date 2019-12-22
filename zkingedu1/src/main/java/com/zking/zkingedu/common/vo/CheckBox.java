package com.zking.zkingedu.common.vo;

import java.io.Serializable;

public class CheckBox implements Serializable {
     private  static final  long serialVersionUID=4163868342370604646L;
     private  Integer roleid;
     private  String rolename;

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
