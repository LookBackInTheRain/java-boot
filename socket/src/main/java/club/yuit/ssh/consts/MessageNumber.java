package club.yuit.ssh.consts;

/**
 * @author yuit
 * date 2021-03-14 13:14
 **/
public class MessageNumber {

    public final static  byte SSH_MSG_KEXINIT = 20;

    public final static byte SSH_MSG_GLOBAL_REQUEST = 80;
    public final static byte SSH_MSG_REQUEST_SUCCESS = 81;
    public final static byte SSH_MSG_REQUEST_FAILURE = 82;
    public final static byte SSH_MSG_CHANNEL_OPEN = 90;
    public final static byte SSH_MSG_CHANNEL_OPEN_CONFIRMATION = 91;
    public final static byte SSH_MSG_CHANNEL_OPEN_FAILURE = 92;
    public final static byte SSH_MSG_CHANNEL_WINDOW_ADJUST = 93;
    public final static byte SSH_MSG_CHANNEL_DATA = 94;
    public final static byte SSH_MSG_CHANNEL_EXTENDED_DATA = 95;
    public final static byte SSH_MSG_CHANNEL_EOF = 96;
    public final static byte SSH_MSG_CHANNEL_CLOSE = 97;
    public final static byte SSH_MSG_CHANNEL_REQUEST = 98;
    public final static byte SSH_MSG_CHANNEL_SUCCESS = 99;
    public final static byte SSH_MSG_CHANNEL_FAILURE = 100;
}
