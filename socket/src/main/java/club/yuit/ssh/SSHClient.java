package club.yuit.ssh;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author yuit
 * date 2021-03-12 22:10
 **/
@Slf4j
public class SSHClient {

    public static final byte[] VERSION = "SSH-2.0-UitSSH-0.1\n".getBytes();
    public static final byte FLG_LF = 0xA;
    public static  final int FLG_EXIT_CODE = -999;
    public static final int BUFFER_SIZE = 256;
    public static final byte[] NONE = "none".getBytes();

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

        if (flg >= 8) {
            byte maxVersionNumber = buffer.get(4);
            byte minVersionNumber = buffer.get(6);
            if (maxVersionNumber != '2' && minVersionNumber != '0') {
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
        while (flg < 6) {
            flg += channel.read(buffer);
        }

        buffer.flip();
        // 包长度
        int packageLength = buffer.getInt();
        log.info("package length {}", packageLength);
        // 填充长度
        byte paddingLength = buffer.get();
        log.info("padding length {}", paddingLength);
        int payloadLength = packageLength - paddingLength - 1;
        /**
         * 消息码
         * {@link club.yuit.ssh.consts.MessageNumber}
         */
        byte messageCode = buffer.get();
        log.info("Message Code: {}", messageCode);
        buffer.compact();
        buffer.flip();
        /**
         * 16 位随机cookie值
         */
        byte[] cookie = new byte[16];

        while (buffer.remaining() < 16) {
            buffer.compact();
            channel.read(buffer);
            buffer.flip();
        }

        buffer.get(cookie);


        log.info("cookie: {}",  bytesToHex(cookie));
        int payloadFlg = cookie.length;


        // 交换算法列表长度
        buffer.compact();
        buffer.flip();
        int kexAlgorithmsLen = readInt(channel, buffer);
        log.info("Kex Algorithms length: {}", kexAlgorithmsLen);
        List<byte[]> kexAlgorithmBytes = readListBytes(channel, buffer, kexAlgorithmsLen);
        log.info("Kex Algorithms:{}", listBytesToString(kexAlgorithmBytes, StandardCharsets.UTF_8));

        // server_host_key_algorithms
        int serHKAlgorithmsLen = readInt(channel, buffer);
        List<byte[]> serHKAlgorithmBytes = readListBytes(channel, buffer, serHKAlgorithmsLen);
        log.info("server_host_key_algorithms: {}", listBytesToString(serHKAlgorithmBytes, StandardCharsets.UTF_8));


        // encryption_algorithms_client_to_server
        int encryptionAlgorithmsCSLen = readInt(channel, buffer);
        List<byte[]> encryptionAlgorithmCSBytes = readListBytes(channel, buffer, encryptionAlgorithmsCSLen);
        log.info("encryption_algorithms_client_to_server:{}", listBytesToString(encryptionAlgorithmCSBytes, StandardCharsets.UTF_8));


        // encryption_algorithms_server_to_client
        int encryptionAlgorithmsSCLen = readInt(channel, buffer);
        List<byte[]> encryptionAlgorithmSCBytes = readListBytes(channel, buffer, encryptionAlgorithmsSCLen);
        log.info("encryption_algorithms_server_to_client: {}", listBytesToString(encryptionAlgorithmSCBytes, StandardCharsets.UTF_8));


        // mac_algorithms_client_to_server
        int macAlgorithmsCSLen = readInt(channel, buffer);
        List<byte[]> macAlgorithmsCSBytes = readListBytes(channel, buffer, macAlgorithmsCSLen);
        log.info("mac_algorithms_client_to_server: {}", listBytesToString(macAlgorithmsCSBytes, StandardCharsets.UTF_8));


        // mac_algorithms_server_to_client
        int macAlgorithmsSCLen = readInt(channel, buffer);
        List<byte[]> macAlgorithmsSCBytes = readListBytes(channel, buffer, macAlgorithmsSCLen);
        log.info("mac_algorithms_server_to_client: {}", listBytesToString(macAlgorithmsSCBytes, StandardCharsets.UTF_8));

        // compression_algorithms_client_to_server
        int compressionAlgorithmsCSLen = readInt(channel, buffer);
        List<byte[]> compressionAlgorithmsCSBytes = readListBytes(channel, buffer, compressionAlgorithmsCSLen);
        log.info("compression_algorithms_client_to_server: {}", listBytesToString(compressionAlgorithmsCSBytes, StandardCharsets.UTF_8));

        // compression_algorithms_server_to_client
        int compressionAlgorithmsSCLen = readInt(channel, buffer);
        List<byte[]> compressionAlgorithmsSCBytes = readListBytes(channel, buffer, compressionAlgorithmsSCLen);
        log.info("compression_algorithms_server_to_client: {}", listBytesToString(compressionAlgorithmsSCBytes, StandardCharsets.UTF_8));

        // languages_client_to_server
        int langCSLen = readInt(channel, buffer);
        if (langCSLen > 0) {
            byte[] langCSBytes = readBytes(channel, buffer, langCSLen);
            log.info("languages_client_to_server: {}", new String(langCSBytes, StandardCharsets.UTF_8));
        }else {
            log.info("languages_client_to_server:");
            buffer.flip();
        }

        // languages_server_to_client
        int langSCLen = readInt(channel, buffer);
        if (langSCLen > 0) {
            byte[] langCSBytes = readBytes(channel, buffer, langSCLen);
            log.info("languages_server_to_client: {}", new String(langCSBytes, StandardCharsets.UTF_8));
        }else {
            log.info("languages_server_to_client: ");
        }


        byte firstKexPacketFollows = readBytes(channel, buffer, 1)[0];
        log.info("First KEX Packet Follows: {}", firstKexPacketFollows);
        buffer.compact();
        byte[] reserved = readBytes(channel, buffer,4);
        log.info("reserved: {}", reserved);

        buffer.compact();
        byte[] padding = readBytes(channel, buffer, paddingLength);
        buffer.clear();
        log.info("padding String: {}", new String(padding, StandardCharsets.UTF_8));

    }



    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }




