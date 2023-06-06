package club.yuit.basic.clazz.struct.attr;
import lombok.Getter;
import lombok.Setter;
/**
 * @author yuit
 * @date 2023/6/6
 * 如果当字节 码从第start_pc行[1]到第end_pc行之间（不含第end_pc行）出现了类型为catch_type或者其子类的异常
 * （catch_type为指向一个CONSTANT_Class_info型常量的索引），则转到第handler_pc行继续处理。当 catch_type的值为0时，
 * 代表任意异常情况都需要转到handler_pc处进行处理
 */
@Getter
@Setter
public class  ExceptionInfo {
    private int startPc;
    private int endPc;
    private int handlePc;
    private int catchType;
}
