package com.zking.zkingedu.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志记录表
 */
@Data
@Component
public class Loginfo implements Serializable {

    private static final long serialVersionUID = -3557983717050588807L;
    private Integer logid;//日志ID
    private Integer empid;//用户ID

    private String operationtext;            //操作详情
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operationdate;            //操作时间
    private String title;                   //日志标题
    private String remoteAddr;             //请求地址
    private String requestUri;            //URI
    private String method;               //请求方式
    private String params;              //请求参数

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getOperationtext() {
        return operationtext;
    }

    public void setOperationtext(String operationtext) {
        this.operationtext = operationtext;
    }

    public Date getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
