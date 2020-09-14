package club.yuit.socket.chat.server;

import club.yuit.socket.chat.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server {


    private ServerSocket serverSocket;

    private int port;

    private List<User> users;


    public Server(int port) throws IOException {
        this.port = port;
        this.users = new ArrayList<>();
        this.serverSocket = new ServerSocket(this.port);

    }

    public void run() throws IOException {


        while (true){
            //等待客户端的链接
            Socket socket = this.serverSocket.accept();
            socket.setKeepAlive(true);
            User user = new User(socket,null);
            new Thread(new MsgHandler(users,socket)).start();
        }


    }


}
