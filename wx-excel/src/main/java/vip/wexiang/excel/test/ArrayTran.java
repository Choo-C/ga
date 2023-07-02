package vip.wexiang.excel.test;

import vip.wexiang.excel.translate.impl.TextTranslate;

import javax.xml.soap.SAAJResult;
import java.util.*;
import java.util.Random;
import java.util.stream.Collectors;

public class ArrayTran {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("")
        String src = "首页   ~1\n" +
            "钟表珠宝  ~2\n" +
            "待商家同意  ~3\n" +
            "商家已同意（退款中）  ~4\n" ;

        TextTranslate textTranslate = new TextTranslate();
        String translated = "";
        try{
            translated = textTranslate.translate(src, "zh-CHS", "ja");

        }catch (Exception e){
            e.printStackTrace();
        }
        String[] split = translated.split("\n");
        System.out.println("xxxxxxxxxx");
        for (String s:split){
            System.out.println(s);
        }
        System.out.println(split.length);
        //随机暂停
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println(i);
        long start = System.currentTimeMillis();
        try {

            Thread.sleep(i*1000);
        }
        catch (Exception e){

            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(i);
        System.out.println(end-start);



    }

}
