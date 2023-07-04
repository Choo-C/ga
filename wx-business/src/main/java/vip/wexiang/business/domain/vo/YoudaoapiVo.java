package vip.wexiang.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import vip.wexiang.common.annotation.ExcelDictFormat;
import vip.wexiang.common.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 有道翻译视图对象 youdaoapi
 * Created by admin.
 */
@Data
@ExcelIgnoreUnannotated
public class YoudaoapiVo {

    private static final long serialVersionUID = 1L;

    /**
     * key
     */
    @ExcelProperty(value = "key")
    private String youdaoKey;

    /**
     * 密钥
     */
    @ExcelProperty(value = "密钥")
    private String youdaoSecret;

    /**
     * 链接的api接口
     */
    @ExcelProperty(value = "链接的api接口")
    private String url;

}
