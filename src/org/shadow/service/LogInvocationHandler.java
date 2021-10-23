package org.shadow.service;

import org.shadow.util.CInvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */
public class LogInvocationHandler implements CInvocationHandler {

    Object target;
    public LogInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method md, Object... args) {
        System.out.println("start log --- proxy");
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
