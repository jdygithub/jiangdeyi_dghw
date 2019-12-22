package com.zking.zkingedu.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class Employee implements  Serializable{
    private static final long serialVersionUID=6985644619420539431L;

    private Integer empid;

    private String empname;

    private String emppassword;

    private String empimage;

    private String createdate;

    private Set<Role> roles=new HashSet<>();

    private Integer state;

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname == null ? null : empname.trim();
    }

    public String getEmppassword() {
        return emppassword;
    }

    public void setEmppassword(String emppassword) {
        this.emppassword = emppassword == null ? null : emppassword.trim();
    }

    public String getEmpimage() {
        return empimage;
    }

    public void setEmpimage(String empimage) {
        this.empimage = empimage == null ? null : empimage.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


}