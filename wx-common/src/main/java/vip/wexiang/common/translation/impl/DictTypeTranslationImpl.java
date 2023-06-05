package vip.wexiang.common.translation.impl;

import vip.wexiang.common.annotation.TranslationType;
import vip.wexiang.common.constant.TransConstant;
import vip.wexiang.common.core.service.DictService;
import vip.wexiang.common.translation.TranslationInterface;
import vip.wexiang.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 字典翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTypeTranslationImpl implements TranslationInterface<String> {

    private final DictService dictService;

    public String translation(Object key, String other) {
        if (key instanceof String && StringUtils.isNotBlank(other)) {
            return dictService.getDictLabel(other, key.toString());
        }
        return null;
    }
}
