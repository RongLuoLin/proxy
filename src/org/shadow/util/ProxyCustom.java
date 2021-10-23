package org.shadow.util;

import com.shadow.proxy.UserDao;
import sun.misc.ProxyGenerator;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 */
public class ProxyCustom {

    /**
     * 代码
     *
     * io 把我们的代码写到了一个 .java文件中，然后在手动把这个 .java 文件编译
     * 编译完成之后，肯定会产生一个 .class 文件，继而把这个class 文件 loader 到 JVM 中
     * 然后通过反射去实例化这个对象，最终返回回去
     *
     */
    public static Object createProxy(Class infce,CInvocationHandler cInvocationHandler){
        //方法的字符串
        String methodStr = "";
        //换行 + 一个tab
        String rt = "\r\n";
        String tab = "\t";
        String r = "\n";
        //获取接口当中的所有方法，方便后面编译方法构建代理类的方法
        Method[] methods = infce.getMethods();

        //每个方法的返回类型
        String rtype="";

        for (Method m : methods) {
            //得到方法的返回类型
            rtype = m.getReturnType().getSimpleName();
            //参数的字符串 有可能有参，或者无参  ------(mname(int p0,String  p0,))
            String argsStr = "";
            String argsValueStr = "";
            //得到这个方法所有的参数的个数
            int parameterCount = m.getParameterCount();
            Class[] classParamArr = null;
            //再反射得到的目标方法的时候需要的参数个数和类型的字符串
            String getMethodParamStr = "new Class[]{";
            if(parameterCount > 0){
                classParamArr = new Class[parameterCount];
                //得到所有参数个数和类型
                Class<?>[] parameterTypes = m.getParameterTypes();
                int pc = 0;
                for ( Class<?> parameterType :parameterTypes) {

                    getMethodParamStr += parameterType.getSimpleName() + ".class,";
                    argsStr += parameterType.getSimpleName() + " p"  + pc + ",";
                    argsValueStr += "p" + pc +",";
                    pc++;
                }
                //截取一个逗号结尾
                getMethodParamStr = getMethodParamStr.substring(0,getMethodParamStr.length()-1);
                // int p0, String p1
                argsStr = argsStr.substring(0, argsStr.length()-1);
                //最终执行invoke方法的时候需要传入的参数值  po,p1
                argsValueStr = argsValueStr.substring(0, argsValueStr.length()-1);
            }
            getMethodParamStr += "}";

            boolean flag = false;

            String returnStr = "";
            String endReturnStr = "";
            if(!rtype.equals("void")){
                returnStr = "return ";
                endReturnStr = "return null;";
                flag = true;
            }

            String convertStr = "";
            if(flag){
                convertStr = " (" + rtype + ") ";
            }



            methodStr += tab + "@Override" + rt +
                    tab + "public " + rtype + " " + m.getName() + " ( " + argsStr + ") {" + rt +
                    tab + tab + "try {"+ rt +
                    tab + tab + tab + "Method md = " + infce.getSimpleName() + ".class.getMethod(\"" + m.getName() + "\"," + getMethodParamStr + ");" + rt +
                    tab + tab + tab + returnStr + convertStr + "h.invoke( this, md, new Object[]{" + argsValueStr +"});" + rt +
                    tab + tab + "} catch ( Exception e) {" + rt +
                    tab + tab + tab + " e.printStackTrace();" + rt +
                    tab + tab + "}" + rt +
                    tab + tab + endReturnStr +rt+
                    tab + "}"+rt;
        }



        String src =
                "package org.shadow.proxy;" + rt +
                        "import java.lang.reflect.Method;" + rt +
                        "import " + infce.getName()+";" + rt +
                        "import org.shadow.util.CInvocationHandler;"+ rt +
                        "public  class $Proxy1  implements " + infce.getSimpleName() + " {"  + rt +
                        "\tCInvocationHandler h;" + rt +
                        "\tpublic $Proxy1(CInvocationHandler h)   {" + rt +
                        "\t\tthis.h = h;" + rt +
                        "\t}"  + rt +
                        methodStr + rt +
                        "}";


       //把产生的源代码输出到 .java文件当中，通过IO

        File root = new File("d:/org/shadow/proxy/");
        if(!root.exists()){
            root.mkdirs();
        }
        String filePath = "d:/org/shadow/proxy/$Proxy1.java";
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fos = new FileWriter(file);
            fos.write(src);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //把java源文件编译成 class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> units = fileMgr.getJavaFileObjects(filePath);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        try {
            fileMgr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //从磁盘或者网络上面加载一个类文件到JVM
        URL[] urls = new URL[0];
        try {
            urls = new URL[] {new URL("file:/d:/")};
            URLClassLoader ul = new URLClassLoader(urls);
            Class<?> c = ul.loadClass("org.shadow.proxy.$Proxy1");
            Constructor<?> declaredConstructor = c.getDeclaredConstructor(CInvocationHandler.class);
            Object proxy = declaredConstructor.newInstance(cInvocationHandler);
            //返回 $Proxy1
            return proxy;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
