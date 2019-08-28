package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 藏品表
 */
@Data
@Component
public class Collections implements Serializable {

    private static final long serialVersionUID = 6525922498497713937L;
    private Integer collectionid;//藏品ID
    private String collectionno;//藏品编号
    private String collectionname;//藏品名字
    private String collectionyear;//藏品年代
    private String collectionspeuear;//藏品入藏范围 1949-1965,1965-2001,2001到至今
    private Integer collectionlevel;//藏品级别 一,二,三级,一般,未定级
    private Integer collectiondisability;//藏品完残程度  完整,基本,残缺,重残
    private Integer collectionpreservation;//藏品保存状态 不需修复,需要,急需,已修复
    private String collectiondiameter;//藏品底径
    private String collectionbore;//藏品口径
    private String collectionhigh;//藏品通高
    private String collectionsize;//藏品具体尺寸
    private String collectionoldname;//藏品原名
    private String collectionsource;//藏品来源
    private String collectionremark;//藏品基本情况描述
    private Integer collectioncount;//藏品数量 单件/套,多件
    private String collectionmass;//藏品质量
    private String collectioninyear;//入藏年度
    private String collectionjtyear;//具体年份
    private String createdtime;//创建时间
    private String modifiedtime;//创建用户
    private Integer empid;//创建ID
    private Integer collectionstatus;//藏品状态 0 下架  1在架
    private String collectionaddresscode;//存放地址编码 架位名称
}
