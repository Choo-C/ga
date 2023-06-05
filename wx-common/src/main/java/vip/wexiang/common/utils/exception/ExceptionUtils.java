package vip.wexiang.common.utils.exception;

import vip.wexiang.common.exception.ServiceException;

/**
 * 抛异常工具类
 * Created by wu.
 */
public class ExceptionUtils {

    public static ThrowExceptionFunction isTrue(boolean flag) {
        return (message -> {
            if (flag) {
                throw new ServiceException(message);
            }
        });
    }

}
