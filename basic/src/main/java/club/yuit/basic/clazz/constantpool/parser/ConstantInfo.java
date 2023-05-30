package club.yuit.basic.clazz.constantpool.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author yuit
 * @date 2022/5/23
 **/
public interface ConstantInfo {


    void handle(InputStream in) throws IOException;

    default void clear(byte[] bts){
        Arrays.fill(bts, (byte) 0);
    }

    void print();
     String getValue();
}
