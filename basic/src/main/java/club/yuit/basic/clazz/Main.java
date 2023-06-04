package club.yuit.basic.clazz;

import club.yuit.basic.clazz.constantpool.ConstantPoolParserManger;
import club.yuit.basic.clazz.struct.ClassFile;
import club.yuit.basic.clazz.struct.Struct;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.HexUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;

/**
 * @author yuit
 * @date 2022/5/22
 * <p>
 * 解析class文件
 **/
public class Main {

    public static void main(String[] args) throws IOException {

        ClassFileParser parser = new ClassFileParser();

        ClassFile classFile = parser.parse("D:/dev/java-boot/basic/files/TestClazz.class");

        System.out.println();

    }





}
