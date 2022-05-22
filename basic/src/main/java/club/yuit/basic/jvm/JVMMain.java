package club.yuit.basic.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2022/3/31
 **/
public class JVMMain {


    public static void main(String[] args) {

            String s = new String("jav");
            //String s1 = new String("jav");


        //System.out.println(s.intern()==s);

        //new OutOfMemoryErrorTest().errorInHeap();

        Runtime run = Runtime.getRuntime();
        // 使用内存空间总数
        long total = run.totalMemory();
        // 空闲内存
        long free = run.freeMemory();
        // 最大可使用内存
        long max = run.maxMemory();

        System.out.printf("max:%d\ttotal:%d\tfree:%d%n",max,total,free);


    }


    static void stackOvError(){
        //stackOvError();

    }

}
