package fun.yizhierha.common.annotation.rest;


import java.lang.annotation.*;

/**
 * 标记匿名访问方法
 */
@Inherited //子类也可以继承这个注解
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
