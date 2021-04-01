package club.yuit.ssh;

import java.util.Hashtable;

/**
 * @author yuit
 * date 2021-04-01 22:49
 **/
public class SSH {

    public final static Hashtable<String,String> CONFIG = new Hashtable<>();

    static {
        CONFIG.put("KEX","ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group14-sha1,diffie-hellman-group-exchange-sha256,diffie-hellman-group-exchange-sha1,diffie-hellman-group1-sha1");
        CONFIG.put("SERVER_HOST_KEY", "ssh-rsa,ssh-dss,ecdsa-sha2-nistp256,ecdsa-sha2-nistp384,ecdsa-sha2-nistp521");
    }




}
