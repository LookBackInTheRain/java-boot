package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.struct.ClassFile;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author yuit
 * @date 2023/6/2
 **/
public class ClassFileReader implements Reader {


   private FileInputStream stream;

    public ClassFileReader(FileInputStream stream) {
       this.stream = stream;
    }

    @Override
    public InputStream getStream() {
        return stream;
    }


}
