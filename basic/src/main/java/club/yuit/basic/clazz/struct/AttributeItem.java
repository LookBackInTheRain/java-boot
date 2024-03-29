package club.yuit.basic.clazz.struct;

import club.yuit.basic.clazz.utils.ByteBufferReader;
import cn.hutool.core.bean.BeanUtil;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

/**
 * @author yuit
 * @date 2023/6/6
 **/
@Getter
@Setter
public class AttributeItem {

    /**
     * 名称索引
     */
    private int nameIndex;
    /**
     * 属性长度
     */
    private int length;
    /**
     * 属性内容
     */
    private ByteBuffer buffer;

    private String name;

    private ByteBufferReader reader;


    public ByteBufferReader getReader(){
        return reader==null ? ByteBufferReader.build(this.buffer): this.reader;
    }


    public void copy(AttributeItem dst){
        BeanUtil.copyProperties(this,dst);
    }

}
