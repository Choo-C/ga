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
import vip.wexiang.business.domain.bo.DemoBo;
import vip.wexiang.business.domain.vo.DemoVo;
import vip.wexiang.business.domain.Demo;
import vip.wexiang.business.mapper.DemoMapper;
import vip.wexiang.business.service.IDemoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 演示Service业务层处理
 * Created by admin.
 */
@RequiredArgsConstructor
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

    /**
     * 查询演示
     */
    @Override
    public DemoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询演示列表
     */
    @Override
    public TableDataInfo<DemoVo> queryPageList(DemoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Demo> lqw = buildQueryWrapper(bo);
        Page<DemoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询演示列表
     */
    @Override
    public List<DemoVo> queryList(DemoBo bo) {
        LambdaQueryWrapper<Demo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Demo> buildQueryWrapper(DemoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Demo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), Demo::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增演示
     */
    @Override
    public Boolean insertByBo(DemoBo bo) {
        Demo add = BeanUtil.toBean(bo, Demo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改演示
     */
    @Override
    public Boolean updateByBo(DemoBo bo) {
        Demo update = BeanUtil.toBean(bo, Demo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Demo entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除演示
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}
