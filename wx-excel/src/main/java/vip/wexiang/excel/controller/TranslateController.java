package vip.wexiang.excel.controller;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vip.wexiang.common.exception.ServiceException;

@RestController
@RequestMapping("/translate")
@Slf4j
@Validated
@RequiredArgsConstructor
public class TranslateController {
    @PostMapping(value = "/excelFile")
    public String upExcelFile(
        @RequestPart("file")
        MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            throw new ServiceException("上传文件不能为空");
        }
        log.error(file.getSize() + "<<<<<<<<<<<<<<<<<<<<<");
        log.info("start");
        return "start";

    }

}
