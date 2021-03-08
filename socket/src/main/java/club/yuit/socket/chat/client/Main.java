package club.yuit.socket.chat.client;


import club.yuit.socket.chat.entity.Constant;
import club.yuit.socket.chat.entity.TXTPacket;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, ParseException {
        Client client = new Client("172.16.2.36", 8000);
        client.connect(args[0]);


        String str = "";
        Scanner scanner = new Scanner(System.in);
        client.receive();

        while (!str.equals("done")) {
            str = scanner.next();
            TXTPacket packet = new TXTPacket();
            packet.setMsgType(Constant.MSG_DATA_TXT);
            packet.setData(str);
            client.send(packet);
        }
        scanner.close();
        client.close();



    }

}





