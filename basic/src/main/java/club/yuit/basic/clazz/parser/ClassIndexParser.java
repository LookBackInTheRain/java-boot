package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.struct.ClassOrInterfaceIndex;
import club.yuit.basic.clazz.struct.Struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuit
 * @date: 2023/06/04 15:40
 * 类索引,父类索引，接口索引集合
 */

@Lexer(order = 3)
public class ClassIndexParser  extends AbstractParser{
    @Override
    public void doParser(Reader reader, Struct struct) {

        ClassOrInterfaceIndex classIndex = new ClassOrInterfaceIndex(reader.readU2());
        ClassOrInterfaceIndex superClassIndex = new ClassOrInterfaceIndex(reader.readU2());

        struct.setThisClassIndex(classIndex);
        struct.setSuperClassIndex(superClassIndex);

        int ifCount = reader.readU2();
        if (ifCount!=0){
            List<ClassOrInterfaceIndex> interfaceIndices = new ArrayList<>(ifCount);
            for (int i=0;i<ifCount;i++){
                interfaceIndices.add(new ClassOrInterfaceIndex(reader.readU2()));
            }

            struct.setInterfaceIndices(interfaceIndices);
        }
    }
}
