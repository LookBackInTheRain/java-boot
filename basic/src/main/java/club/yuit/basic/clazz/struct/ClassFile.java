package club.yuit.basic.clazz.struct;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author yuit
 * @date 2023/6/2
 **/
@Getter
@Setter
public class ClassFile {

    private File file;
    private Struct struct;
    private FileInputStream stream;

    public static ClassFile build(String filename)  {
        File file = new File(filename);
        if (!file.exists()||!file.isFile()){
            throw new RuntimeException("error create ClassFile Struct,"+filename + "is not class file");
        }
        ClassFile classFile = new ClassFile();
        classFile.setFile(file);
        try {
            classFile.setStream( new FileInputStream(file));
            classFile.setStruct(new Struct());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return classFile;
    }

}
