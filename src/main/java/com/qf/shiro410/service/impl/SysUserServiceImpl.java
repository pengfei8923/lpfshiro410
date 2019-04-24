package com.qf.shiro410.service.impl;

import com.qf.shiro410.mapper.SysUserMapper;
import com.qf.shiro410.pojo.SysUser;
import com.qf.shiro410.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser queryUserByUsername(String username) {
        SysUser user = sysUserMapper.findUserByloginName(username);
        return user;
    }
}
