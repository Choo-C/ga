package vip.wexiang.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.wexiang.business.service.TranslateService;
import vip.wexiang.business.translate.TranslateFile;
import vip.wexiang.business.translate.TranslateUtils;
import vip.wexiang.common.core.page.TableDataInfo;
import vip.wexiang.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.wexiang.business.domain.bo.YoudaoapiBo;
import vip.wexiang.business.domain.vo.YoudaoapiVo;
import vip.wexiang.business.domain.Youdaoapi;
import vip.wexiang.business.mapper.YoudaoapiMapper;
import vip.wexiang.business.service.IYoudaoapiService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 有道翻译Service业务层处理
 * Created by admin.
 */
@RequiredArgsConstructor
@Service
public class YoudaoapiServiceImpl extends ServiceImpl<YoudaoapiMapper, Youdaoapi> implements IYoudaoapiService {
//    private final TranslateService translateServiceImpl;
//    private final TranslateFile translateFile;
    @Resource
    private YoudaoapiMapper youdaoapiMapper;

    @Override
    public List<Youdaoapi> getAllYoudao() {
        return youdaoapiMapper.getAllYoudaoapi();
    }

    @Override
    public boolean detectionYoudaoapi(Youdaoapi youdaoapi) {

        return false;
    }

    /**
     * 查询有道翻译
     */
    @Override
    public YoudaoapiVo queryById(String youdaoKey) {
        return baseMapper.selectVoById(youdaoKey);
    }

    /**
     * 查询有道翻译列表
     */
    @Override
    public TableDataInfo<YoudaoapiVo> queryPageList(YoudaoapiBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Youdaoapi> lqw = buildQueryWrapper(bo);
        Page<YoudaoapiVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询有道翻译列表
     */
    @Override
    public List<YoudaoapiVo> queryList(YoudaoapiBo bo) {
        LambdaQueryWrapper<Youdaoapi> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Youdaoapi> buildQueryWrapper(YoudaoapiBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Youdaoapi> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getYoudaoSecret()), Youdaoapi::getYoudaoSecret, bo.getYoudaoSecret());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), Youdaoapi::getUrl, bo.getUrl());
        return lqw;
    }

    /**
     * 新增有道翻译
     */
    @Override
    public Boolean insertByBo(YoudaoapiBo bo) {
        Youdaoapi add = BeanUtil.toBean(bo, Youdaoapi.class);
        validEntityBeforeSave(add);
        try {
            boolean b = TranslateUtils.detectionYoudao(add);
            if (!b){
                return b;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean flag = youdaoapiMapper.insertYoudao(add) > 0;
        if (flag) {
            bo.setYoudaoKey(add.getYoudaoKey());
        }
        return flag;
    }

    /**
     * 修改有道翻译
     */
    @Override
    public Boolean updateByBo(YoudaoapiBo bo) {
        Youdaoapi update = BeanUtil.toBean(bo, Youdaoapi.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Youdaoapi entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除有道翻译
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean deleteByBo(YoudaoapiBo bo) {
        Youdaoapi remove = BeanUtil.toBean(bo, Youdaoapi.class);
        validEntityBeforeSave(remove);

        boolean flag = youdaoapiMapper.removeOne(remove) > 0;
        if (flag) {
            bo.setYoudaoKey(remove.getYoudaoKey());
        }
        return flag;
    }
}
