package vip.wexiang.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.wexiang.business.domain.Youdaoapi;
import vip.wexiang.business.domain.vo.YoudaoapiVo;
import vip.wexiang.business.domain.bo.YoudaoapiBo;
import vip.wexiang.common.core.page.TableDataInfo;
import vip.wexiang.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 有道翻译Service接口
 * Created by admin.
 */
public interface IYoudaoapiService extends IService<Youdaoapi> {

    /**
     * 查询有道翻译
     */
    YoudaoapiVo queryById(String youdaoKey);

    /**
     * 查询有道翻译列表
     */
    TableDataInfo<YoudaoapiVo> queryPageList(YoudaoapiBo bo, PageQuery pageQuery);

    /**
     * 查询有道翻译列表
     */
    List<YoudaoapiVo> queryList(YoudaoapiBo bo);

    /**
     * 新增有道翻译
     */
    Boolean insertByBo(YoudaoapiBo bo);

    /**
     * 修改有道翻译
     */
    Boolean updateByBo(YoudaoapiBo bo);

    /**
     * 校验并批量删除有道翻译信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);

}
