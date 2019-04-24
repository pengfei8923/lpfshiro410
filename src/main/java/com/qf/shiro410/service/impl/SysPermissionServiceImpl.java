package com.qf.shiro410.service.impl;

import com.qf.shiro410.mapper.SysPermissionMapper;
import com.qf.shiro410.pojo.SysPermission;
import com.qf.shiro410.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> queryPermissionByUserName(String username) {
        List<SysPermission> permissionList = sysPermissionMapper.findPermissionsByUsername(username);
        return permissionList;
    }
}
