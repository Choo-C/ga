package vip.wexiang.common.translation.impl;

import vip.wexiang.common.annotation.TranslationType;
import vip.wexiang.common.constant.TransConstant;
import vip.wexiang.common.core.service.OssService;
import vip.wexiang.common.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * OSS翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.OSS_ID_TO_URL)
public class OssUrlTranslationImpl implements TranslationInterface<String> {

    private final OssService ossService;

    public String translation(Object key, String other) {
        return ossService.selectUrlByIds(key.toString());
    }
}
