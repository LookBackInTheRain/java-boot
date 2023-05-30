package club.yuit.basic.clazz;

import club.yuit.basic.clazz.constantpool.parser.ConstantInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuit
 * @date 2023/5/30
 **/
@Getter
@Setter
public class Struct {
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
    private List<ConstantInfo> cpInfo;

    /**
     * 访问标志
     */
    private AccessFlag accessFlag;



}
