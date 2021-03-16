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

    public static final byte[] VERSION = "SSH-2.0-UitSSH-0.1\n".getBytes();
    public static final byte FLG_LF = 0xA;
    public static final int BUFFER_SIZE =  256;
    public static final byte[] NONE =  "none".getBytes();

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

        if(flg>=8){
            byte maxVersionNumber = buffer.get(4);
            byte minVersionNumber = buffer.get(6);
            if (maxVersionNumber!='2'&&minVersionNumber!='0'){
                log.info("客户端只支持SSH-2.0");
                channel.close();
                throw new IOException("Client support ssh-2.0");
            }
        }

        // 向服务端发送版本号
        channel.write(ByteBuffer.wrap(VERSION));

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
        // 包长度
        int packageLength = buffer.getInt();
        log.info("package length {}", packageLength);
        // 填充长度
        byte paddingLength = buffer.get();
        log.info("padding length {}", paddingLength);
        int payloadLength = packageLength - paddingLength -1;
        List<byte[]> payloadBytes = new ArrayList<>();
        byte[] randomPadding = new byte[paddingLength];
        buffer.compact();
        buffer.flip();
        byte[] tmp = new byte[buffer.remaining()];
        int payloadFlg = tmp.length;
        int paddingFlg = 0;
        buffer.get(tmp);
        payloadBytes.add(tmp);
        buffer.clear();

        // 读取payload中的数据
        while (payloadFlg<payloadLength) {
            int readLen= channel.read(buffer);
            payloadFlg += readLen;
            if (readLen==0){
                continue;
            }

            buffer.flip();
            if (payloadFlg<=payloadLength){
                tmp = new  byte[readLen];
                buffer.get(tmp);
                payloadBytes.add(tmp);
                buffer.compact();
            }else {
                int tmpLen = payloadFlg-payloadLength;
                tmp = new byte[tmpLen];
                buffer.get(tmp);
                payloadBytes.add(tmp);
                buffer.compact();
                buffer.flip();
                // 读取randomPadding
                buffer.get(randomPadding);
                paddingFlg = buffer.remaining();
                buffer.clear();
            }
        }

        // 读取randomPadding
        while (paddingFlg < paddingLength){
            int readLen = channel.read(buffer);
            paddingFlg += readLen;
            if (readLen==0) {
                continue;
            }
            buffer.flip();
            if (paddingFlg <= packageLength){
                tmp = new  byte[readLen];
                buffer.get(tmp);
                System.arraycopy(tmp,0,randomPadding,(paddingFlg-readLen), readLen);
            }else {
                int  tmpLen = paddingLength - paddingFlg;
                tmp = new byte[tmpLen];
                buffer.get(tmp);
                System.arraycopy(tmp,0,randomPadding,(paddingFlg-readLen),tmpLen);
            }
            buffer.compact();
        }

        buffer.flip();
        byte[]  mac = new byte[NONE.length];

        int macFlg = 0;
        if (buffer.remaining()>0){
            macFlg = buffer.remaining();
            tmp = new byte[macFlg];
            buffer.get(tmp);
            System.arraycopy(tmp,0,mac,0, macFlg);
            buffer.clear();
        }

        while (macFlg<NONE.length){
            int readLen = channel.read(buffer);
            if (readLen==0){
                break;
            }
            macFlg +=readLen;
            buffer.flip();
            if (macFlg <= NONE.length){
                tmp = new byte[readLen];
                buffer.get(tmp);
                System.arraycopy(tmp,0,mac,macFlg,readLen);
            }else {
                int tmpLen =  macFlg-NONE.length;
                tmp = new byte[tmpLen];
                buffer.get(tmp);
                System.arraycopy(tmp,0,mac,(macFlg-readLen),tmpLen);
            }

            buffer.compact();
        }



        log.info("payload:{}", listBytesToString(payloadBytes,StandardCharsets.UTF_8));
        log.info("random padding: {}", new String(randomPadding));
        log.info("MAC: {}", new String(mac));

    }


    static  void handleVersionDiffer(){

    }

    /**
     * 字节转化为String
     * @param listBytes
     * @param charset
     * @return
     */
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
