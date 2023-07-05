package vip.wexiang.business.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.wexiang.business.domain.TranExcel;
import vip.wexiang.business.domain.Youdaoapi;
import vip.wexiang.business.service.IYoudaoapiService;
import vip.wexiang.business.service.TranslateService;
import vip.wexiang.business.translate.TranslateUtils;
import vip.wexiang.business.translate.impl.TextTranslate;
import vip.wexiang.common.utils.StringUtils;
import vip.wexiang.common.utils.poi.ExcelUtil;


import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class TranslateServiceImpl implements TranslateService {

    @Resource
    private IYoudaoapiService youdaoapiService;
    @Override
    public List<Map<Integer, String>> translateByMF(MultipartFile file, String lang) throws Exception{
//        String lang = "zh-CHT,en,ja";
        List<String> languages = TranslateUtils.removeLanguaNotMatch(lang);

        // 创建一个线程安全的队列，当多线程操作时，可以使用此队列。安全的获取到目标语言，根据语言执行语言指令和/或替换标准词库中的单词。
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(languages);

        String directory = "C:\\excel\\";
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
                log.append(rowsrc + 1);
                log.append("行出错;  ");
                log.append("<~~>");
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

//        获得所有youdao的api
        List<Youdaoapi> youdao = youdaoapiService.getAllYoudao();
//        清空无效原文 null or ”“ or "   "
        //        原文 翻译语言 youdaoid 都不能为0，否则解决翻译。
        if (importExcel.size() <= 0 && languages.size() <= 0 && youdao.size() <= 0) {
            System.out.println("");
            return null;
        }
        //        每次翻译只翻译一种语言。如果有多余api或者是线程，剩余的线程不会执行。
        if (languages.size() < youdao.size()) {
            youdao = ListUtil.sub(youdao, 0, languages.size());
        }
        //剔除不能用的有道api
        TranslateUtils.checkyoudaoAll(youdao);
//        检查有道api是否有效，无效则剔除。有概率会全部清除所以得再确认。
        if (youdao.size() <= 0) {
            System.out.println("");
            return null;
        }
        //执行了多线程得到了，多个List<String> 三个语言 10句话翻译
        //        --------------------------------多线程组装
//        需要 src、lang、youdao key
        List<CompletableFuture> futures = new ArrayList<>();
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(youdao.size());
        for (Youdaoapi y : youdao) {
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
                            translated = " " + translated + " ";
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
//        第几列，哪种语言
        Map<Integer, List<String>> concordance = new HashMap<>();
        for (CompletableFuture cf : array) {
            //语言、翻译后内容
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

        //        --------------------------------多线程的翻译结果进行组装
        for (int x = 0; x < concordance.size(); x++) {
//            某一列的所有译文
            List<String> targets = concordance.get(x);

            for (int y = 0; y < targets.size(); y++) {
                String target = targets.get(y);
                Map<Integer, String> r =
//                  获取第几行的数据,因为第一行被占据了,得+1
                    rows.get(y + 1);
//                既然确认了哪一行了,只要确认处于哪一列,即可.第一列为译文,所以下标都要+1
                if (StringUtils.isEmpty(target.trim()) && x == 0) {
                    r.put(x + 1, "请输入符合中国言论规范,否则无法翻译");
                    log.append(y + 1);
                    log.append("行;请输入符合中国言论规范,否则无法翻译");
                    log.append("<~~>");

                } else {
                    r.put(x + 1, target);
                }

            }
        }
        Map<Integer, String> endLog = new HashMap<>();
        if (log.toString().equals("")) {
            endLog.put(0, "翻译成功，无任何问题");
        } else if (log.toString().contains("<youdao>401</youdao>")) {
            List<String> list = Arrays.asList(log.toString().split("<~~>"));
            //下标的遍历
            for (int i1 = 0; i1 < list.size(); i1++) {
                String line = list.get(i1);
                endLog.put(i1, line);
            }
        } else {
            List<String> list = Arrays.asList(log.toString().split("<~~>"));
            //下标的遍历
            endLog.put(0, "error:      出错的位置对不上，是某一行没数据可能没有数据或者空格会影响到出错处的坐标位置    仅供参考：");
            for (int i1 = 0; i1 < list.size(); i1++) {
                String line = list.get(i1);
                endLog.put(i1 + 1, line);
            }

        }
        rows.add(endLog);



        return rows;
    }
}
