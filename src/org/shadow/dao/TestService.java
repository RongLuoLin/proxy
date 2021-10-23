package org.shadow.dao;

import org.shadow.service.Impl.UserServiceImpl;
import org.shadow.service.LogInvocationHandler;
import org.shadow.service.LogJdkInvocationHandler;
import org.shadow.service.UserService;
import org.shadow.util.CInvocationHandler;
import org.shadow.util.ProxyCustom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *
 */
public class TestService {
    public static void main(String[] args) {
//        CInvocationHandler cInvocationHandler = new LogInvocationHandler(new UserServiceImpl());
//        UserService userService = (UserService) ProxyCustom.createProxy(UserService.class, cInvocationHandler);
//       // userService.query(1,"son road");
//        userService.del();

        InvocationHandler invocationHandler = new LogJdkInvocationHandler(new UserServiceImpl());
        UserService userService = (UserService)Proxy.newProxyInstance(TestService.class.getClassLoader(), new Class[]{UserService.class}, invocationHandler);
        userService.del();

    }
}
