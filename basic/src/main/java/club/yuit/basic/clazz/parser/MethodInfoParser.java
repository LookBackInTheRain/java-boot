package club.yuit.basic.clazz.parser;

import club.yuit.basic.clazz.annotations.Lexer;
import club.yuit.basic.clazz.struct.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yuit
 * @date 2023/6/9
 **/
@Slf4j
@Lexer(order = 5)
public class MethodInfoParser  extends FieldMethodInfoParser {
    @Override
    public void doParser(Reader reader, Struct struct) {
        log.debug("start method info parser");
        FieldMethodInfo fm = doFMParser(reader, struct);
        struct.setMethodInfo(fm);
        log.debug("end method info parser");
    }
}
