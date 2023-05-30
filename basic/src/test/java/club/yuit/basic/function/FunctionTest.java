package club.yuit.basic.function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author yuit
 * @date 2020/5/26 下午3:20
 */
@RunWith(JUnit4.class)
public class FunctionTest {

    @Test
    public void test(){

        Uls<Integer> l = new Uls<>();

        for (Integer i: l ) {

            System.out.println(i);

        }

    }


    @Test
    public void test01(){
        int[] i = new int[10];

        for (int l:i){
            System.out.println(l);
        }

    }

}


class  Uls<T> implements Iterable<T> {

    Object[] ts = new Object[10];
    int size = 10;
    int pos = 0 ;

    public Uls() {
        ts[0] = 1;
    }

    private T getElement(Object[] ds, int index){
        return (T)ds[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    class Itr implements Iterator<T>{
        @Override
        public boolean hasNext() {
            return size>0;
        }

        @Override
        public T next() {
            T t =  (T)ts[pos];
            pos ++;
            return  t;
        }

        @Override
        public void remove() {
            final int newSize;
            if ((newSize = size - 1) > pos)
                System.arraycopy(ts, pos + 1, ts, pos, newSize - pos);
            ts[size = newSize] = null;

            pos --;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            for (int i=0;i<size;i++){
                action.accept(getElement(ts,i));
            }
        }
    }
}