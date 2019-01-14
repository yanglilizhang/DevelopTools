package com.example.module_login.bean;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * author:wxq
 * email:805380422@qq.com
 * time:2019/01/08
 * desc:
 * version:1.0
 */
public class User extends BmobUser{

    public String phoneNumber;

    public String age;

    public String pic;

    /**
     * 性别
     */
    public Integer gender;


    /**
     * 用户当前位置
     */
    public BmobGeoPoint address;


    /**
     * 头像
     */
    public BmobFile avatar;


    /**
     * 别名
     */
    public List<String> alias;



}
