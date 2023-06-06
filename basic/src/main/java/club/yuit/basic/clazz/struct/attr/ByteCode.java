package club.yuit.basic.clazz.struct.attr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuit
 * @date 2023/6/6
 **/
public class ByteCode {

    private final static Map<Integer, String> BYTE_CODE = new HashMap<>();

    static {
        //Constants
        BYTE_CODE.put(0x00, "nop");
        BYTE_CODE.put(0x01, "aconst_null");
        BYTE_CODE.put(0x02, "iconst_m1");
        BYTE_CODE.put(0x03, "iconst_0");
        BYTE_CODE.put(0x04, "iconst_1");
        BYTE_CODE.put(0x05, "iconst_2");
        BYTE_CODE.put(0x06, "iconst_3");
        BYTE_CODE.put(0x07, "iconst_4");
        BYTE_CODE.put(0x08, "iconst_5");
        BYTE_CODE.put(0x09, "lconst_0");
        BYTE_CODE.put(0x0a, "lconst_1");
        BYTE_CODE.put(0x0b, "fconst_0");
        BYTE_CODE.put(0x0c, "fconst_1");
        BYTE_CODE.put(0x0d, "fconst_2");
        BYTE_CODE.put(0x0e, "dconst_0");
        BYTE_CODE.put(0x0f, "dconst_1");
        BYTE_CODE.put(0x10, "bipush");
        BYTE_CODE.put(0x11, "sipush");
        BYTE_CODE.put(0x12, "ldc");
        BYTE_CODE.put(0x13, "ldc_w");
        BYTE_CODE.put(0x14, "ldc2_w");
        // Loads
        BYTE_CODE.put(0x15, "iload");
        BYTE_CODE.put(0x16, "lload");
        BYTE_CODE.put(0x17, "fload");
        BYTE_CODE.put(0x18, "dload");
        BYTE_CODE.put(0x19, "aload");
        BYTE_CODE.put(0x1a, "iload_0");
        BYTE_CODE.put(0x1b, "iload_1");
        BYTE_CODE.put(0x1c, "iload_2");
        BYTE_CODE.put(0x1d, "iload_3");
        BYTE_CODE.put(0x1e, "lload_0");
        BYTE_CODE.put(0x1f, "lload_1");
        BYTE_CODE.put(0x20, "lload_2");
        BYTE_CODE.put(0x21, "lload_3");
        BYTE_CODE.put(0x22, "fload_0");
        BYTE_CODE.put(0x23, "fload_1");
        BYTE_CODE.put(0x24, "fload_2");
        BYTE_CODE.put(0x25, "fload_3");
        BYTE_CODE.put(0x26, "dload_0");
        BYTE_CODE.put(0x27, "dload_1");
        BYTE_CODE.put(0x28, "dload_2");
        BYTE_CODE.put(0x29, "dload_3");
        BYTE_CODE.put(0x2a, "aload_0");
        BYTE_CODE.put(0x2b, "aload_1");
        BYTE_CODE.put(0x2c, "aload_2");
        BYTE_CODE.put(0x2d, "aload_3");
        BYTE_CODE.put(0x2e, "iaload");
        BYTE_CODE.put(0x2f, "laload");
        BYTE_CODE.put(0x30, "faload");
        BYTE_CODE.put(0x31, "daload");
        BYTE_CODE.put(0x32, "aaload");
        BYTE_CODE.put(0x33, "baload");
        BYTE_CODE.put(0x34, "caload");
        BYTE_CODE.put(0x35, "saload");

        //Stores
        BYTE_CODE.put(0x36, "istore");
        BYTE_CODE.put(0x37, "lstore");
        BYTE_CODE.put(0x38, "fstore");
        BYTE_CODE.put(0x39, "dstore");
        BYTE_CODE.put(0x3a, "astore");
        BYTE_CODE.put(0x3b, "istore_0");
        BYTE_CODE.put(0x3c, "istore_1");
        BYTE_CODE.put(0x3d, "istore_2");
        BYTE_CODE.put(0x3e, "istore_3");
        BYTE_CODE.put(0x3f, "lstore_0");
        BYTE_CODE.put(0x40, "lstore_1");
        BYTE_CODE.put(0x41, "lstore_2");
        BYTE_CODE.put(0x42, "lstore_3");
        BYTE_CODE.put(0x43, "fstore_0");
        BYTE_CODE.put(0x44, "fstore_1");
        BYTE_CODE.put(0x45, "fstore_2");
        BYTE_CODE.put(0x46, "fstore_3");
        BYTE_CODE.put(0x47, "dstore_0");
        BYTE_CODE.put(0x48, "dstore_1");
        BYTE_CODE.put(0x49, "dstore_2");
        BYTE_CODE.put(0x4a, "dstore_3");
        BYTE_CODE.put(0x4b, "astore_0");
        BYTE_CODE.put(0x4c, "astore_1");
        BYTE_CODE.put(0x4d, "astore_2");
        BYTE_CODE.put(0x4e, "astore_3");
        BYTE_CODE.put(0x4f, "iastore");
        BYTE_CODE.put(0x50, "lastore");
        BYTE_CODE.put(0x51, "fastore");
        BYTE_CODE.put(0x52, "dastore");
        BYTE_CODE.put(0x53, "aastore");
        BYTE_CODE.put(0x54, "bastore");
        BYTE_CODE.put(0x55, "castore");
        BYTE_CODE.put(0x56, "sastore");

        // Stack
        BYTE_CODE.put(0x57, "pop");
        BYTE_CODE.put(0x58, "pop2");
        BYTE_CODE.put(0x59, "dup");
        BYTE_CODE.put(0x5a, "dup_x1");
        BYTE_CODE.put(0x5b, "dup_x2");
        BYTE_CODE.put(0x5c, "dup2");
        BYTE_CODE.put(0x5d, "dup2_x1");
        BYTE_CODE.put(0x5e, "dup2_x2");
        BYTE_CODE.put(0x5f, "swap");
        // Math
        BYTE_CODE.put(0x60, "iadd");
        BYTE_CODE.put(0x61, "ladd");
        BYTE_CODE.put(0x62, "fadd");
        BYTE_CODE.put(0x63, "dadd");
        BYTE_CODE.put(0x64, "isub");
        BYTE_CODE.put(0x65, "lsub");
        BYTE_CODE.put(0x66, "fsub");
        BYTE_CODE.put(0x67, "dsub");
        BYTE_CODE.put(0x68, "imul");
        BYTE_CODE.put(0x69, "lmul");
        BYTE_CODE.put(0x6a, "fmul");
        BYTE_CODE.put(0x6b, "dmul");
        BYTE_CODE.put(0x6c, "idiv");
        BYTE_CODE.put(0x6d, "ldiv");
        BYTE_CODE.put(0x6e, "fdiv");
        BYTE_CODE.put(0x6f, "ddiv");
        BYTE_CODE.put(0x70, "irem");
        BYTE_CODE.put(0x71, "lrem");
        BYTE_CODE.put(0x72, "frem");
        BYTE_CODE.put(0x73, "drem");
        BYTE_CODE.put(0x74, "ineg");
        BYTE_CODE.put(0x75, "lneg");
        BYTE_CODE.put(0x76, "fneg");
        BYTE_CODE.put(0x77, "dneg");
        BYTE_CODE.put(0x78, "ishl");
        BYTE_CODE.put(0x79, "lshl");
        BYTE_CODE.put(0x7a, "ishr");
        BYTE_CODE.put(0x7b, "lshr");
        BYTE_CODE.put(0x7c, "iushr");
        BYTE_CODE.put(0x7d, "lushr");
        BYTE_CODE.put(0x7e, "iand");
        BYTE_CODE.put(0x7f, "land");
        BYTE_CODE.put(0x80, "ior");
        BYTE_CODE.put(0x81, "lor");
        BYTE_CODE.put(0x82, "ixor");
        BYTE_CODE.put(0x83, "lxor");
        BYTE_CODE.put(0x84, "iinc");
        //Conversions
        BYTE_CODE.put(0x85, "i2l");
        BYTE_CODE.put(0x86, "i2f");
        BYTE_CODE.put(0x87, "i2d");
        BYTE_CODE.put(0x88, "l2i");
        BYTE_CODE.put(0x89, "l2f");
        BYTE_CODE.put(0x8a, "l2d");
        BYTE_CODE.put(0x8b, "f2i");
        BYTE_CODE.put(0x8c, "f2l");
        BYTE_CODE.put(0x8d, "f2d");
        BYTE_CODE.put(0x8e, "d2i");
        BYTE_CODE.put(0x8f, "d2l");
        BYTE_CODE.put(0x90, "d2f");
        BYTE_CODE.put(0x91, "i2b");
        BYTE_CODE.put(0x92, "i2c");
        BYTE_CODE.put(0x93, "i2s");

        //Comparisons
        BYTE_CODE.put(0x94, "lcmp");
        BYTE_CODE.put(0x95, "fcmpl");
        BYTE_CODE.put(0x96, "fcmpg");
        BYTE_CODE.put(0x97, "dcmpl");
        BYTE_CODE.put(0x98, "dcmpg");
        BYTE_CODE.put(0x99, "ifeq");
        BYTE_CODE.put(0x9a, "ifne");
        BYTE_CODE.put(0x9b, "iflt");
        BYTE_CODE.put(0x9c, "ifge");
        BYTE_CODE.put(0x9d, "ifgt");
        BYTE_CODE.put(0x9e, "ifle");
        BYTE_CODE.put(0x9f, "if_icmpeq");
        BYTE_CODE.put(0xa0, "if_icmpne");
        BYTE_CODE.put(0xa1, "if_icmplt");
        BYTE_CODE.put(0xa2, "if_icmpge");
        BYTE_CODE.put(0xa3, "if_icmpgt");
        BYTE_CODE.put(0xa4, "if_icmple");
        BYTE_CODE.put(0xa5, "if_acmpeq");
        BYTE_CODE.put(0xa6, "if_acmpne");

        //Control
        BYTE_CODE.put(0xa7, "goto");
        BYTE_CODE.put(0xa8, "jsr");
        BYTE_CODE.put(0xa9, "ret");
        BYTE_CODE.put(0xaa, "tableswitch");
        BYTE_CODE.put(0xab, "lookupswitch");
        BYTE_CODE.put(0xac, "ireturn");
        BYTE_CODE.put(0xad, "lreturn");
        BYTE_CODE.put(0xae, "freturn");
        BYTE_CODE.put(0xaf, "dreturn");
        BYTE_CODE.put(0xb0, "areturn");
        BYTE_CODE.put(0xb1, "return");
        //References
        BYTE_CODE.put(0xb2, "getstatic");
        BYTE_CODE.put(0xb3, "putstatic");
        BYTE_CODE.put(0xb4, "getfield");
        BYTE_CODE.put(0xb5, "putfield");
        BYTE_CODE.put(0xb6, "invokevirtual");
        BYTE_CODE.put(0xb7, "invokespecial");
        BYTE_CODE.put(0xb8, "invokestatic");
        BYTE_CODE.put(0xb9, "invokeinterface");
        BYTE_CODE.put(0xba, "invokedynamic");
        BYTE_CODE.put(0xbb, "new");
        BYTE_CODE.put(0xbc, "newarray");
        BYTE_CODE.put(0xbd, "anewarray");
        BYTE_CODE.put(0xbe, "arraylength");
        BYTE_CODE.put(0xbf, "athrow");
        BYTE_CODE.put(0xc0, "checkcast");
        BYTE_CODE.put(0xc1, "instanceof");
        BYTE_CODE.put(0xc2, "monitorenter");
        BYTE_CODE.put(0xc3, "monitorexit");

        //Extended
        BYTE_CODE.put(0xc4, "wide");
        BYTE_CODE.put(0xc5, "multianewarray");
        BYTE_CODE.put(0xc6, "ifnull");
        BYTE_CODE.put(0xc7, "ifnonnull");
        BYTE_CODE.put(0xc8, "goto_w");
        BYTE_CODE.put(0xc9, "jsr_w");

        //Reserved
        BYTE_CODE.put(0xca, "breakpoint");
        BYTE_CODE.put(0xfe, "impdep1");
        BYTE_CODE.put(0xff, "impdep2");

    }

    public static String value(int key) {
        return BYTE_CODE.get(key);
    }

}
