package org.shadow.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 */
public class CustomInvocationHandler implements InvocationHandler {

    //目标对象
    Object object;
    public CustomInvocationHandler(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理的 before 业务逻辑
        System.out.println("before------xxxxxxxxx-----JDKProxy");
        //执行目标对象的方法
        Object invoke = method.invoke(object, args);
        //代理的 after 业务逻辑
        System.out.println("after------xxxxxxxxx-----JDKProxy");
        return invoke;
    }
}
