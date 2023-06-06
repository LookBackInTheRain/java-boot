package club.yuit.basic.clazz.struct;

import club.yuit.basic.clazz.constantpool.parser.AbstractConstantInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuit
 * @date 2023/6/6
 * 常量池
 **/
@Getter
@Setter
public class ConstantPool {

    /**
     * u2  常量池容量
     */
    private int count;
    private List<AbstractConstantInfo> cpInfo;

}
