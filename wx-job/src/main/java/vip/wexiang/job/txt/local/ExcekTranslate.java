package vip.wexiang.job.txt.local;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Slf4j
public class ExcekTranslate {
    public static Map<String,String> languages = new LinkedHashMap<>();

    public static void main(String[] args) {
        putAllLanguage();
        try{
            String excelPath = "C:\\Users\\Choo\\Desktop\\XXX.xlsx";
            FileInputStream file = new FileInputStream(new File(excelPath));
//            log.error(new File(excelPath).length()+"");
            // 创建工作簿对象
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // 获取第一个工作表
            XSSFSheet sheet = workbook.getSheet("翻译表");
            Set<String> keyNames = languages.keySet();
            List<String> values = new ArrayList<>();
            values.addAll(languages.values());

            int x = 0;
            for (String keyName:keyNames){
                sheet.setColumnWidth(1+x,15*256);
                x++;
            }
            XSSFRow headRow = sheet.getRow(0);
            System.out.println(sheet.getLastRowNum()+1);
            //获取excel原文集
            int height = sheet.getLastRowNum()+1;
            List<String> srcs = new ArrayList<>();
            for (int y = 1;y<height;y++){
                srcs.add(sheet.getRow(y).getCell(0).getStringCellValue());
            }
            //一列一列进行翻译, 翻译从第二列开始 column index ： 1   翻译始终从第二行开始 row index ：1
            x = 0;
            for (String keyName:keyNames){
                log.error(keyName);
                log.info(values.get(x));

//                sheet.getRow();
                XSSFCell headCell = headRow.createCell(x + 1);
                headCell.setCellValue(keyName);
                String languageCode = values.get(x);
                log.error(languageCode);

                x++;
            }



//            XSSFCellStyle headStyle = headRow.getRowStyle();


            workbook.close();
            file.close();

        }catch (Exception e){
            e.printStackTrace();
        }



    }
    private static void putAllLanguage(){

        languages.put("中文繁体","zh-CHT");
        languages.put("英文","en");
        languages.put("日文","ja");
        languages.put("韩文","ko");

    }
}
