package club.yuit.basic.clazz;

import cn.hutool.core.util.HexUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2023/5/30
 **/


public class AccessFlag {

    /**
     * 2 字节原始数据
     */
    private int value;

    /**
     *  16 进制数据
     */
    private String hex;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        this.hex = HexUtil.toHex(value);
    }

    public String getHex() {
        return hex;
    }


}
