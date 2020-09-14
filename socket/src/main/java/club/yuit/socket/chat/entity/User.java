package club.yuit.socket.chat.entity;

import java.net.Socket;

/**
 * @author yuit
 */
public class User  {
   private Socket socket;
   private String username;
   public User(Socket socket, String username) {
      this.socket = socket;
      this.username = username;
   }

   public User() {
   }



   public Socket getSocket() {
      return socket;
   }

   public void setSocket(Socket socket) {
      this.socket = socket;
   }
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
}
