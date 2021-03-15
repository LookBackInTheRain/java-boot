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

        buffer.flip();
        buffer.get(3);

        byte[] bytes = new  byte[buffer.limit()];
        buffer.get(bytes);
        log.info("{}",bytes);
        log.info("{}", Integer.MAX_VALUE);


    }
}
