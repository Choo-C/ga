package vip.wexiang.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import vip.wexiang.common.annotation.ExcelDictFormat;
import vip.wexiang.common.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 演示视图对象 t_demo
 * Created by admin.
 */
@Data
@ExcelIgnoreUnannotated
public class DemoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String name;

}
