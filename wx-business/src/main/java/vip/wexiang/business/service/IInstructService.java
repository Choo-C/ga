package vip.wexiang.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.wexiang.business.domain.Instruct;
import vip.wexiang.business.domain.vo.InstructVo;
import vip.wexiang.business.domain.bo.InstructBo;
import vip.wexiang.common.core.page.TableDataInfo;
import vip.wexiang.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 指令Service接口
 * Created by admin.
 */
public interface IInstructService extends IService<Instruct> {

    /**
     * 查询指令
     */
    InstructVo queryById(String id);

    /**
     * 查询指令列表
     */
    TableDataInfo<InstructVo> queryPageList(InstructBo bo, PageQuery pageQuery);

    /**
     * 查询指令列表
     */
    List<InstructVo> queryList(InstructBo bo);

    /**
     * 新增指令
     */
    Boolean insertByBo(InstructBo bo);

    /**
     * 修改指令
     */
    Boolean updateByBo(InstructBo bo);

    /**
     * 校验并批量删除指令信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);

}
