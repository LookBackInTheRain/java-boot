package club.yuit.socket.chat.client;

import club.yuit.socket.chat.entity.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author yuit
 * time 2018/10/5 10:42
 * description
 */
public class Client {

    private String host;
    private int port;
    private User user;
    private OutputStream ot;
    private InputStream in;
    private TXTPacketHandler packetHandler;
    private Socket socket;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.user = new User();
        packetHandler = new TXTPacketHandler();
    }

    public void connect(String userName) throws IOException {
         socket = new Socket(this.host, this.port);
        socket.setKeepAlive(true);
        this.user.setSocket(socket);
        ot = socket.getOutputStream();
        in = socket.getInputStream();

        TXTPacket packet = new TXTPacket();
        packet.setMsgType(Constant.MSG_USER_ONLINE);
        packet.setData(userName);
        packetHandler.sendPacket(packet,ot);

    }


    public void send(TXTPacket packet) throws IOException {
       packetHandler.sendPacket(packet,ot);
    }


    public void receive() {
        new Thread(() -> {
            boolean flg = true;
            while (flg) {
                try {
                    TXTPacket txtPacket = TXTPacket.loadDataFromInputStream(in);
                    System.out.println(txtPacket.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                    flg = false;
                }
            }

        }).start();
    }


    public void close() throws IOException {
        in.close();
        ot.close();
        socket.close();
    }
}
