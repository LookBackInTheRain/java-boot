package club.yuit.socket.chat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yuit
 * time 2018/10/5 11:37
 * description
 */
@Getter
@Setter
public abstract class Packet implements Serializable {
    // 一个字节的数据类型
    private byte msgType=Constant.MSG_USER_ONLINE;
    // 4个字节的数据长度
    private int length=0;

    public Packet(byte msgType, int length) {
        this.msgType = msgType;
        this.length = length;
    }

    public Packet() {
    }




}
