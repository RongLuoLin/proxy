package org.shadow.zhujie;


import org.springframework.stereotype.Component;

/**
 * 自定义注解
 */


public class Member {


    private String name;

    @ZL
    public  void memberMethod(){
        System.out.println("xxx");
    }

}
