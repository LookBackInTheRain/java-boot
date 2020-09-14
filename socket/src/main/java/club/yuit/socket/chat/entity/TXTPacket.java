package club.yuit.socket.chat.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author yuit
 * @date 2020/5/13 下午6:13
 */
@Getter
@Setter
@Slf4j
public class TXTPacket extends Packet {
    private String data;

    public TXTPacket(byte msgType, int length, String data) {
        super(msgType, length);
        this.data = data;
    }

    public TXTPacket() {
    }

    public static TXTPacket loadDataFromInputStream(InputStream in) throws Exception {
        return new TXTPacketHandler().parsePacket(in);
    }




}
