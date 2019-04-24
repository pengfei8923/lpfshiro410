package com.qf.shiro410.mapper;

import com.qf.shiro410.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysPermissionMapper {

    public List<SysPermission> findPermissionsByUsername(String username);

}
