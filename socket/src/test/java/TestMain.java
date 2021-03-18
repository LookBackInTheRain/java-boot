import club.yuit.ssh.encryption.*;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author yuit
 * date 2021-03-13 20:58
 **/
@Slf4j
public class TestMain {
    public static void main(String[] args) {

        AES cbc =  AES192CBC.getAES();
        byte[] data = "123123123123".getBytes(StandardCharsets.UTF_8);
        byte[] ec = cbc.encode(data);
        byte[] dc = cbc.decode(ec);

        System.out.println();


    }
}
