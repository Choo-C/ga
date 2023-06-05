package vip.wexiang.business.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.wexiang.common.core.domain.BaseEntity;
import vip.wexiang.common.core.validate.AddGroup;
import vip.wexiang.common.core.validate.EditGroup;

import javax.validation.constraints.*;

/**
 * 演示业务对象 t_demo
 * Created by admin.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DemoBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

}
