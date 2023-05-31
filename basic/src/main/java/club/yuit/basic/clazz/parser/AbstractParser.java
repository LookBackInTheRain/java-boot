package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import cn.hutool.core.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 * @author yuit
 * @date 2023/5/31
 **/
public abstract class AbstractParser implements Parser {

    final int length;
    ByteBuffer buffer;


    public AbstractParser(FileChannel channel) {
        length = getLexer().length();
        if (length>0){
            buffer = ByteBuffer.allocate(length);
        }

        read(channel);
    }

    @Override
    public ByteBuffer read(FileChannel in) {
        try {

            if (buffer==null){

            }

            int read = 0;
            while (read != length) {
                read += in.read(buffer);
            }
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public Lexer getLexer() {
        Lexer lexer = this.getClass().getAnnotation(Lexer.class);
        if (lexer == null) {
            throw new IllegalStateException("not fount Lexer annotation");
        }

        return lexer;
    }


    public short readUnsignedByte() {
        byte[] tmp = new byte[2];
        buffer.get(tmp, 1, 1);
        return ByteUtil.bytesToShort(tmp, ByteOrder.BIG_ENDIAN);
    }

    public byte[] readBytes(int length) {
        return readBytes(length,0,0);
    }

    public byte[] readBytes(int length,int index,int offset) {
        byte[] tmp = new byte[length];
        buffer.get(index,tmp,offset,length);
        return tmp;
    }

    public int readUnsignedShort() {
        byte[] tmp = new byte[4];
        buffer.get(tmp, 2, 2);
        return ByteUtil.bytesToInt(tmp, ByteOrder.BIG_ENDIAN);
    }

    public byte readByte() {
        return buffer.get();
    }

    public short readShort() {
        return buffer.getShort();
    }


    public int readInt() {
        return buffer.getInt();
    }

}
