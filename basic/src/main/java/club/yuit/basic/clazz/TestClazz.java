package club.yuit.basic.clazz;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author yuit
 * @date 2022/5/22
 **/

@Getter
@Setter
public class TestClazz<T> extends ArrayList<T> {
    private String tCount;
    public volatile String tName;
    protected String tgd;

    public Object object(){


        class MethodInnerClass {

        }

        return new MethodInnerClass();
    }


    protected void syncTest(int b){
        synchronized (this){
            if (b>0){
                System.out.println(b);
            }else {
                int v = b;
                v=90;
                this.tCount = String.valueOf(v);
            }
        }
    }

    public synchronized static void syncStaticMethod(int b){
        if (b>0){
            System.out.println(b);
        }else {
            int v = b;
            v=90;

        }
    }

    public synchronized void syncMethod(int b){
        synchronized (this){
            if (b>0){
                System.out.println(b);
            }else {
                int v = b;
                v=90;
                this.tCount = String.valueOf(v);
            }
        }
    }



    public static class InnerStaticClass{

    }


    public abstract class InnerClass {

    }

    public enum InnerEnum{

    }


}
