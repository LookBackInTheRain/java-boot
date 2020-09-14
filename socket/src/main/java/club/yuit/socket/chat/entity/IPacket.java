package club.yuit.socket.chat.entity;

import java.io.InputStream;

/**
 * @author yuit
 * @date 2020/5/14 下午2:07
 */
public interface IPacket {
     Packet parsePacket(InputStream in) throws Exception;
     byte[] readData(InputStream in,int len) throws Exception;
}
