package vip.wexiang.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.wexiang.business.domain.Demo;
import vip.wexiang.business.domain.vo.DemoVo;
import vip.wexiang.business.domain.bo.DemoBo;
import vip.wexiang.common.core.page.TableDataInfo;
import vip.wexiang.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 演示Service接口
 * Created by admin.
 */
public interface IDemoService extends IService<Demo> {

    /**
     * 查询演示
     */
    DemoVo queryById(Long id);

    /**
     * 查询演示列表
     */
    TableDataInfo<DemoVo> queryPageList(DemoBo bo, PageQuery pageQuery);

    /**
     * 查询演示列表
     */
    List<DemoVo> queryList(DemoBo bo);

    /**
     * 新增演示
     */
    Boolean insertByBo(DemoBo bo);

    /**
     * 修改演示
     */
    Boolean updateByBo(DemoBo bo);

    /**
     * 校验并批量删除演示信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
