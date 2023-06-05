package vip.wexiang.common.utils.exception;

/**
 * 抛异常接口
 * Created by wu.
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    /**
     * 抛出异常信息
     *
     * @param message 异常信息
     */
    void throwMessage(String message);

}
