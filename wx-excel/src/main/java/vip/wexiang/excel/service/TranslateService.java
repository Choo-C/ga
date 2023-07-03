package vip.wexiang.excel.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TranslateService {
    List<Map<Integer, String>> translateByMF(MultipartFile file,String lang)throws Exception;
}
