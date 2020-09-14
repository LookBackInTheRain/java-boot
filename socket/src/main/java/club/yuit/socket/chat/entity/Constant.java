package club.yuit.socket.chat.entity;

/**
 * @author yuit
 * @date 2020/5/13 下午5:57
 */
public class Constant {
    public final static byte MSG_USER_ONLINE=0;
    public final static byte MSG_DATA_TXT=1;
    public final static byte MSG_DATA_IMAGE=2;
    public final static byte MSG_DATA_VIDEO=3;

    public static boolean isMessage(byte b){
        boolean flg = true;
        switch (b){
            case MSG_DATA_IMAGE:
            case MSG_DATA_TXT:
            case MSG_DATA_VIDEO:
            case MSG_USER_ONLINE:
                break;
            default: flg = false;
        }

        return flg;
    }

}
