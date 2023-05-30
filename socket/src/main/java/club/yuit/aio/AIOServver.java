package club.yuit.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author yuit
 * @date 2022/6/28
 **/
public class AIOServver {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8889));
        /*serverChannel.accept(
                serverChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                    @Override
                    public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {

                    }

                    @Override
                    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {

                    }
                }
        );*/

        Future<AsynchronousSocketChannel> channelFuture = serverChannel.accept();

        channelFuture.get();



    }
}
