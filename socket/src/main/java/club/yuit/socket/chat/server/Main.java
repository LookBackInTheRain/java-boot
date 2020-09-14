package club.yuit.socket.chat.server;

import java.io.IOException;

/**
 * @author yuit
 * time 2018/10/5 11:07
 * description
 */
public class Main {

    public static void main(String[] args) throws IOException {

        new Server(8000).run();
        

    }


}
