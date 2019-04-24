package com.qf.shiro410.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    /**
     * 显示登录页
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 实现登录后——显示主页
     * @return
     */
    @RequestMapping("/dealLogin")
    public String dealLogin(String username,String password){
        try {
//        登录逻辑
            /*获取主体对象*/
            Subject subject = SecurityUtils.getSubject();
            /*构造令牌对象*/
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            /*是否登录，转交控制权给SecurityManager处理*/
            subject.login(token);
            if(subject.isAuthenticated()){
                return "main";
//                return "index";
            }
        } catch (UnknownAccountException e) {
            return "login";
        }catch (IncorrectCredentialsException e) {
            return "login";
        }catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "unauth";
    }
    /**
     * 主页
     * @return
     */
    @RequestMapping("/main")
    public String main(){
        return "main";
    }


    /**
     * 显示one
     * @return
     */
    /*注解过滤*/
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/one")
    public String one(){
        return "one";
    }

    /**
     * 显示two
     * @return
     */
    @RequiresPermissions(value = {"user_forbidden"})
    /*注解过滤角色*/
//    @RequiresRoles(value = {"admin"})
    @RequestMapping("/two")
    public String two(){
        return "two";
    }

    /**
     * 显示unauth
     * @return
     */
    @RequestMapping("/unauth")
    public String unauth(){
        return "unauth";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject!=null){
            subject.logout();//用户登出
        }
        return "login";
    }
}
