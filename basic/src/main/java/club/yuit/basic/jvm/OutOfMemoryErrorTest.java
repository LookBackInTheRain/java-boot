package club.yuit.basic.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2022/3/31
 **/
public class OutOfMemoryErrorTest {

    void errorInHeap(){
        List<Object> l = new ArrayList<>();
        while (true){
            //l.add(new OutOfMemoryErrorTest());
        }

    }

}
