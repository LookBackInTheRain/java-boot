package club.yuit.socket.chat.server;

import club.yuit.socket.chat.entity.Constant;
import club.yuit.socket.chat.entity.TXTPacket;
import club.yuit.socket.chat.entity.TXTPacketHandler;
import club.yuit.socket.chat.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

@Slf4j
public class MsgHandler implements Runnable {

    private List<User> users;
    private User currentUser;
    private InputStream in;
    private  Socket socket;
    private TXTPacketHandler packetHandler;


    public MsgHandler(List<User> users, Socket socket) {
        this.users = users;
        this.socket = socket;
        init(socket);
        currentUser = new User();
        packetHandler = new TXTPacketHandler();
    }


    private void init(Socket socket)  {

        try {
            in =  socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {

        boolean flg = true;
        while (flg){
            try {
                TXTPacket packet = TXTPacket.loadDataFromInputStream(in);

                if (packet.getMsgType()== Constant.MSG_USER_ONLINE){
                    currentUser.setUsername(packet.getData());
                    packet.setData("["+currentUser.getUsername()+"] 上线了");
                    log.info("{}",packet.getData());
                    currentUser.setSocket(socket);
                    this.users.add(currentUser);
                }

                if (this.users.size() > 1) {
                    this.users.forEach(item -> {
                        // 消息不在转发给当前发送消息的用户
                        if (!item.getUsername().equals(currentUser.getUsername())) {
                            Socket socket = item.getSocket();
                            try {
                                OutputStream iut = socket.getOutputStream();
                                if (packet.getMsgType()==Constant.MSG_USER_ONLINE){
                                    packet.setData("[系统消息]:<"+currentUser.getUsername()+">上线了");
                                }else {
                                    packet.setData("["+currentUser.getUsername()+"]:"+packet.getData());
                                }
                                packetHandler.sendPacket(packet,iut);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
                flg=false;
            }
        }

    }
}
