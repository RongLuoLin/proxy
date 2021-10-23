package org.shadow.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */
public class CInvocationHandlerImpl implements CInvocationHandler {
    Object target;

    public CInvocationHandlerImpl(Object target){
        this.target = target;
    }

    //执行目标方法的执行
    //代理逻辑
    @Override
    public Object invoke(Object proxy, Method md, Object... args) {
        Object invoke = null;
        System.out.println("custom proxy before");
        try {
            invoke = md.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("custom proxy after");
        return invoke;
    }
}
