package club.yuit.basic.function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
import java.util.List;

/**
 * @author yuit
 * @date 2020/5/26 下午3:20
 */
@RunWith(JUnit4.class)
public class FunctionTest {




    @Test
    public void test(){
        StringBuffer sb = new StringBuffer("Hello");
        String t = " MY";
        t = t + " FRIEND";
        sb.append(t);
        System.out.println(sb.toString().toLowerCase());

    }

    static void a(){

    }

}

class Cry{
    public void cry(){ System.out.println("大家好!"); }}
 class E{
    public static void main(String[] args){
        Cry hello=new Cry(){
            public void cry(){ System.out.println("大家好，祝工作顺利!");}
        };
        hello.cry();
    }
}


class A<T> {
    public T aa(){

        return null;
    }
}

class B extends A<Integer>{
    public static void main(String args[]){
        TreeSet<Integer> set = new TreeSet<>();

        while (set.size()!=10){
            int x=(int)(Math.random()*100);
            set.add(x);
        }

        set.forEach(System.out::println);

    }
}

class XYdistance {
    private int x;
    private int y;

    public XYdistance(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double distance(XYdistance m){
        double dis = ((this.x-m.getX())*(this.x-m.getX()))+(this.y-m.getY())*(this.y-m.getY());
        return Math.sqrt(dis);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class TestXY {
    public static void main(String[] a){
        XYdistance m = new XYdistance(10,10);

        XYdistance m1 = new XYdistance(0,0);

        System.out.println(m.distance(m1));
    }
}
