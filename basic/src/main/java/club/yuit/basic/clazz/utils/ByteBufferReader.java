package club.yuit.basic.clazz.utils;

import cn.hutool.core.util.ByteUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author yuit
 * @date 2023/6/6
 **/
public class ByteBufferReader {

    private final ByteBuffer buffer;

    public ByteBufferReader(ByteBuffer buffer) {
        this.buffer = buffer;
    }


    public byte readByte(){
        return buffer.get();
    }

    public short readU1(){
        byte[] tmp = new byte[]{0,buffer.get()};
       return ByteUtil.bytesToShort(tmp,ByteOrder.BIG_ENDIAN);
    }

    public short readShort(){
        return buffer.getShort();
    }

    public int readU2(){
        byte[] tmp = new byte[4];
        buffer.get(tmp,2,2);
        return ByteUtil.bytesToInt(tmp, ByteOrder.BIG_ENDIAN);
    }

    public int readInt(){
        return buffer.getInt();
    }


    public long readU4(){
        byte[] tmp = new byte[8];
        buffer.get(tmp,4,4);
        return ByteUtil.bytesToLong(tmp, ByteOrder.BIG_ENDIAN);
    }

    public long readLong(){
        return buffer.getLong();
    }

    public byte[] readBytes(int length){
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }

    public static ByteBufferReader build(ByteBuffer buffer){
        return new ByteBufferReader(buffer);
    }

    public ByteBuffer readBuffer(int length){
        byte[] bytes = this.readBytes(length);
        return ByteBuffer.wrap(bytes);
    }

}
