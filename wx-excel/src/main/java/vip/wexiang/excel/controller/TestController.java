package vip.wexiang.excel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.wexiang.excel.domain.TestBean;
//import org.springframework.mock.web.MockMultipartFile;
import java.util.Base64;

@RestController
@RequestMapping("/test")
@Slf4j
@Validated
@RequiredArgsConstructor
public class TestController {
    @RequestMapping(value = "/test")
    public String test(
        @RequestPart(value = "file")
        MultipartFile file,
        @RequestParam(value = "bean")
        TestBean bean
    ) {
        System.out.println(file.getOriginalFilename());
        System.out.println(bean.getName());
        System.out.println(bean.getAge());
        return "";
    }


}