    static List<byte[]> readListBytes(SocketChannel channel, ByteBuffer buffer, int readLen) throws IOException {


        List<byte[]> dataBytes = new ArrayList<>();
        int flg = 0;
        byte[] tmp = null;
        if (readLen == 0) {
            return null;
        }

        buffer.flip();
        while (flg<readLen) {
            int remaining = buffer.remaining();
            flg += remaining;
            if (remaining > 0 && flg <= readLen) {
                tmp = new byte[remaining];
                buffer.get(tmp);
                dataBytes.add(tmp);
                buffer.compact();
                buffer.flip();
                continue;
            } else if (remaining > 0) {
                flg -= remaining;
                tmp = new byte[readLen - flg];
                buffer.get(tmp);
                dataBytes.add(tmp);
                buffer.compact();
                buffer.flip();
                break;
            }

            buffer.compact();
            channel.read(buffer);
            buffer.flip();
        }

        return dataBytes;
    }

    static byte[] readBytes(SocketChannel channel, ByteBuffer buffer, int readLen) throws IOException {
        byte[] dataBytes = new byte[readLen];
        int flg = 0;
        byte[] tmp = null;
        buffer.flip();

        while (flg<readLen) {
            int remaining = buffer.remaining();
            flg += remaining;
            if (remaining > 0 && flg <= readLen) {
                tmp = new byte[remaining];
                buffer.get(tmp);
                System.arraycopy(tmp, 0, dataBytes, (flg-remaining), tmp.length);
                buffer.compact();
                buffer.flip();
                continue;
            } else if (remaining > 0) {
                flg -= remaining;
                tmp = new byte[readLen - flg];
                buffer.get(tmp);
                System.arraycopy(tmp, 0, dataBytes, flg, readLen - flg);
                buffer.compact();
                buffer.flip();
                break;
            }


            buffer.compact();
            channel.read(buffer);
            buffer.flip();
        }

        return dataBytes;
    }

    static int readInt(SocketChannel channel, ByteBuffer buffer) throws IOException {
        while (buffer.remaining() < 4) {
            buffer.compact();
            channel.read(buffer);
            buffer.flip();
        }
        int data = buffer.getInt();
        buffer.compact();

        return data;
    }


    static void handleVersionDiffer() {

    }

    /**
     * 字节转化为String
     *
     * @param listBytes
     * @param charset
     * @return
     */
    static String listBytesToString(List<byte[]> listBytes, Charset charset) {

        if (listBytes.isEmpty()) {
            return null;
        }

        int len = 0;
        int size = listBytes.size();
        if (size == 1) {
            len = listBytes.get(0).length;
        } else {
            len = (size - 1) * BUFFER_SIZE + listBytes.get(size - 1).length;
        }

        byte[] dataBytes = new byte[len];
        int destPos = 0;
        for (byte[] item : listBytes) {
            System.arraycopy(item, 0, dataBytes, destPos, item.length);
            destPos = (item.length - 1) + destPos;
        }
        ;

        return new String(dataBytes, charset);
    }

}
