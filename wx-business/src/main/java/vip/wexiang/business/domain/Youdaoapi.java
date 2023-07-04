package vip.wexiang.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.wexiang.common.core.domain.BaseEntity;

/**
 * 有道翻译对象 youdaoapi
 * Created by admin.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("youdaoapi")
public class Youdaoapi extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * key
     */
    @TableId(value = "youdao_key")
    private String youdaoKey;
    /**
     * 密钥
     */
    private String youdaoSecret;
    /**
     * 链接的api接口
     */
    private String url;

}
