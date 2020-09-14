package club.yuit.socket.chat.client;


import club.yuit.socket.chat.entity.Constant;
import club.yuit.socket.chat.entity.TXTPacket;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, ParseException {
       /* Client client = new Client("127.0.0.1", 8000);
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
        client.close();*/

        List<Integer> l = Arrays.asList(1,1,2,1);

       Iterator it =  l.iterator();

       while (it.hasNext()){
           System.out.print(it.next());
       }


        System.out.println("aa");


    }

}





