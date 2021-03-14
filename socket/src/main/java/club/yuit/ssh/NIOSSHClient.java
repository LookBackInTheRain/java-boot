package club.yuit.ssh;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yuit
 * date 2021-03-13 19:19
 **/
@Slf4j
public class NIOSSHClient {

    public static byte[] VERSION = "SSH-2.0-UitSSH-0.1\n".getBytes();

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.101",22);
        InputStream in = socket.getInputStream();
        OutputStream ot = socket.getOutputStream();
        checkVersion(in,ot);
        encryptProtocol(in,ot);
    }

    static void checkVersion(InputStream in, OutputStream ot) throws IOException {
        byte[] serverVersion = new byte[256];
        int f = in.read(serverVersion);
        log.info("server version: {}", new String(serverVersion));

        ot.write(VERSION);
        ot.flush();
    }

    static void encryptProtocol(InputStream in, OutputStream ot) throws IOException {
        byte[] buf = new byte[256];
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int readAble = in.available();
        int flg = 0;
        if (readAble>10){
            flg = in.read(buf);
            buffer.put(buf,0,flg);
        }

        while (readAble-flg>0){
            readAble = in.available();

        }


    }

}


