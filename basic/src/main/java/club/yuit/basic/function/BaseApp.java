package club.yuit.basic.function;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2020/5/26 下午3:18
 */
@Slf4j
public abstract class BaseApp  {

    List<App> apps= new ArrayList<>();

    public void init(){
        apps.add(this::onStart);
    }


    private void onStart(){
        log.info("onStart");
    }
}
