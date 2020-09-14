package club.yuit.basic.function;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yuit
 * @date 2020/5/26 下午3:18
 */
@Slf4j
public class BaseApp {

    public App init(){

        return this::tt;
    }

    private void tt(){
        log.info("tt");
    }
}
