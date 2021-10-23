package org.shadow.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */
@SuppressWarnings({"all"})
public class LogJdkInvocationHandler implements InvocationHandler {
    Object target;
    public LogJdkInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method md, Object... args) {
        System.out.println("jdk log --- proxy");
        Object invoke = null;
        try {
            invoke = md.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }
}
