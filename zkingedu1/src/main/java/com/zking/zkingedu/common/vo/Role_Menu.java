package com.zking.zkingedu.common.vo;

import java.util.List;

public class Role_Menu {
    private int roleid;

    private String rolename;

    private List<String> menuname;

    private List<String> menuurl;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<String> getMenuname() {
        return menuname;
    }

    public void setMenuname(List<String> menuname) {
        this.menuname = menuname;
    }

    public List<String> getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(List<String> menuurl) {
        this.menuurl = menuurl;
    }
}
