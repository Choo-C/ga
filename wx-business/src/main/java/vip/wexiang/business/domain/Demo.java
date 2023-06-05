package vip.wexiang.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.wexiang.common.core.domain.BaseEntity;

/**
 * 演示对象 t_demo
 * Created by admin.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_demo")
public class Demo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
