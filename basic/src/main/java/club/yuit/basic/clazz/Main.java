package club.yuit.basic.clazz;

import club.yuit.basic.clazz.constantpool.ConstantPoolParserManger;
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
        File f = new File("/Users/yuit/dev/yuit/java-boot/basic/files/TestClazz.class");

        Struct struct = new Struct();

        FileInputStream in = new FileInputStream(f);
        byte[] buf4 = new byte[4];


        // read 4 bytes magic number
        in.read(buf4);

        System.out.println("Magic Number: " + HexUtil.encodeHexStr(buf4, false));

        // minor version
        clear(buf4);
        in.read(buf4, 2, 2);
        System.out.println("Minor Version: " + ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN));

        // major vesion
        clear(buf4);
        in.read(buf4, 2, 2);
        System.out.println("Major Version: " + ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN));

        // constant_pool_count
        clear(buf4);
        in.read(buf4, 2, 2);
        int constantPoolCount = ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN);
        System.out.println("CONSTANT_POOL_COUNT: " + constantPoolCount);

        // constant_pool
        System.out.println("Constant_Pool:");
        int constantPoolFlg = 1;

        ConstantPoolParserManger constantPoolParser = new ConstantPoolParserManger(struct);
        while (constantPoolFlg < constantPoolCount) {
            constantPoolFlg++;
            clear(buf4);
            in.read(buf4, 3, 1);
            int tag = ByteUtil.bytesToInt(buf4, ByteOrder.BIG_ENDIAN);
            constantPoolParser.parse(tag, in);
        }

       /* for (int i=0;i<poolList.size();i++) {
            System.out.print("\t#");
            if (i<9){
                System.out.print("0"+(i+1)+"=");
            }else {
                System.out.print((i+1)+"=");
            }
            poolList.get(i).print();
            System.out.println();
        }*/

        clear(buf4);
        in.read(buf4,2,2);
        System.out.println("ACCESS_FLAGS: "+HexUtil.encodeHexStr(buf4,false));

        clear(buf4);
        in.read(buf4,2,2);
        int classIndex = ByteUtil.bytesToInt(buf4,ByteOrder.BIG_ENDIAN);
        clear(buf4);
        in.read(buf4,2,2);
        int superClassIndex = ByteUtil.bytesToInt(buf4,ByteOrder.BIG_ENDIAN);
        clear(buf4);
        //System.out.println(poolList.get(classIndex-1).getValue()+" extends " + poolList.get(superClassIndex-1).getValue());
        in.read(buf4,2,2);
        int implsIndex = ByteUtil.bytesToInt(buf4,ByteOrder.BIG_ENDIAN);
        in.close();

    }


    static void clear(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            b[i] = 0;
        }
    }


}
