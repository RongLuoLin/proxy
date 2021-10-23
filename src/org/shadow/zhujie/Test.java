package org.shadow.zhujie;

import java.lang.reflect.Method;

/**
 * @author rowling
 * @version 1.0
 */

public class Test {

    public static void main(String[] args) {

        Class<Member> clazz = Member.class;
        try {
            Method memberMethod = clazz.getDeclaredMethod("memberMethod");
            if (memberMethod.isAnnotationPresent(ZL.class)){
                ZL zl = memberMethod.getAnnotation(ZL.class);
                zl.value();
                System.out.println(zl.age());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if(clazz.isAnnotationPresent(ZL.class)){
            System.out.println("anned");
            ZL zl = clazz.getAnnotation(ZL.class);
            String value = zl.value();
            int age = zl.age();
            System.out.println(value + "," + age);
        }
//        Member member = new Member();
//        member.memberMethod();
    }
}
