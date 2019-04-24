package com.qf.shiro410.config;

import com.qf.shiro410.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro配置对象
 */
@Configuration/*标识本类为配置类*/
public class ShiroConfig {
    /**
     * 创建并权限过滤器
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
       /*创建过滤器工厂对象*/
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
       /*给过滤器配置安全管理器*/
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*设置登录页*/
        filterFactoryBean.setLoginUrl("/login");
        /*设置登陆成功显示页*/
        filterFactoryBean.setSuccessUrl("/main");
        /*设置失败显示页——权限不足跳转*/
        filterFactoryBean.setUnauthorizedUrl("/unauth");
        /*定义过滤权限列表*/
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/login","anon");//匿名访问资源
        map.put("/dealLogin","anon");//匿名访问资源
        map.put("/main","authc");//必须登录后方可访问的路径
//        map.put("/one","perms[user_edit]");//登录后，user_edit权限访问
//        map.put("/two","perms[user_forbidden]");//登录后，user_forbidden权限访问
        map.put("/*","authc");//其他访问需登录，通常置于末尾
        /*设置过滤权限*/
        filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }


    /**
     * 创建并配置shiro安全管理器
     * @param myShiroRealm
     * @return
     */
    @Bean("defaultWebSecurityManager")/*通过名称注入*/
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm){/*通过名称注入*/
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
        /*给安全管理器配置自定义安全策略*/
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定解密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }

    /**
     * 创建自定义的安全策略对象，并交给spring容器管理
     * @return
     */
    @Bean("myShiroRealm")/*通过名称注入*/
    public MyShiroRealm myShiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher credentialsMatcher){
        /*自定义安全策略*/
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        /*关闭认证缓存*/
        myShiroRealm.setAuthenticationCachingEnabled(false);
        /*设定凭证匹配*/
        myShiroRealm.setCredentialsMatcher(credentialsMatcher);
        return myShiroRealm;
    }


    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /*设置动态代理为cglib，默认是jdk代理*/
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
