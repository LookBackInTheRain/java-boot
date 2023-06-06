package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.AccessFlagsUtils;
import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.struct.Struct;

/**
 * @author: yuit
 * @date: 2023/06/04 15:33
 *访问标志解析
 */

@Lexer(order = 2)
public class AccessFlagParser  extends AbstractParser{
    @Override
    public void doParser(Reader reader, Struct struct) {
        int value = reader.readU2();
        struct.setAccessFlags(AccessFlagsUtils.convertToFlags(value));
    }
}
