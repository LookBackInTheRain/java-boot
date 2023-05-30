package club.yuit.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author yuit
 * @date 2021/8/16
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1", 8888));
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
        Scanner scanner = new Scanner(System.in);

        boolean flg =true;
        while (flg) {
            int count = selector.select();
            if (count < 1) {
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isWritable()) {


                   String str = scanner.next();
                    SocketChannel writeableChannel = (SocketChannel) key.channel();
                    writeableChannel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
                    int writeCount = writeableChannel.write(buffer);
                    while (writeCount==0){
                        writeCount = writeableChannel.write(buffer);
                    }
                    //key.cancel();
                   // scanner.close();
                    //writeableChannel.close();
                    //flg = false;
                    break;
                } else if (key.isConnectable()) {
                    SocketChannel conChannel = (SocketChannel) key.channel();
                    if (conChannel.isConnectionPending()) {
                        System.out.println("connected");
                        conChannel.finishConnect();
                    }

                    conChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel readableChannel = (SocketChannel) key.channel();
                    readableChannel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(64);
                    readableChannel.read(buffer);
                    System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));
                    readableChannel.register(selector, SelectionKey.OP_WRITE);
                }
            }
        }

        channel.close();


    }
}
