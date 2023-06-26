package vip.wexiang.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import vip.wexiang.common.annotation.ExcelDictFormat;
import vip.wexiang.common.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 指令视图对象 t_instruct
 * Created by admin.
 */
@Data
@ExcelIgnoreUnannotated
public class InstructVo {

    private static final long serialVersionUID = 1L;

    /**
     * 指令id
     */
    @ExcelProperty(value = "指令id")
    private String id;

    /**
     * 指令
     */
    @ExcelProperty(value = "指令")
    private String instruct;

    /**
     * 预取的结果
     */
    @ExcelProperty(value = "预取的结果")
    private String expectant;

    /**
     * 指令集合id
     */
    @ExcelProperty(value = "指令集合id")
    private String instructSet;

}
