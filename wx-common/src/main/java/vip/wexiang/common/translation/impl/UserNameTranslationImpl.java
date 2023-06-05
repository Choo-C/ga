package vip.wexiang.common.translation.impl;

import vip.wexiang.common.annotation.TranslationType;
import vip.wexiang.common.constant.TransConstant;
import vip.wexiang.common.core.service.UserService;
import vip.wexiang.common.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户名翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.USER_ID_TO_NAME)
public class UserNameTranslationImpl implements TranslationInterface<String> {

    private final UserService userService;

    public String translation(Object key, String other) {
        if (key instanceof Long) {
            return userService.selectUserNameById((Long) key);
        }
        return null;
    }
}
