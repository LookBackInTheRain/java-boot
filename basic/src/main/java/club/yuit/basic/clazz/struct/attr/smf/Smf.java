package club.yuit.basic.clazz.struct.attr.smf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Smf {

    int value()   default -1;

    int[] section();

    String desc() default "";

}
