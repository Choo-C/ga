package vip.wexiang.job.txt.local;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import vip.wexiang.common.utils.poi.ExcelUtil;
import vip.wexiang.job.translate.impl.TextTranslateTwo;
import vip.wexiang.job.txt.domain.JobTranExcel;
import vip.wexiang.job.txt.domain.Youdao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class ExcelMultithreadingTwo {
    public static int count = 0;
    public static void main(String[] args) {
        String directory = "C:\\excel\\xxx\\";
        String fileName = "src.xlsx";
        String path = directory+fileName;


        System.out.println("XXXXXXXXXXXXXXX");

        try{
            FileInputStream inputStream = new FileInputStream(new File(path));
//        获取到了整个excel，但目前只是拿到了原文
            List<JobTranExcel> rows = ExcelUtil.importExcel(inputStream, JobTranExcel.class);
            log.error(rows.size()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

            // 创建一个线程安全的队列
            ConcurrentLinkedQueue<JobTranExcel> queue = new ConcurrentLinkedQueue<>();
            queue.addAll(rows);

            //创建一个有道key集合
            List<Youdao> list = new ArrayList<>();
//            珠宝通用
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("3a98d3b6fa96b508").appsecret("sth7u8U3flx02og0dZtOJyc1sS2ctwRT").build());
//            珠宝2
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6b09dd60007c0946").appsecret("dZdHqPQWUhob2WUlSXAE9MlSuTYSUdUW").build());
//            3
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("71df0580007c5a2b").appsecret("hHvZoUKvJ2AT0CkXMGZttsImQI1lpBvi").build());
//            4
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0f135cc0008b7094").appsecret("gZOu0Z8djBcso9h4KlRSAnYUERt7z9H6").build());
//            5
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("02f306b5008bba09").appsecret("d6AjnDuxFR8q0IePHhQ3VZlAfuIqmvJT").build());
//            6
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("78e04fc3008c3aa0").appsecret("PsKk2wedl4AEeM0zogKoxGAIqCoEaGjg").build());
////            7
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6b00d429008c9dd3").appsecret("1PP8kRcjBDJ9s0VWDMFnq6Q95Bk7wzvf").build());
//            8
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("3ee656f9008ce0e6").appsecret("ITzVYpyep2faZ0I0UHfm3Vs9Bma5aDKf").build());
////            9
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6e0eed59008d1334").appsecret("Rx4U9oTLz5jotlx6kKbTNr3WyIL04twE").build());
//            10
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("18ab0027008d42fe").appsecret("GcbFQ69wuVvhGWMrdeDSnjGfz6miTZJH").build());
////            //            11
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0fcbc3bd00da64e6").appsecret("uUaqSfZI47BfoXb6EMkokhyuQU4gqlMx").build());
//            //            12
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("42065f3200da9171").appsecret("5pJ87KfLhjDw8W0TdsGUUVSiZBEG2GAS").build());
//            //            13
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0e61923b00dad6db").appsecret("RT7fKZpdPrqf89urHsjtwl1UUwRdkRO8").build());
//            //            14
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("1e4b114600db1a81").appsecret("fFDhArzBazvtlZgz7IFlRbNYm56kgBcz").build());
//            //            15
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("07e77b8b00db541f").appsecret("yopOdV6Hj4W7SPP0rY0RnuDWxbx1LEl9").build());
//            //            16
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("7d28c03a00db87a3").appsecret("QpruIT9VKuPGFNuJcv3GK10pUzpSNehz").build());
//            //            17
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("2d2159bd00e3fd4f").appsecret("xa7S6jTwIvJMTOo5D6GKsQW9RbB3EyKR").build());
//            //            18
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("578c491d00e46e7a").appsecret("FeSR8Z6Shx1LZB0rW6qZtOXqqOxefzfa").build());
//            //            19
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6c5f32e900e64897").appsecret("GBuL8FW1xe6v90MJ7TYfOuQIHsn68Y2R").build());
//            //            20
//            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("62ccf56900e678c4").appsecret("hPG9hMbPow5c8fTMFaO7CAM32YUZdl0H").build());

            // 创建一个线程池
            ExecutorService executor = Executors.newFixedThreadPool(list.size());
            System.out.println(list.size());
            // 提交任务到线程池
            for (int i = 0; i < list.size(); i++) {
                Youdao o = list.get(i);
                ElementProcessor processor = new ElementProcessor(queue, o);
                processor.setName("tran:>>>"+i+"<<<<<");
                executor.submit(processor);
            }

            // 关闭线程池
            executor.shutdown();

            // 等待所有任务完成
            int q = 0;
            try{
                while (!executor.isTerminated()) {
                    // 等待
//                    多线程问题。加了延时与检测是否开始有线程执行完了，如果部分卡了，还能靠检测次数，如果超过就停止,防止翻译的成果丢失
//                    如果此预防措施也失效了，可断网等动作中止程序，通过try-catch跳出while循环。
                    if (count==0){
                        log.info("检验是否弄好");
                        Thread.sleep(60*1000);
                        continue ;

                    }else if(count>=(list.size()/2)){
                        log.info("已经弄好一半部分");
                        Thread.sleep(60*1000);
                        if (q>=10){
                            break ;
                        }
                        q++;

                    }else {
                        log.info("已经弄好一部分");
                        Thread.sleep(30*1000);
                    }


                }
            }catch (Exception e){
                e.printStackTrace();
            }


            // 将处理后的数据写入到Excel文件中
