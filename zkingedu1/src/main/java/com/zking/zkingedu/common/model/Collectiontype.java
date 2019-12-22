package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 藏品类别表
 */
@Component
public class Collectiontype implements Serializable {
    private static final long serialVersionUID = -4112338268061549658L;
    private Integer collectiontypeid;//藏品类别ID
    private String collectiontypename;//藏品类别名称 画/玉器/铜器
    private Integer collectionparenttypeid;//藏品父类别ID
    private List<Collectiontype> types;  //子类别

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCollectiontypeid() {
        return collectiontypeid;
    }

    public void setCollectiontypeid(Integer collectiontypeid) {
        this.collectiontypeid = collectiontypeid;
    }

    public String getCollectiontypename() {
        return collectiontypename;
    }

    public void setCollectiontypename(String collectiontypename) {
        this.collectiontypename = collectiontypename;
    }

    public Integer getCollectionparenttypeid() {
        return collectionparenttypeid;
    }

    public void setCollectionparenttypeid(Integer collectionparenttypeid) {
        this.collectionparenttypeid = collectionparenttypeid;
    }

    public List<Collectiontype> getTypes() {
        return types;
    }

    public void setTypes(List<Collectiontype> types) {
        this.types = types;
    }
}
