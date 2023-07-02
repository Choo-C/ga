package vip.wexiang.job.txt.local;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcelFactory;
import vip.wexiang.common.utils.poi.ExcelUtil;
import vip.wexiang.job.translate.impl.TextTranslate;
import vip.wexiang.job.txt.domain.TranExcel;
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

public class ExcelArray {

    public static List<Youdao> getYoudao() {
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

    private static List<String> removeLanguaNotMatch(String lang) {
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
        languages = languages.stream().filter(youdaoSupport::contains).
            collect(Collectors.toList());
        return languages;
    }
    private static void checkyoudaoAll(List<Youdao> youdaos)throws Exception{
        for (Iterator<Youdao> car = youdaos.iterator();car.hasNext();){
            Youdao you = car.next();
            TextTranslate translate = new TextTranslate(you);
            boolean valid = translate.checkYoudao();
            if(!valid){
                car.remove();
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        ,vi,ms,pl,ar,it,ar,ta,sv,fi,id,th
//,ko,hi,fr,de,vi,ms,pl,ar,it,ar,ta,sv,fi,id,th
        String lang = "zh-CHT,en,ja";
        List<String> languages = removeLanguaNotMatch(lang);

        // 创建一个线程安全的队列，当多线程操作时，可以使用此队列。安全的获取到目标语言，根据语言执行语言指令和/或替换标准词库中的单词。
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(languages);

        String directory = "Y:\\excel\\";
        String fileName = "src.xlsx";
        String path = directory + fileName;
        System.out.println(languages);
        FileInputStream inputStream = new FileInputStream(new File(path));
//        获取到了整个excel，但目前只是拿到了原文

        List<TranExcel> importExcel = ExcelUtil.importExcel(inputStream, TranExcel.class);

        StringBuilder log = new StringBuilder("");

        int rowsrc = 0;
        for (Iterator<TranExcel> car = importExcel.iterator(); car.hasNext(); ) {

            TranExcel excel = car.next();
            System.out.println(">>>>>>>>>>>>>>>" + excel.getSrc());
            if (excel.getSrc() == null || excel.getSrc().equals("") || excel.getSrc().replace(" ", "").equals("")) {
                log.append("原文：");
                log.append(rowsrc + 3);
                log.append("行出错;  ");
            }
            rowsrc++;
        }
        importExcel.removeIf(item -> {
            if (item.getSrc() == null || item.getSrc().equals("") || item.getSrc().replace(" ", "").equals("")) {
                return true;
            }
            return false;
        });
        List<Map<Integer, String>> rows = new ArrayList<>();
        //每列的语言是什么，以代号形式显示
        //每列的语言是什么。
        Map<Integer, String> head = new HashMap<>();
        //第一列为原文
        head.put(0, "src");
        for (int x = 0; x < languages.size(); x++) {
            //跳过第一列，第一列已经被原文占领了。
            head.put(x + 1, languages.get(x));
        }
        rows.add(head);
//        写好rows的第一列原文
        for (int x = 0; x < importExcel.size(); x++) {
            TranExcel excel = importExcel.get(x);
            Map<Integer, String> map = new HashMap<>();
            map.put(0, excel.getSrc());
            rows.add(map);
        }
        List<StringBuilder> srcs = new ArrayList<>();
        StringBuilder src = new StringBuilder("");
        System.out.println(importExcel.size());
        for (TranExcel excel : importExcel) {
            if (src.toString().length() > 1200) {
                srcs.add(src);
                src = new StringBuilder("");
            }
            System.out.println(excel.getSrc());
            src.append(excel.getSrc());
            src.append("\n");
        }
        if (src.toString().length() > 0) {
            srcs.add(src);
        }
//        Map<String, List<String>> results = new HashMap<>();
        //        每次翻译只翻译一种语言。如果有多余api或者是线程，剩余的线程不会执行。
        //线程数量依据youdaoid有多少而决定的
        List<Youdao> youdao = getYoudao();
//        清空无效原文 null or ”“ or "   "
        //        原文 翻译语言 youdaoid 都不能为0，否则解决翻译。
        if (importExcel.size() <= 0 && languages.size() <= 0 && youdao.size() <= 0) {
            System.out.println("");
            return;
        }
        //        每次翻译只翻译一种语言。如果有多余api或者是线程，剩余的线程不会执行。
        if (languages.size() < youdao.size()) {
            youdao = ListUtil.sub(youdao, 0, languages.size());
        }
        //剔除不能用的有道api
        checkyoudaoAll(youdao);
//        //        检查有道api是否有效，无效则剔除。有概率会全部清除所以得再确认。
        if (youdao.size() <= 0){
            System.out.println("");
            return;
        }
        //执行了多线程得到了，多个List<String> 三个语言 10句话翻译
        //        --------------------------------多线程组装
//        需要 src、lang、youdao key
        List<CompletableFuture> futures = new ArrayList<>();
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(youdao.size());
        for (Youdao y : youdao) {
            //Map<String,List<String>>  String 线程处理的语言,List<String>
            // 线程需要处理1-n个语言，处理数量不确定，但是最少是一种。
            CompletableFuture<Map<String, List<String>>> future = CompletableFuture.supplyAsync(() -> {
                TextTranslate translateTool = new TextTranslate(y);
//                处理过的语言
                Map<String, List<String>> results = new HashMap<>();
                try {
//                    处理语言，处理语言可能是多次的，多种的
                    while (!queue.isEmpty()) {
                        //此时来到任务线程
//                        目标语言
                        String lan = queue.poll();
                        ArrayList<String> translatedList = new ArrayList<>();
                        //多个拼接换行的原文
                        for (StringBuilder sb : srcs) {
//                          处理一个拼接的原文
                            String translated = translateTool.translate(sb.toString(), "zh-CHS", lan);
//                            String translated = "<youdao>401</youdao>";test 如果有道欠费会停止止翻英文
                            if ("<youdao>401</youdao>".equals(translated)) {
                                //如果出现错误，则跳过该语言
                                log.append("<youdao>401</youdao>");
                                log.append("这个有道欠费了，请充值，再使用！");
                                log.append(y.toString());
                                log.append("<~~>");
                                break;
                            }
                            String[] split = translated.split("\n");
                            List<String> someList = Arrays.asList(split);
                            translatedList.addAll(someList);
                        }
                        results.put(lan, translatedList);

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
        //集合
        Map<Integer, List<String>> concordance = new HashMap<>();
        for (CompletableFuture cf : array) {

            Map<String, List<String>> map = (Map<String, List<String>>) cf.get();
            int x = 0;
            for (String lan : languages) {
//                线程处理过的语言，根据语言名字，获得数据，并且放进对应位置  concordance
                List<String> data = map.get(lan);
                if (!ObjectUtil.isNull(data)) {
                    concordance.put(new Integer(x), data);
                }
                x++;
            }
        }
        int i = 0;
        //        --------------------------------多线程的翻译结果进行组装
        for (int x = 0; x < concordance.size(); x++) {
            List<String> targets = concordance.get(x);

            for (int y = 0; y < targets.size(); y++) {
                String target = targets.get(y);
                Map<Integer, String> r = rows.get(y + 1);
                r.put(x + 1, target);
            }
        }
        Map<Integer, String> endLog = new HashMap<>();
        if (log.toString().equals("")) {
            endLog.put(0, "翻译成功，无任何问题");
        }else if(log.toString().contains("<youdao>401</youdao>")){
            List<String> list = Arrays.asList(log.toString().split("<~~>"));
            //下标的遍历
            for (int i1 = 0; i1 < list.size(); i1++) {
                String line = list.get(i1);
                endLog.put(i1, "error: " + line);
            }
        } else {
            endLog.put(0, "error:      出错的位置可能对不上，如果是某一行没数据可能会影响到错处的坐标位置    仅供参考：" + log.toString());
        }
        rows.add(endLog);
        //此刻翻译完所有原文，输出结果
        String salt = RandomUtil.randomString(5);
        String target = directory + salt + fileName;
        FileOutputStream outputStream = new FileOutputStream(new File(target));
        EasyExcelFactory.write(outputStream).sheet("xxxxxx").doWrite(rows);
    }
}

