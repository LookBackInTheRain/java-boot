package club.yuit.basic.clazz.struct;

import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.List;

/**
 * @author yuit
 * @date 2023/5/30
 **/
@Getter
@Setter
public class Struct {

    private InputStream stream;

    /**
     * 魔数 4 字节 CAFEBABE
     */
    private String magic;
    /**
     *  2 字节次版本号
     */
    private int minorVersion;
    /**
     * 2 字节主版本号
     */
    private int majorVersion;
    /**
     * 2 字节常量池长度
     */
    private int constantPoolCount;

    /**
     * 常量池 cpInfo[constantPoolCount -1]
     */
    private List<AbstractConstantInfo> cpInfo;

    /**
     * 访问标志
     */
    private ClassAccessFlags accessFlags;

    /**
     * 类索引
     */
    private ClassOrInterfaceIndex thisClassIndex;
    /**
     * 父类索引
     */
    private ClassOrInterfaceIndex superClassIndex;

    /**
     * 类接口索引集合大小
     */
    private int interfaceCount;
    /**
     * 接口索引集合
     */
    private List<ClassOrInterfaceIndex> interfaceIndices;


    @Override
    public String toString() {
        return super.toString();
    }
}
