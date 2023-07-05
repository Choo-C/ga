package vip.wexiang.job.txt.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import vip.wexiang.job.translate.TranslateTwoFile;
import vip.wexiang.job.translate.impl.TextTranslateTwo;
import vip.wexiang.job.txt.domain.JobTranExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ExcelDataListener implements ReadListener<JobTranExcel> {

    private String toLanguage = "en";
    private  List<String> data = new ArrayList<>();
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        String value = new JSONObject(headMap.get(1)).getString("stringValue");
        if (value.split(":").length != 2) {
            return;
        }
        toLanguage = value.substring(value.indexOf(":") + 1, value.length());

    }

    @Override
    public void invoke(JobTranExcel tranExcel, AnalysisContext analysisContext) {
        TranslateTwoFile<String> translateFile = new TextTranslateTwo();
        try {
            String translated = translateFile.translate(tranExcel.getSrc(), "中文", toLanguage);
//            tranExcel.setTarget(translated);
            data.add(translated);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    public List<String> getData(){
        return data;
    }
}
