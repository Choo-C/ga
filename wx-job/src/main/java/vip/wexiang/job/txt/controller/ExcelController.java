package vip.wexiang.job.txt.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vip.wexiang.common.core.domain.R;
import vip.wexiang.job.translate.TranslateFile;
import vip.wexiang.job.txt.domain.TranExcel;
import vip.wexiang.job.txt.listener.ExcelDataListener;
import vip.wexiang.job.txt.properties.LanguageProperties;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/excel")
@RestController
public class ExcelController {



    @PostMapping("/getExcel")
    public R<String> getExcel(
        @RequestPart("file")
        MultipartFile file,
        HttpServletResponse response) throws IOException {
        log.info("file name: {}", file.getOriginalFilename());

//        EasyExcel.read(file.getInputStream(), TranExcel.class, new ExcelDataListener()).sheet().doRead();
        ExcelDataListener listener = new ExcelDataListener();
        EasyExcel.read(file.getInputStream(), TranExcel.class, listener).sheet("翻译表").doRead();
        List<String> modifiedDataList = listener.getData();
        for (String d : modifiedDataList){
            System.out.println(d);
        }
// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
//        EasyExcel.write(response.getOutputStream(), TranExcel.class).sheet("").doWrite(modifiedDataList);


        return R.ok(file.getOriginalFilename());
    }
}
