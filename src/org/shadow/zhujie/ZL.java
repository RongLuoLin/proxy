package org.shadow.zhujie;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rowling
 * @version 1.0
 *
 *
 * @Retention ：限定注解的生命周期
 *
 * RetentionPolicy.CLASS  class 文件中,运行时期也没有
 * RetentionPolicy.SOURCE java文件中可以看到，class文件中看不到
 * RetentionPolicy.RUNTIME 运行期间有用
 *
 *
 * @Target ： 限定注解出现的位置
 * CONSTRUCTOR  只能出现在构造方法上
 * TYPE         只能出现在类上
 * METHOD       只能出现在方法上
 * FIELD        只能出现在属性上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ZL {
    public String value() default "";

    public int age() default 2;
}
