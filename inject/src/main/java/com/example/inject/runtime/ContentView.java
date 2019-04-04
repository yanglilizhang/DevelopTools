package com.example.inject.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author:wxq
 * email:805380422@qq.com
 * time:2019/04/04
 * desc:注入布局的
 * version:1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)  //TYPE:用于描述类、接口(包括注解类型) 或enum声明
public @interface ContentView {
    int value();
}
