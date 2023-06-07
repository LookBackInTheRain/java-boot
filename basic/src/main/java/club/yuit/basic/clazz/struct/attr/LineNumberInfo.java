package club.yuit.basic.clazz.struct.attr;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/6/7
 **/
@Getter
@Setter
public class LineNumberInfo {

    /**
     * u2 字节码行号
     */
    private int startPc;

    /**
     * u2 源码行号
     */
    private int lineNumber;

}
