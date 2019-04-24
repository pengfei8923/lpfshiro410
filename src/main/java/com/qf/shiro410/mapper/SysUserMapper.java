package com.qf.shiro410.mapper;

import com.qf.shiro410.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    /*根据用户名查询用户*/
    public SysUser findUserByloginName(String loginName);
}
