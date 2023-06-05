package vip.wexiang.common.translation.impl;

import vip.wexiang.common.annotation.TranslationType;
import vip.wexiang.common.constant.TransConstant;
import vip.wexiang.common.core.service.DeptService;
import vip.wexiang.common.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DEPT_ID_TO_NAME)
public class DeptNameTranslationImpl implements TranslationInterface<String> {

    private final DeptService deptService;

    public String translation(Object key, String other) {
        return deptService.selectDeptNameByIds(key.toString());
    }
}
