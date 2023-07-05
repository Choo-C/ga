package vip.wexiang.job.txt.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class JobTranExcel {
    @ExcelProperty(index = 0)
    private String src;
    @ExcelProperty(index = 1)
    private String en;
    @ExcelProperty(index = 2)
    private String ja;
    @ExcelProperty(index = 3)
    private String de;
    @ExcelProperty(index = 4)
    private String pt;
    @ExcelProperty(index = 5)
    private String ko;
    @ExcelProperty(index = 6)
    private String hi;
    @ExcelProperty(index = 7)
    private String fr;
    @ExcelProperty(index = 8)
    private String vi;
    @ExcelProperty(index = 9)
    private String ms;
    @ExcelProperty(index = 10)
    private String pl;
    @ExcelProperty(index = 11)
    private String sv;
    @ExcelProperty(index = 12)
    private String ar;
    @ExcelProperty(index = 13)
    private String ta;
    @ExcelProperty(index = 14)
    private String it;
    @ExcelProperty(index = 15)
    private String fi;
    @ExcelProperty(index = 16)
    private String id;
    @ExcelProperty(index = 17)
    private String th;
//    新增语言，请在后续自行添加。
}
