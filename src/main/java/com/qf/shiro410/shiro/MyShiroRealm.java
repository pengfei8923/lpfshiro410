package com.qf.shiro410.shiro;

import com.qf.shiro410.pojo.SysPermission;
import com.qf.shiro410.pojo.SysUser;
import com.qf.shiro410.service.SysPermissionService;
import com.qf.shiro410.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

/**
 * 自定义安全策略
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService permissionService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*获取主体信息*/
        Subject subject = SecurityUtils.getSubject();
        String principal = (String)subject.getPrincipal();//获取用户名
        /*根据用户查询权限清单*/
        List<SysPermission> sysPermissions = permissionService.queryPermissionByUserName(principal);
        /*获取用户权限字符串集合（去重）*/
        HashSet<String> authoriedSet = new HashSet<>();
        for (SysPermission perm: sysPermissions) {
            String perName = perm.getPerName();
            authoriedSet.add(perName);//添加权限名称
        }
        /*常见授权信息对象*/
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*给授权信息对象赋予权限*/
        authorizationInfo.setStringPermissions(authoriedSet);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户信息
        Object principal = authenticationToken.getPrincipal();
        String username = (String) principal;//用户名
        //根据用户名从数据库中查询用户对象（含密码）
        SysUser sysUser = sysUserService.queryUserByUsername(username);
        if(sysUser!=null){//如果对象不为空
            String credentials = sysUser.getPassword();//获取密码
            String realmName = this.getName();//获取本类名称
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,credentials,realmName);
            return authenticationInfo;
        } else {
            return null;
        }
    }
}
