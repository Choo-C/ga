package vip.wexiang.job.txt.test;

import java.util.Arrays;
import java.util.List;

public class TestString  {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("error:      出错的位置可能对不上，如果是某一行没数据可能会影响到错处的坐标位置   仅供参考：这个有道欠费了，请充值，再使用！Youdao(url=https://openapi.youdao.com/api, <~~>appkey=3a98d3b6fa96b508, appsecret=sth7u8U3flx02og0dZtOJyc1sS2ctwRT)||||这个有道欠费了，请充值，再使用！<~~>Youdao(url=https://openapi.youdao.com/api, appkey=3a98d3b6fa96b508, appsecret=sth7u8U3flx02og0dZtOJyc1sS2ctwRT)|<~~>|||这个有道欠费了，请充值，再使用！Youdao(url=https://openapi.youdao.com/api, appkey=6b09dd60007c0946, appsecret=dZdHqPQWUhob2WUlSXAE9MlSuTYSUdUW)".split("<~~>"));
        for (String s : list) {
            System.out.println(s);
        }


    }
}
