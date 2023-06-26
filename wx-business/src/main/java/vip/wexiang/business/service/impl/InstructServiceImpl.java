package vip.wexiang.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.wexiang.common.core.page.TableDataInfo;
import vip.wexiang.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.wexiang.business.domain.bo.InstructBo;
import vip.wexiang.business.domain.vo.InstructVo;
import vip.wexiang.business.domain.Instruct;
import vip.wexiang.business.mapper.InstructMapper;
import vip.wexiang.business.service.IInstructService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 指令Service业务层处理
 * Created by admin.
 */
@RequiredArgsConstructor
@Service
public class InstructServiceImpl extends ServiceImpl<InstructMapper, Instruct> implements IInstructService {

    /**
     * 查询指令
     */
    @Override
    public InstructVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询指令列表
     */
    @Override
    public TableDataInfo<InstructVo> queryPageList(InstructBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Instruct> lqw = buildQueryWrapper(bo);
        Page<InstructVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询指令列表
     */
    @Override
    public List<InstructVo> queryList(InstructBo bo) {
        LambdaQueryWrapper<Instruct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Instruct> buildQueryWrapper(InstructBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Instruct> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getInstruct()), Instruct::getInstruct, bo.getInstruct());
        lqw.eq(StringUtils.isNotBlank(bo.getExpectant()), Instruct::getExpectant, bo.getExpectant());
        lqw.eq(StringUtils.isNotBlank(bo.getInstructSet()), Instruct::getInstructSet, bo.getInstructSet());
        return lqw;
    }

    /**
     * 新增指令
     */
    @Override
    public Boolean insertByBo(InstructBo bo) {
        Instruct add = BeanUtil.toBean(bo, Instruct.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改指令
     */
    @Override
    public Boolean updateByBo(InstructBo bo) {
        Instruct update = BeanUtil.toBean(bo, Instruct.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Instruct entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除指令
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}
