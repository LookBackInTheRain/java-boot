package club.yuit.basic.clazz;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/5/30
 **/

@Getter
@Setter
public class AccessFlag {

    /**
     * 2 字节原始数据
     */
    private int value;

    /**
     *  16 进制数据
     */
    private String hex;




}
