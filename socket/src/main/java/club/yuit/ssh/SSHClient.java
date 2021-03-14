package club.yuit.ssh;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * date 2021-03-12 22:10
 **/
@Slf4j
public class SSHClient {

    public static byte[] VERSION = "SSH-2.0-UitSSH-0.1\n".getBytes();

    public static void main(String[] args) throws IOException {


        SocketChannel channel = SocketChannel.open(new InetSocketAddress("192.168.1.101", 22));
        channel.configureBlocking(true);

        if (channel!=null){
            checkVersion(channel);
            encrypt(channel);
        }else {
            log.error("channel is null");
        }




    }

    /**
     * 版本号协商阶段
     *
     * @param channel
     * @throws IOException
     */
    static void checkVersion(SocketChannel channel) throws IOException {
        channel.write(ByteBuffer.wrap(VERSION));
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int flg = channel.read(buffer);

        while (true){
            flg = channel.read(buffer);
            if (flg<0) break;

            byte start = buffer.get();
            // 判断在服务端发送版本号之前是否存在其他信息
            if (start != 'S'&& buffer.get(1)!= 'S'&&buffer.get(2)!='H'&&buffer.get(3)!='-'){

                // 清除已读的数据
                buffer.compact();
            }
        }

        log.info("服务端版本号:{}",new String(buffer.array()));



    }

    /**
     * 算法协商
     * @param channel
     */
    static void encrypt(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1280);
        List<byte[]> data = new ArrayList<>();
        int flg = channel.read(buffer);
        // 切换到读模式
        buffer.flip();

        System.out.println(buffer.get());

        log.debug("buffer可读数据{}",  buffer.remaining());

        //-- begin 读取 16 字节cookie
        while (buffer.remaining()<16){
            // 切换到写模式，清除已经读过的数据
            buffer.compact();
            channel.read(buffer);
        }

        byte[] cookieBytes = new byte[16];
        buffer.get(cookieBytes,0,16);
        log.info("server cookie (16位随机值):{}",cookieBytes);
        // -- end


        StringBuffer str = new StringBuffer();
        /*int limit = buffer.limit();

        byte endPoint1 = 10;
        byte endPoint2 = 11;
        byte endPoint = 0;

        do {
            if (buffer.hasRemaining()){
                limit = buffer.limit();
                endPoint = buffer.get(limit-1);
                byte[] tmp = new byte[buffer.remaining()-buffer.position()];
                buffer.get(tmp);
                data.add(tmp);
            }
            buffer.compact();
            channel.read(buffer);
            buffer.flip();
        } while (endPoint!=endPoint1||endPoint!=endPoint2);*/


        log.info("data:{}",new String(buffer.array()));




    }

}
