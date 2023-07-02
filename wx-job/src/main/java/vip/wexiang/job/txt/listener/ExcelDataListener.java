package vip.wexiang.job.txt.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.github.therapi.runtimejavadoc.repack.com.eclipsesource.json.JsonObject;
import io.swagger.v3.core.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vip.wexiang.job.translate.TranslateFile;
import vip.wexiang.job.translate.impl.TextTranslate;
import vip.wexiang.job.txt.domain.TranExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ExcelDataListener implements ReadListener<TranExcel> {

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
    public void invoke(TranExcel tranExcel, AnalysisContext analysisContext) {
        TranslateFile<String> translateFile = new TextTranslate();
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
