package vip.wexiang.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.wexiang.common.core.domain.BaseEntity;

/**
 * 指令对象 t_instruct
 * Created by admin.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_instruct")
public class Instruct extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 指令id
     */
    @TableId(value = "id")
    private String id;
    /**
     * 指令
     */
    private String instruct;
    /**
     * 预取的结果
     */
    private String expectant;
    /**
     * 指令集合id
     */
    private String instructSet;

}
