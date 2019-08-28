package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 菜单表
 * @ClassName Menu
 * @Author likai
 **/
@Data
@Component
public class Menu implements Serializable {
    private static final long serialVersionUID = 5675833734353972899L;
    //菜单id
    private Integer menuID;
    //菜单名称
    private String menuName;
    //菜单父id
    private Integer menuFid;
    //菜单图标
    private String menuImg;
    //授权码(按钮)
    private String menuCode;
    //url
    private String menuURL;
    //序号
    private Integer menuRank;
    //类别0菜单1授权码（按钮)
    private Integer menuSort;
}
