package vip.wexiang.business.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.wexiang.common.core.domain.BaseEntity;
import vip.wexiang.common.core.validate.AddGroup;
import vip.wexiang.common.core.validate.EditGroup;

import javax.validation.constraints.*;

/**
 * 有道翻译业务对象 youdaoapi
 * Created by admin.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class YoudaoapiBo extends BaseEntity {

    /**
     * key
     */
    @NotBlank(message = "key不能为空", groups = { EditGroup.class })
    private String youdaoKey;

    /**
     * 密钥
     */
    @NotBlank(message = "密钥不能为空", groups = { AddGroup.class, EditGroup.class })
    private String youdaoSecret;

    /**
     * 链接的api接口
     */
    @NotBlank(message = "链接的api接口不能为空", groups = { AddGroup.class, EditGroup.class })
    private String url;

}
