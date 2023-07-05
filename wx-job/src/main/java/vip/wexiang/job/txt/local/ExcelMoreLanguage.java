package vip.wexiang.job.txt.local;

import lombok.extern.slf4j.Slf4j;
import vip.wexiang.common.utils.poi.ExcelUtil;
import vip.wexiang.job.translate.impl.TextTranslateTwo;
import vip.wexiang.job.txt.domain.JobTranExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
@Slf4j
public class ExcelMoreLanguage {
    public static void main(String[] args) {
        String directory = "C:\\excel\\";
        String fileName = "src.xlsx";
        String path = directory+fileName;
        //所有的语言
        //    新增语言，请在后续自行添加。并且对应好属性
        String lang = "en,ja,de,pt,ko,hi,fr,vi,ms,pl,sv,ar,ta,it,fi,id,th";
        List<String> languages = Arrays.asList(lang.split(","));
//        固定的url和key miyao
        TextTranslateTwo translateTool = new TextTranslateTwo();
        try{
            //获取到了Excel文件对象
            FileInputStream inputStream = new FileInputStream(new File(path));
//        获取到了整个excel，但目前只是拿到了原文
            List<JobTranExcel> rows = ExcelUtil.importExcel(inputStream, JobTranExcel.class);
            for (JobTranExcel row : rows){
                //拿到了一行，根据原文，逐一翻译对应单词或者句子，将翻译结果set回对应语言。
                log.info(row.toString());
//                一行原文
                String src = row.getSrc();
                for (String language :languages){
                    String translation = translateTool.translate(src,"zh-CHS",language);
                    String setterName = "set" + language.substring(0, 1).toUpperCase() + language.substring(1);
                    Method setterMethod = JobTranExcel.class.getMethod(setterName, String.class);
                    setterMethod.invoke(row,translation);
                }
                //此刻翻译完一行，每种语言都翻译了
            }
            //此刻翻译完所有原文
//            String salt = RandomUtil.randomString(5);
            FileOutputStream outputStream = new FileOutputStream(new File(directory+"301-600"+fileName));
            ExcelUtil.exportExcel(rows,"翻译表", JobTranExcel.class,outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
