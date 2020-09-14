package club.yuit.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author yuit
 * @date 2020/5/12 下午12:06
 */
@Slf4j
public class FileUtil {

    public static void createFile(String pathName) throws IOException, URISyntaxException {
        File file = new File(new URI(pathName));
        log.info(file.list().toString());
    }

    public static void randomAccessFileTest() throws FileNotFoundException {
        RandomAccessFile file = new RandomAccessFile("random.txt","rw");

    }

}
