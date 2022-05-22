package club.yuit.basic.function;

/**
 * @author yuit
 * @date 2022/4/2
 **/
public class FunctionMan {
    public static void main(String[] args) {
        LamQuery<TestEntity> l = new LamQuery<>();
        l.lamSelect(TestEntity::getId);
    }
}
