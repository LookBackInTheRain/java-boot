package club.yuit.basic.clazz.constantpool.parser;

import club.yuit.basic.clazz.annotations.ConstPoolLexer;
import club.yuit.basic.clazz.parser.Reader;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author yuit
 * @date 2022/5/23
 * utf8 缩略编码的字符串
 **/
@ConstPoolLexer(1)
public class Utf8Info extends AbstractConstantInfo {


    /**
     * u2 长度
     */
    private int length;
    private String data;

    public Utf8Info(List<AbstractConstantInfo> pools) {
        super(pools);
    }



    @Override
    public void doParser(Reader reader) {

        length = reader.readU2();
        if (length>0){
            byte[] bytes = reader.readBytes(length);
            data = new String(bytes,StandardCharsets.UTF_8);
        }
    }

    @Override
    public void print() {
        System.out.print("Utf8\t\t\t\t"+this.data);
    }

    @Override
    public String getValue() {
        return this.data;
    }

    public int getLength() {
        return length;
    }
}
