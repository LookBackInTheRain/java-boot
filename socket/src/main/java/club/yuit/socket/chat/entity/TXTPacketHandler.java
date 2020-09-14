package club.yuit.socket.chat.entity;

import club.yuit.common.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date 2020/5/14 下午2:31
 */
@Slf4j
public class TXTPacketHandler implements IPacket {
    /**
     * 拆包
     *
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public TXTPacket parsePacket(InputStream in) throws Exception {

        while (in.available() < 5) {
            Thread.sleep(1000);
        }

        // 获取消息一个字节的消息类型
        byte type = (byte) in.read();
        if (!Constant.isMessage(type)) {
            throw new RuntimeException("未知消息类型");
        }

        // 读取4个字节的消息长度
        byte[] msgLen = new byte[4];
        int rl = in.read(msgLen);

        while (rl != 4) {
            int f = in.read(msgLen, 4 - rl, 4 - rl);
            rl += f;
        }

        TXTPacket packet = new TXTPacket();
        packet.setMsgType(type);

        // 大段转小端
        msgLen = this.bigToLittle(msgLen);

        int ml = ObjectUtil.bytesToInteger(msgLen);
        packet.setLength(ml);

        byte[] data=readData(in,ml);

        packet.setData(new String(data, StandardCharsets.UTF_8));

        return  packet;
    }



    @Override
    public byte[] readData(InputStream in, int len) throws Exception {

        List<byte[]> dataByteList = new ArrayList<>();

        // 如果剩余可读字节没有达到数据长度等待1秒后继续判断
        while (in.available() - len < 0) {
            Thread.sleep(1000);
        }


        int dLen = 128;

        if (len < dLen) {
            dLen = len;
        }

        byte[] bf = new byte[dLen];
        int rl = 0;

        while (rl != len) {
            int flg = in.read(bf,0,dLen);
            dataByteList.add(bf);
            rl += flg;

            // 如果数据字节数减去已读字节数小于bf缓冲区长度，说明只需要在读取一次即可读完本次的数据包，解决数据粘包问题
            if (len-rl<dLen){
                dLen=len-rl;
            }

            bf = new byte[dLen];

        }

        byte[] result = new byte[len];
        int pos=0;

        for (int i=0;i<dataByteList.size();i++) {
            byte[] bs = dataByteList.get(i);
            System.arraycopy(bs,0,result,pos,bs.length);
            pos = i*bs.length+1;
        }


        return result;
    }


    public void sendPacket(TXTPacket packet, OutputStream ot) throws IOException {

        if (packet.getData()!=null){
            packet.setLength(packet.getData().getBytes().length);
        }
        log.info("数据长度:{}",packet.getLength());
        log.info("消息类型:{}",packet.getMsgType());
        byte[] bytes = new byte[packet.getLength()+5];

        bytes[0]=packet.getMsgType();

        byte[] mlBytes = ObjectUtil.intToBytes(packet.getLength());
        System.arraycopy(mlBytes,0,bytes,1,4);

        System.arraycopy(packet.getData().getBytes(),0,bytes,5,packet.getLength());

        log.info("packet长度:{}",bytes.length);

        ot.write(bytes);
        ot.flush();

    }


    private byte[] bigToLittle(byte[] li){

        byte[] bytes = new byte[li.length];
        int i=0;

        for (int j = li.length-1; j >=0; j--) {
            bytes[i]=li[j];
            i++;
        }

        return bytes;
    }

}
