package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.struct.FieldMethodInfo;
import club.yuit.basic.clazz.struct.Struct;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yuit
 * @date 2023/6/9
 **/
@Slf4j
@Lexer(order = 4)
public class FieldInfoParser  extends FieldMethodInfoParser{
    @Override
    public void doParser(Reader reader, Struct struct) {
        log.debug("start field info parser");
        FieldMethodInfo fm = doFMParser(reader, struct);
        struct.setFieldInfo(fm);
        log.debug("end field info parser");
    }
}
