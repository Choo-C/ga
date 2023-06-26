package vip.wexiang.business.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.wexiang.common.core.domain.BaseEntity;
import vip.wexiang.common.core.validate.AddGroup;
import vip.wexiang.common.core.validate.EditGroup;

import javax.validation.constraints.*;

/**
 * 指令业务对象 t_instruct
 * Created by admin.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InstructBo extends BaseEntity {

    /**
     * 指令id
     */
    @NotBlank(message = "指令id不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 指令
     */
    @NotBlank(message = "指令不能为空", groups = { AddGroup.class, EditGroup.class })
    private String instruct;

    /**
     * 预取的结果
     */
    @NotBlank(message = "预取的结果不能为空", groups = { AddGroup.class, EditGroup.class })
    private String expectant;

    /**
     * 指令集合id
     */
    @NotBlank(message = "指令集合id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String instructSet;

}
