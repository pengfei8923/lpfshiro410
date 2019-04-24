package com.qf.shiro410.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysUser implements Serializable {
    private Integer userId;//用户id
    private String loginName;//登录名
    private String password;//
    private Integer state;//用户实名
    private Date createTime;
    private String realname;

//    private Integer userid;
//    private Date createtime;
//    private String realname;
}
