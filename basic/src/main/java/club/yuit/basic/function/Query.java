package club.yuit.basic.function;

import java.lang.reflect.Type;
import java.util.function.Function;

/**
 * @author yuit
 * @date 2022/4/2
 **/
public interface Query<T,R>  {

   T lamSelect(R ...c);


}





class LamQuery<T> implements Query<LamQuery<T>,FunctionObject<T,?>>{


    @Override
    public LamQuery<T> lamSelect(FunctionObject<T, ?>... c) {

        Class<?> clazz = c[0].getClass();

        Type[] tys = clazz.getGenericInterfaces();
        System.out.println();

        return this;
    }
}
