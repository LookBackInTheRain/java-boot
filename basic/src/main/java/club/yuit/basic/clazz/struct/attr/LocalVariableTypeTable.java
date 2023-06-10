package club.yuit.basic.clazz.struct.attr;

import club.yuit.basic.clazz.struct.AttributeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuit
 * @date: 2023/06/10 12:46
 *
 * Code 中的属性，在运行期间调试器可以使用其来确定局部变量的值
 * 每个局部变量最多一个该属性
 */

@Getter
@Setter
public class LocalVariableTypeTable extends AttributeItem {
    private int tableLength;
    private LocalVariableTypeTableInfo[] variableTableInfos;

    public LocalVariableTypeTable(AttributeItem source){
        source.copy(this);
    }

    public LocalVariableTypeTable() {
    }
}
