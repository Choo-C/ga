package vip.wexiang.business.mapper;

import org.springframework.stereotype.Repository;

import vip.wexiang.business.domain.Youdaoapi;
import vip.wexiang.business.domain.vo.YoudaoapiVo;
import vip.wexiang.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 有道翻译Mapper接口
 * Created by admin.
 */
@Repository(value = "youdaoapiMapper")
public interface YoudaoapiMapper extends BaseMapperPlus<YoudaoapiMapper, Youdaoapi, YoudaoapiVo> {

    List<Youdaoapi> getAllYoudaoapi();
    int insertYoudao(Youdaoapi youdaoapi);

    int removeOne(Youdaoapi remove);
}
