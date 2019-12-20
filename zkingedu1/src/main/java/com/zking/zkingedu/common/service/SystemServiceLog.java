package com.zking.zkingedu.common.service;

import javax.persistence.Inheritance;
import java.lang.annotation.*;

/**
 * 自定义一个拦截controller的注解
 *
 * @target({METHOD,TYPE })表示这个注解可以用在类和接口上,还可以用在方法上
 * @Retention(RetentionPolicy.RUNTIME) 表示这是一个运行时的注解,即运行起来之后,才获取注解中的相关信息.
 *                                      而不像基本注解如@Override
 *                                      那种不用运行,在编译时 idea就可以进行相关工作的编译时注解
 * @Inheritance 表示可以被继承
 * @Documented  表示当执行javadoc时候, 本注解会生成相关文档
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inheritance
@Documented
public @interface SystemServiceLog {
    /**
     * 描述业务操作列如:XXX管理员执行xx操作
     * @return
     */
    String description() default "";
}
