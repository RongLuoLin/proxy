package com.shadow.proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Properties;

/**
 *
 */
public class Test {

    public static void main(String[] args) throws IOException {


//        URL resource = Test.class.getClassLoader().getResource("/");



        //bts 就是我们 JDK 产生的字节码 --class 文件
        byte[] bts = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{UserDao.class});

        File file = new File("resource\\", "$Proxy0.class");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bts);
        fos.flush();
        fos.close();

    }

}
