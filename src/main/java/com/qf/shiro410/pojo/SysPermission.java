package com.qf.shiro410.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysPermission implements Serializable {
    private Integer permissionId;//权限id
    private String perName;//权限名称
    private String menuName;//菜单名
    private String menuType;//级别
    private String menuUrl;//菜单url
    private String menuCode;//菜单编码
    private String parentCode;
    private String perDesc;
    private Integer ifVilid;
}
