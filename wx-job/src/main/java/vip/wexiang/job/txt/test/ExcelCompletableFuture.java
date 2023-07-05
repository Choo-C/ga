package vip.wexiang.job.txt.test;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcelFactory;
import vip.wexiang.common.utils.poi.ExcelUtil;
import vip.wexiang.job.translate.impl.TextTranslateTwo;
import vip.wexiang.job.txt.domain.JobTranExcel;
import vip.wexiang.job.txt.domain.Youdao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ExcelCompletableFuture {

    public static List<Youdao> getYoudao(){
        //youdao 的list
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
//            7
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6b00d429008c9dd3").appsecret("1PP8kRcjBDJ9s0VWDMFnq6Q95Bk7wzvf").build());
//            8
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("3ee656f9008ce0e6").appsecret("ITzVYpyep2faZ0I0UHfm3Vs9Bma5aDKf").build());
//            9
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6e0eed59008d1334").appsecret("Rx4U9oTLz5jotlx6kKbTNr3WyIL04twE").build());
//            10
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("18ab0027008d42fe").appsecret("GcbFQ69wuVvhGWMrdeDSnjGfz6miTZJH").build());
            //            11
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0fcbc3bd00da64e6").appsecret("uUaqSfZI47BfoXb6EMkokhyuQU4gqlMx").build());
            //            12
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("42065f3200da9171").appsecret("5pJ87KfLhjDw8W0TdsGUUVSiZBEG2GAS").build());
            //            13
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("0e61923b00dad6db").appsecret("RT7fKZpdPrqf89urHsjtwl1UUwRdkRO8").build());
            //            14
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("1e4b114600db1a81").appsecret("fFDhArzBazvtlZgz7IFlRbNYm56kgBcz").build());
            //            15
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("07e77b8b00db541f").appsecret("yopOdV6Hj4W7SPP0rY0RnuDWxbx1LEl9").build());
            //            16
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("7d28c03a00db87a3").appsecret("QpruIT9VKuPGFNuJcv3GK10pUzpSNehz").build());
            //            17
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("2d2159bd00e3fd4f").appsecret("xa7S6jTwIvJMTOo5D6GKsQW9RbB3EyKR").build());
            //            18
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("578c491d00e46e7a").appsecret("FeSR8Z6Shx1LZB0rW6qZtOXqqOxefzfa").build());
            //            19
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("6c5f32e900e64897").appsecret("GBuL8FW1xe6v90MJ7TYfOuQIHsn68Y2R").build());
            //            20
            list.add(Youdao.builder().url("https://openapi.youdao.com/api").appkey("62ccf56900e678c4").appsecret("hPG9hMbPow5c8fTMFaO7CAM32YUZdl0H").build());
        return list;
    }
    private static List<String> xx(String lang){
        //符合youdao翻译的语言
        String youdaoSupportString = "zh-CHS,zh-CHT,en,ja,ko,fr,es,pt,it,ru,vi,de,ar,id,af,bs,bg,yue,ca,hr,cs,da,nl,et,fj,fi,el,ht,he,hi,mww,hu,sw,tlh,lv,lt,ms,mt,no,fa,pl,otq,ro,sr-Cyrl,sr-Latn,sk,sl,sv,ty,th,to,tr,uk,ur,cy,yua,sq,am,hy,az,bn,eu,be,ceb,co,eo,tl,fy,gl,ka,gu,ha,haw,is,ig,ga,jw,kn,kk,km,ku,ky,lo,la,lb,mk,mg,ml,mi,mr,mn,my,ne,ny,ps,pa,sm,gd,st,sn,sd,si,so,su,tg,ta,te,uz,xh,yi,yo,zu";
        List<String> youdaoSupport = new ArrayList<>(Arrays.asList(youdaoSupportString.split(",")));
        List<String> languages = Arrays.stream(lang.split(",")).map(String::trim).collect(Collectors.toList());
//        List<String> languages = new ArrayList<>(Arrays.asList(lang.split(",")));
        // 使用LinkedHashSet删除重复项并保持插入顺序
        Set<String> set = new LinkedHashSet<>(languages);
        // 清空原始列表
        languages.clear();
        // 将Set中的元素添加回列表
        languages.addAll(set);
//        去除不支持的元素
//        languages.retainAll(youdaoSupport);
        languages = languages.stream()
            .filter(youdaoSupport::contains)
            .collect(Collectors.toList());
        return languages;
    }
    public static void main(String[] args) throws Exception{
//        ,vi,ms,pl,ar,it,ar,ta,sv,fi,id,th
//,ko,hi,fr,de,vi,ms,pl,ar,it,ar,ta,sv,fi,id,th
        String lang = "en,pt,ja";
        List<String> languages = xx(lang);

        // 创建一个线程安全的队列，当多线程操作时，可以使用此队列。安全的获取到目标语言，根据语言执行语言指令和/或替换标准词库中的单词。
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(languages);

        String directory = "Y:\\excel\\";
        String fileName = "src.xlsx";
        String path = directory+fileName;
        System.out.println(languages);
        FileInputStream inputStream = new FileInputStream(new File(path));
//        获取到了整个excel，但目前只是拿到了原文
        List<JobTranExcel> importExcel = ExcelUtil.importExcel(inputStream, JobTranExcel.class);
        //线程数量依据youdaoid有多少而决定的
        List<Youdao> youdao = getYoudao();
        //        原文 翻译语言 youdaoid 都不能为0，否则解决翻译。
        if (importExcel.size() <= 0 && languages.size() <=0 && youdao.size() <= 0){
            System.out.println("");
            return;
        }

//        输出到Excel的数据，List里每个Map元素代表了一行，Map的key代表了列，value代表了数据
        List<Map<Integer,String>> rows = new ArrayList<>();
        //每列的语言是什么。
        Map<Integer, String> head = new HashMap<>();
        //第一列为原文
        head.put(0,"src");
        for (int x = 0;x<languages.size();x++){
            //跳过第一列，第一列已经被原文占领了。
            head.put(x+1,languages.get(x));
        }
        //每列的语言是什么，以代号形式显示
        rows.add(head);
//        写好rows的第一列原文
        for (int x = 0;x<importExcel.size();x++){
            JobTranExcel excel = importExcel.get(x);
            Map<Integer, String> map = new HashMap<>();
            map.put(0,excel.getSrc());
            rows.add(map);
        }


//        每次翻译只翻译一种语言。如果有多余api或者是线程，剩余的线程不会执行。
        if (languages.size()<youdao.size()){
            youdao = ListUtil.sub(youdao,0,languages.size());
        }

        //执行了多线程得到了，多个List<String> 三个语言 10句话翻译
        //        --------------------------------多线程组装
//        需要 src、lang、youdao key
        List<CompletableFuture> futures = new ArrayList<>();
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(youdao.size());
        for (Youdao y:youdao){
//            CallableMap callableMap = new CallableMap(queue, y, importExcel);
            //Map<String,List<String>>  String 线程处理的语言,List<String>
            // 线程需要处理1-n个语言，处理数量不确定，但是最少是一种。
            CompletableFuture<Map<String,List<String>>> future = CompletableFuture.supplyAsync(() -> {
                TextTranslateTwo translateTool = new TextTranslateTwo(y);
                Map<String,List<String>> results = new HashMap<>();
                try {
//                    {lang:value},{tran: List<String>}
//                    处理语言，处理语言可能是多次的
                    while (!queue.isEmpty()){
                        //此时来到任务线程
//                        目标语言
                        String lan = queue.poll();
                        System.out.println(lan);
                        List<String> translateds = new ArrayList<>();
                        for (JobTranExcel q:importExcel){
                            String translated = translateTool.translate(q.getSrc(), "zh-CHS", lan);
                            translateds.add(translated);
                        }
                        results.put(lan,translateds);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return results;
            });
            futures.add(future);
        }
        CompletableFuture[] array = futures.stream().toArray(CompletableFuture[]::new);
        CompletableFuture<Void> waitResult = CompletableFuture.allOf(array);
        System.out.println("start wait");
        // 阻塞当前线程，直到所有的future完成
        waitResult.join();
        Map<Integer,List<String>> concordance = new HashMap<>();
        for (CompletableFuture cf : array){

            Map<String, List<String>> map = (Map<String, List<String>>) cf.get();
            int x = 0;
            for (String lan:languages){
                List<String> data = map.get(lan);
                if (!ObjectUtil.isNull(data)){
                    concordance.put(new Integer(x),data);
                }
                x++;
            }
        }
//        --------------------------------多线程的翻译结果进行组装
        for (int x= 0; x<concordance.size();x++){
            List<String> targets = concordance.get(x);

            for (int y = 0;y<targets.size();y++){
                String target = targets.get(y);
                Map<Integer, String> r = rows.get(y+1);
                r.put(x+1,target);
            }
        }
        //此刻翻译完所有原文，输出结果
        String salt = RandomUtil.randomString(5);
        String target = directory+salt+fileName;
        FileOutputStream outputStream = new FileOutputStream(new File(target));
        EasyExcelFactory.write(outputStream).sheet("xxxxxx").doWrite(rows);

    }

}
//class CallableMap implements Callable<List<String>>{
//    private ConcurrentLinkedQueue<String> queueLanguages;
//    private Youdao youdao;
//    private List<TranExcel> src;
//
//    public CallableMap(
//        ConcurrentLinkedQueue<String> queueLanguages,
//        Youdao youdao,
//        List<TranExcel> src) {
//        this.queueLanguages = queueLanguages;
//        this.youdao = youdao;
//        this.src =src;
//    }
//    @Override
//    public List<String> call() throws Exception {
//        //假设每次从队列集合获取到对象，如果有则继续翻译，要是没有就不
//        try {
//            while (!queueLanguages.isEmpty()){
//                String language = queueLanguages.poll();
//
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
//class Excel
