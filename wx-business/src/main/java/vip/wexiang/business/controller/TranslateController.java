package vip.wexiang.business.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vip.wexiang.business.service.TranslateService;


import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/translate")
@Slf4j
@Validated
@RequiredArgsConstructor
public class TranslateController {
    @Qualifier(value = "translateServiceImpl")
    @Autowired
    private TranslateService translateService;

//    @Autowired
//    private HttpServletResponse response;

    @PostMapping(value = "/excelFile")
    public void upExcelFile(
        HttpServletResponse response,
        @NotNull(message="xxxxxxxxxx")
        @RequestParam("file")
        MultipartFile file,
        @NotNull(message = "xxxxxxxxxx")
        @RequestParam("lang")
        String lang
        ) throws Exception{
        List<Map<Integer, String>> maps = translateService.translateByMF(file, lang);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Map.class).sheet("模板").doWrite(maps);
    }

//    @PostMapping(value = "/excelFile")
//    public void upExcelFile(
//        HttpServletResponse response,
//        @NotNull(message="xxxxxxxxxx")
//        @RequestParam("file")
//        MultipartFile file,
////        @Validated
////        @ModelAttribute("")
////        TestBean testBean,
////        BindingResult result
////         An Errors/BindingResult argument is expected to be declared immediately after the model attribute, the @RequestBody or the @RequestPart arguments to which they apply: public java.lang.String vip.wexiang.excel.controller.TranslateController.upExcelFile(org.springframework.web.multipart.MultipartFile,java.lang.String,org.springframework.validation.BindingResult
//        @NotNull(message = "xxxxxxxxxx")
//        @RequestParam("lang")
//        String lang
//    ) throws Exception{
//
////        if (result.hasErrors()){
////            List<ObjectError> errors = result.getAllErrors();
////            for (ObjectError error : errors){
////                System.out.println(error.toString());
////            }
////            return "error";
////        }
//        List<Map<Integer, String>> maps = translateService.translateByMF(file, lang);
//
//        //此刻翻译完所有原文，输出结果
//        String salt = RandomUtil.randomString(5);
//        log.error(file.getSize() + "<<<<<<<<<<<<<<<<<<<<<");
//        log.info("start");
//
//        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setCharacterEncoding("utf-8");
//        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
//        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
//        EasyExcel.write(response.getOutputStream(), Map.class).sheet("模板").doWrite(maps);
//
//    }
    @PostMapping("maven")
    public String maven(){
        return "maven";
    }

}
