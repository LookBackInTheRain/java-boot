package club.yuit.basic.function;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yuit
 * @date 2021/3/5
 */
@Slf4j
public class Server  extends BaseApp{

    public Server() {
        this.init();
        this.apps.forEach(App::onStart);
    }
}
