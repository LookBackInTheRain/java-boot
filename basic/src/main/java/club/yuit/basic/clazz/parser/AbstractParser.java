package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.Struct;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/**
 * @author yuit
 * @date 2023/5/31
 **/
public abstract class AbstractParser implements Parser {




    private Struct struct;

    public AbstractParser( Struct struct) {
        this.struct = struct;
    }

    public abstract void doParser(Struct struct);


    @Override
    public int readByte() {
        try {
            if (getStream().available()<1){
                throw new RuntimeException();
            }
            return this.getStream().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public short readShort() {
        try {
            if (getStream().available()<2){
                throw new RuntimeException();
            }
            byte[] b = this.getStream().readNBytes(2);
            return ByteUtil.bytesToShort(b,ByteOrder.BIG_ENDIAN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readUnsignedShort() {
        try {
            byte[] tmp = new byte[4];
            if (getStream().available()<2){
                throw new RuntimeException();
            }
            int read = 0;
            while (read!=2){
                read+=getStream().read(tmp,2,2);
            }
            return ByteUtil.bytesToInt(tmp,ByteOrder.BIG_ENDIAN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readInt() {
        try {
            if (getStream().available()<4){
                throw new RuntimeException();
            }
            byte[] tmp =  this.getStream().readNBytes(4);
            return ByteUtil.bytesToInt(tmp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public byte[] readBytes(int length) {
        try {
            return this.getStream().readNBytes(length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public InputStream getStream() {
        return this.struct.getStream();
    }

    public Struct getStruct() {
        return struct;
    }
}
