package club.yuit.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yuit
 * @date 2021/8/16
 **/
public class Server {

    private boolean flg = true;

    public static void main(String[] args) throws IOException {

        new Server().run();


    }


    private void run() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8888));
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();
        Selector readSelector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        MessageHandler handler = new MessageHandler(readSelector);

        new Thread(handler).start();


        while (true) {
            int selectCount = selector.select();
            if (selectCount < 0) {
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel channel = serverChannel.accept();
                    if (channel == null) {
                        key.cancel();
                        continue;
                    }
                    channel.configureBlocking(false);
                    SocketAddress address = channel.getRemoteAddress();
                    System.out.println("Client：" + address.toString());
                    ByteBuffer buffer = ByteBuffer.wrap("Hello Client.".getBytes(StandardCharsets.UTF_8));
                    channel.write(buffer);
                    channel.register(readSelector, SelectionKey.OP_READ);
                    //MessageHandler messageHandler = new MessageHandler(readSelector);
                    //service.submit(messageHandler);
                }
            }


        }
    }

}

class MessageHandler implements Runnable {

    private final Selector selector;
    private boolean flg = true;
    private boolean  begin = false;

    public MessageHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        System.out.println("read thread init");
        while (flg) {
            try {
                int selectCount = selector.select();
                if (selectCount < 1) {
                    continue;
                }

                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isReadable() && key.isValid()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        try {
                            ByteBuffer buffer = ByteBuffer.allocate(64);
                            //Thread.sleep(3000);
                            int count = channel.read(buffer);
                            if (count == 0) {
                                if (buffer.remaining() == 0) {
                                    do {
                                        printAndClear(buffer, count);
                                        count = channel.read(buffer);
                                    } while (count == 0 && buffer.remaining() == 0);
                                }
                                printAndClear(buffer, count);
                            } else if (count == -1) {
                                System.out.println("客户端断开链接");
                                key.cancel();
                                channel.close();
                                continue;
                            }

                            printAndClear(buffer, count);

                        } catch (Exception e) {
                            key.cancel();
                            channel.close();
                        }


                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void printAndClear(ByteBuffer buffer, int count) {
        buffer.flip();
        byte[] b = new byte[count];
        buffer.get(b);
        System.out.print(new String(b, StandardCharsets.UTF_8));
        buffer.clear();
    }
}
