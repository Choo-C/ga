package vip.wexiang.job.txt.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class TestCF {
    public static void main(String[] args) {
        /* 生成随机日期 */
        // String date = RandomUtil.randomDate("2020-01-01", "2020-12-31").toString();
        /* 生戯含有中文的字符中 */
//生成随机日期
        // String date = RandomUtil.randomDate("2020-01-01", "2020-12-31").toString();
        // String chinese = RandomUtil.randomChineseString(10);
        runAsync();
    }
    public static void runAsync(){
//        Runnable 线程
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            System.out.println("开始计算");
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){

            }
            System.out.println("计算结束，但是没有值的返回");
        });
        System.out.println("主线程继续执行自己的事");
        try {
            TimeUnit.SECONDS.sleep(10);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void supplyAsync(){
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("任务线程开始计算");
                Thread.sleep(10000);
                System.out.println("计算结束");
            }catch (Exception e){
                e.printStackTrace();
            }
            return "RESULT 123123";
        });
        System.out.println("主线程继续");
        cf.thenAccept(System.out::println);
        System.out.println("无需等待，继续主线程自己的事123");
        try {
            TimeUnit.SECONDS.sleep(15);
        }catch (Exception e){

        }
    }
}
