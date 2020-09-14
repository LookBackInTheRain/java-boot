package club.yuit.io;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author yuit
 * @date 2020/5/12 下午12:25
 */
public class StreamUtilTest {

    @Test
    public void test() throws IOException, URISyntaxException {

        int flg =0;

        while (flg<10){
            flg+=1;

            System.out.println(flg);
        }

       // FileUtil.createFile("file:///User/yuit/B79A445F-841A-4167-9C28-A4B86B01E219.dmp");
    }

    @Test
    public void fileInputStreamTest() throws IOException, URISyntaxException {
        StreamUtil.readFileData();
    }


    @Test
    public void fileOutputStreamTest() throws IOException {
        StreamUtil.writerFile();
    }


    @Test
    public void readerTest() throws IOException {
        StreamUtil.reader();
    }

    @Test
    public void writerTest() throws IOException {
        StreamUtil.writer("yuit007");
    }

}
