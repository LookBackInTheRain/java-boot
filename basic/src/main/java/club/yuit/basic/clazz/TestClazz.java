package club.yuit.basic.clazz;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author yuit
 * @date 2022/5/22
 **/
@Getter
@Setter
public class TestClazz<T> extends ArrayList<T> {
    private String tCount;
    public volatile String tName;
    protected String tgd;
}
