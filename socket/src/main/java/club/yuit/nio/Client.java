package club.yuit.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author yuit
 * @date 2021/8/16
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
        channel.configureBlocking(true);

        if (channel.isConnected()){
            System.out.println("connect");
            ByteBuffer buffer = ByteBuffer.wrap("Hello NIO Socket".getBytes(StandardCharsets.UTF_8));
            channel.write(buffer);
            channel.close();
        }


    }
}
