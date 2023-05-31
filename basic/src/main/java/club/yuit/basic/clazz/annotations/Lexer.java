package club.yuit.basic.clazz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuit
 * @date 2023/5/31
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lexer {

    /**
     * 去读的字节数
     * @return
     */
    int length();

}