//            String xxfileName = "result.xlsx";
//            EasyExcel.write(fileName, TranExcel.class).sheet("Sheet1").doWrite(queue);
//
//            for (TranExcel row : rows){
//                //拿到了一行，根据原文，逐一翻译对应单词或者句子，将翻译结果set回对应语言。
//                log.info(row.toString());
////                一行原文
//                String src = row.getSrc();
//                for (String language :languages){
//                    String translation = translateTool.translate(src,"zh-CHS",language);
//                    String setterName = "set" + language.substring(0, 1).toUpperCase() + language.substring(1);
//                    Method setterMethod = TranExcel.class.getMethod(setterName, String.class);
//                    setterMethod.invoke(row,translation);
//                }
//                //此刻翻译完一行，每种语言都翻译了
//            }
            //此刻翻译完所有原文
            String salt = RandomUtil.randomString(5);
            FileOutputStream outputStream = new FileOutputStream(new File(directory+salt+fileName));
            ExcelUtil.exportExcel(rows,"翻译表", JobTranExcel.class,outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}


@Slf4j
class ElementProcessorXX extends Thread {
    private ConcurrentLinkedQueue<JobTranExcel> queue;
    private Youdao youdao;

    public ElementProcessorXX(ConcurrentLinkedQueue<JobTranExcel> queue
        ,Youdao youdao) {
        this.queue = queue;
        this.youdao = youdao;
    }

    @Override
    public void run() {
        //所有的语言
        //    新增语言，请在后续自行添加。并且对应好属性
        String lang = "en,ja,de,pt,ko,hi,fr,vi,ms,pl,sv,ar,ta,it,fi,id,th";
        List<String> languages = Arrays.asList(lang.split(","));
        TextTranslateTwo translateTool = new TextTranslateTwo(youdao);
        try{
            while (!queue.isEmpty()) {

                JobTranExcel element = queue.poll();
                log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+getName());
                System.out.println(element.toString());
//                一行原文
                String src = element.getSrc();
                for (String language :languages){
                    String translation = translateTool.translate(src,"zh-CHS",language);
//                    set语言
                    String setterName = "set" + language.substring(0, 1).toUpperCase() + language.substring(1);
                    Method setterMethod = JobTranExcel.class.getMethod(setterName, String.class);
//                    set传入值
                    setterMethod.invoke(element,translation);
                }
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        log.error(ExcelMultithreadingTwo.count+"<<<<<<<<<<<<<<<<<<<<<<<<<<");
        ExcelMultithreadingTwo.count++;



    }
}

