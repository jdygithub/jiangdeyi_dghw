package com.zking.zkingedu.common.model;

import java.util.List;

public class Menu {
    private Integer menuid;

    private String menuname;

    private Integer menuparentid;

    private String menuimage;

    private String menucode;

    private String menuurl;

    private Integer menutype;

    private Integer menustate;

    private List<Menu> childlist;

    public List<Menu> getChildlist() {
        return childlist;
    }

    public void setChildlist(List<Menu> childlist) {
        this.childlist = childlist;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public Integer getMenuparentid() {
        return menuparentid;
    }

    public void setMenuparentid(Integer menuparentid) {
        this.menuparentid = menuparentid;
    }

    public String getMenuimage() {
        return menuimage;
    }

    public void setMenuimage(String menuimage) {
        this.menuimage = menuimage == null ? null : menuimage.trim();
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode == null ? null : menucode.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public Integer getMenutype() {
        return menutype;
    }

    public void setMenutype(Integer menutype) {
        this.menutype = menutype;
    }

    public Integer getMenustate() {
        return menustate;
    }

    public void setMenustate(Integer menustate) {
        this.menustate = menustate;
    }
}