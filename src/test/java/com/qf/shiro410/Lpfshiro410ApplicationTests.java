package com.qf.shiro410;

import com.qf.shiro410.mapper.SysPermissionMapper;
import com.qf.shiro410.mapper.SysUserMapper;
import com.qf.shiro410.pojo.SysPermission;
import com.qf.shiro410.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lpfshiro410ApplicationTests {

    @Autowired
    SysPermissionMapper permissionMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void contextLoads() {
        List<SysPermission> test2 = permissionMapper.findPermissionsByUsername("admin2");
        for (SysPermission perm: test2) {
            System.out.println(perm);
        }
    }

    @Test
    public void test02() {
        SysUser sysUser = sysUserMapper.findUserByloginName("test2");
        System.out.println(sysUser);
    }

}
