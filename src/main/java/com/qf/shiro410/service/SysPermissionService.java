package com.qf.shiro410.service;

import com.qf.shiro410.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService {
    public List<SysPermission> queryPermissionByUserName(String username);
}
