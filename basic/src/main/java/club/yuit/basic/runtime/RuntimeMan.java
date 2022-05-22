package club.yuit.basic.runtime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author yuit
 * @date 2022/4/1
 **/
public class RuntimeMan {
    public static void main(String[] args) throws IOException, InterruptedException {


    }
    public static boolean isNumber(String s) {

        char[] chs = s.toCharArray();
        char[] beginChs = "+-".toCharArray();
        char[] numberFlg = ".eE".toCharArray();
        char[] numberChs = "0123456789".toCharArray();

        char firstChar = chs[0];
        char lastChar = chs[chs.length-1];

        boolean flg = isContain(firstChar,numberFlg);


        if (!flg) {
            return false;
        }

        for (int i=0;i<chs.length;i++){
            char tmp = chs[i];

        }



        return false;
    }

    private static boolean  isContain(char ch,char[] chs){
        for (char c:chs){
            if (c == ch){
                return true;
            }
        }
        return false;
    }

}



