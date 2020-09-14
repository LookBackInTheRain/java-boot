package club.yuit.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2020/5/12 下午4:06
 */
@Slf4j
public class StreamUtil {

    public static void readFileData() throws URISyntaxException, IOException {
        File file = new File(new URI("file:///Users/yuit/welfare.json"));
        FileInputStream in = new FileInputStream(file);
        // 文件大小
        long fLength = file.length();
        log.info("文件大小:{}", fLength);
        // 可读字节数
        int cLength = in.available();
        log.info("可读取的字节数:{}", cLength);
        int len = 128;
        byte[] bf = new byte[len];
        List<byte[]> dataByte = new ArrayList<>();

        if (cLength < len) {
            len = cLength;
        }

        int rl = 0;

        while ((rl = in.read(bf, 0, len)) != -1) {
            dataByte.add(bf);
            bf = new byte[len];
        }
        // 关闭流
        in.close();
        byte[] result = new byte[len * dataByte.size()];
        int p = 0;
        int flg = 0;
        for (byte[] b : dataByte) {
            System.arraycopy(b, 0, result, p, len);
            flg++;
            p = flg * len;
        }
        log.info("文件数据:\n{}", new String(result, StandardCharsets.UTF_8));

    }


    public static void writerFile() throws IOException {
        File file = new File("./data.txt");

        log.info("文件路径：{}", file.getAbsolutePath());

        // 数据追加方式写入
        OutputStream ot = new FileOutputStream(file, true);
        String data = "写入的数据";
        ot.write(data.getBytes());
        ot.close();

        try {

        } finally {

        }

    }


    public static void reader() throws IOException {
        File file = new File("./data.txt");
        FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8);
        BufferedReader bfr = new BufferedReader(fileReader);
        String str = null;
        StringBuilder sbf = new StringBuilder();
        while ((str = bfr.readLine()) != null) {
            sbf.append(str);
        }

        log.info("\n{}", sbf.toString());
        fileReader.close();
        bfr.close();
    }


    public static void writer(String data) throws IOException {
        File file = new File("./data.txt");
        // 追加方式写入
        FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8, true);
        BufferedWriter bfw = new BufferedWriter(writer);
        bfw.write(data);
        bfw.close();

    }


}

