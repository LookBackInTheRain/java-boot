import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author yuit
 * date 2021-03-13 20:58
 **/
@Slf4j
public class TestMain {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte)1);
        buffer.put((byte)2);
        buffer.put((byte)3);
        buffer.put((byte)5);


        System.out.println(buffer.remaining());
        buffer.flip();
        buffer.get();
        int limit = buffer.limit();
        byte[] ts = new byte[2];
        System.out.println(buffer.remaining());
        System.out.println(buffer.get(limit-1));
        buffer.get(ts);
        log.info("{}",ts);
        System.out.println();
        System.out.println(buffer.remaining());
        System.out.println(buffer.get(3));

        System.out.println();


    }
}
