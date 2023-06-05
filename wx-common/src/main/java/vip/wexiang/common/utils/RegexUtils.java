package vip.wexiang.common.utils;

import cn.hutool.core.util.ReUtil;

/**
 * 正则工具类
 * Created by wu.
 */
public class RegexUtils {

    /**
     * 是否手机号
     *
     * @param phone 手机号
     */
    public static boolean isPhone(String phone) {
        return ReUtil.isMatch("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$", phone);
    }
}
