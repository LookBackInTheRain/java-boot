package club.yuit.basic.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author yuit
 * @date 2022/4/2
 **/
@FunctionalInterface
public interface FunctionObject<T,R> extends Function<T,R>, Serializable {

}
