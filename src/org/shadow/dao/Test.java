package org.shadow.dao;

import org.shadow.util.CInvocationHandler;
import org.shadow.util.CInvocationHandlerImpl;
import org.shadow.util.ProxyCustom;

import javax.naming.Name;
import java.lang.reflect.Proxy;


/**
 * 静态代理的缺点：
 * 1. 会产生很多的代理类
 * 2. 产生的代理类只能代理既定的接口
 */
public class Test {

    public static void main(String[] args) {
        //静态代理
//        MDao mDao = new MDaoTime(new MemberDao());
//        mDao.query(1,"张三凶徒");

//        MDaoSubLog mDaoSubLog = new MDaoSubLog();
//        mDaoSubLog.query(2,"李四");

        //用JDK动态代理
        MDao mDao =  new MemberDao();

        /**动态：
         * newProxyInstance
         * 1. 他会产生一个字符串  代理类的源码
         * 2. 把这个字符串输出到一个 .java ($Proxy.java)文件中
         * 3. 会把这个$Proxy.java 动态编译他成为一个$Proxy.class
         * 4. 会通过一个类加载器把这个 $Proxy.class 加载到 JVM 当中
         * 5. Class.forName("XXX").newInstance 反射实例化这个对象 proxyObject
         *
         * 6.----直接在内存中产生了字节码
         */
        CInvocationHandler cInvocationHandler = new CInvocationHandlerImpl(mDao);
        MDao cdao = (MDao) ProxyCustom.createProxy(MDao.class,cInvocationHandler);
        System.out.println(cdao.query(1,"zlzlzl"));
//        MDao dao = (MDao) Proxy.newProxyInstance(Test.class.getClassLoader(),
//                        new Class[]{MDao.class},
//                        new CustomInvocationHandler(mDao));
//
//        System.out.println(dao.query(1,"aaaa"));

       // System.out.println(mDao.query(1,"son road"));

    }
}
