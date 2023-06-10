package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 12:46
 *
 *描述栈帧中局部变量表的变量与Java源码中定义的变量之间的关系
 */

@Getter
@Setter
public class LocalVariableTable  extends AttributeItem {
    private int tableLength;
    private LocalVariableTableInfo[] variableTableInfos;

    public LocalVariableTable(AttributeItem source){
        source.copy(this);
    }

    public LocalVariableTable() {
    }
}
