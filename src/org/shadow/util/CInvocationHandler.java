package org.shadow.util;

import java.lang.reflect.Method;

/**
 *
 */
public interface CInvocationHandler {
    // Method md 执行目标方法  args 指目标方法的参数   proxy 作用：目前没有任何作用，如果有一天需要看代理对象是谁
    public Object invoke(Object proxy, Method md, Object... args);
}
