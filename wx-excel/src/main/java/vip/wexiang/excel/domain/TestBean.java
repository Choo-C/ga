package vip.wexiang.excel.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TestBean {
    @NotNull(message = "文件不可为空!")
    @ApiModelProperty("姓名")
    private String name;
    @NotNull(message = "文件不可为空!")
    @Max(100)
    @Min(1)
    @ApiModelProperty("姓名")
    private Integer age;

}
