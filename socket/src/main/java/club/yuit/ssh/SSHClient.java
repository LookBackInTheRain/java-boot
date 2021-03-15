package club.yuit.ssh;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * date 2021-03-12 22:10
 **/
@Slf4j
public class SSHClient {

    public static final byte[] VERSION = "SSH-3.0-UitSSH-0.1\n".getBytes();
    public static final byte FLG_LF = 0xA;
    public static final int BUFFER_SIZE =  256;

    public static void main(String[] args) throws IOException {


        SocketChannel channel = SocketChannel.open(new InetSocketAddress("192.168.1.101", 22));
        channel.configureBlocking(true);

        if (channel.isConnected()) {
            checkVersion(channel);
            encrypt(channel);
        } else {
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
        ByteBuffer buffer = ByteBuffer.allocate(3500);
        int flg = channel.read(buffer);

        buffer.flip();
        // 判断在服务端发送版本号之前是否存在其他信息
        if (flg >= 3 && (buffer.get(0) != 'S' && buffer.get(1) != 'S' && buffer.get(2) != 'H' && buffer.get(3) != '-')) {

            if (buffer.get(buffer.limit() - 1) == FLG_LF) {
                byte[] tmp = new byte[buffer.limit() - 2];
                buffer.get(tmp);
                log.info(new String(tmp));
                buffer.clear();
            }
        }

        if (flg < 0) {
            channel.close();
            throw new IOException("Server closed connected");
        }


        byte[] tmp = new byte[buffer.limit() - 2];
        buffer.get(tmp);
        log.info("服务端版本号:{}", new String(tmp));
    }

    /**
     * 算法协商
     *
     * @param channel
     */
    static void encrypt(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        List<byte[]> data = new ArrayList<>();
        int flg = channel.read(buffer);
        // 切换到读模式
        while (flg<5){
            flg += channel.read(buffer);
        }

        buffer.flip();
        //byte flgByte = buffer.get(0);

        /*if (flg>='a' && flg<='Z'){
            handleVersionDiffer();
        }*/

        // 包长度
        //int packageLength = buffer.getInt();
        // 填充长度
        //byte paddingLength = buffer.get();

        // 最后一个字符
        byte lastByte = buffer.get(flg - 1);

        while (flg > 0) {

            if (flg==BUFFER_SIZE) {
                data.add(buffer.array());
                buffer.clear();
            } else {
                byte[] tmp = new byte[flg - 1];
                buffer.get(tmp);
                data.add(tmp);
                buffer.clear();
                break;
            }

            if (lastByte==FLG_LF) break;

            flg = channel.read(buffer);
            if (flg == 0) break;
            buffer.flip();
            lastByte = buffer.get(flg-1);
        }

        if (lastByte == FLG_LF) {
            log.info("{}", listBytesToString(data, StandardCharsets.UTF_8));
            return;
        }



        /*log.debug("buffer可读数据{}", buffer.remaining());

        //-- begin 读取 16 字节cookie
        while (buffer.remaining() < 16) {
            // 切换到写模式，清除已经读过的数据
            buffer.compact();
            channel.read(buffer);
        }

        byte[] cookieBytes = new byte[16];
        buffer.get(cookieBytes, 0, 16);
        log.info("server cookie (16位随机值):{}", cookieBytes);
        // -- end


        StringBuffer str = new StringBuffer();*/
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


        log.info("data:{}", new String(buffer.array()));


    }


    static  void handleVersionDiffer(){

    }

    static String listBytesToString(List<byte[]> listBytes, Charset charset) {

        if (listBytes.isEmpty()){
            return  null;
        }

        int len = 0;
        int size = listBytes.size();
        if (size == 1) {
            len = listBytes.get(0).length;
        }else {
            len = (size-1) * BUFFER_SIZE+ listBytes.get(size-1).length;
        }

        byte[] dataBytes = new byte[len];
        int destPos = 0;
        for (byte[] item : listBytes) {
            System.arraycopy(item,0,dataBytes,destPos,item.length);
            destPos = (item.length-1) + destPos;
        };

        return new String(dataBytes, charset);
    }

}
