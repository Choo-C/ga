package vip.wexiang.business.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import vip.wexiang.common.annotation.RepeatSubmit;
import vip.wexiang.common.annotation.Log;
import vip.wexiang.common.core.controller.BaseController;
import vip.wexiang.common.core.domain.PageQuery;
import vip.wexiang.common.core.domain.R;
import vip.wexiang.common.core.validate.AddGroup;
import vip.wexiang.common.core.validate.EditGroup;
import vip.wexiang.common.enums.BusinessType;
import vip.wexiang.common.utils.poi.ExcelUtil;
import vip.wexiang.business.domain.vo.YoudaoapiVo;
import vip.wexiang.business.domain.bo.YoudaoapiBo;
import vip.wexiang.business.service.IYoudaoapiService;
import vip.wexiang.common.core.page.TableDataInfo;

/**
 * 有道翻译
 * Created by admin.
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/youdaoapi")
public class YoudaoapiController extends BaseController {

    private final IYoudaoapiService iYoudaoapiService;

    /**
     * 查询有道翻译列表
     */
    @SaCheckPermission("business:youdaoapi:list")
    @GetMapping("/list")
    public TableDataInfo<YoudaoapiVo> list(YoudaoapiBo bo, PageQuery pageQuery) {
        return iYoudaoapiService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出有道翻译列表
     */
    @SaCheckPermission("business:youdaoapi:export")
    @Log(title = "有道翻译", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(YoudaoapiBo bo, HttpServletResponse response) {
        List<YoudaoapiVo> list = iYoudaoapiService.queryList(bo);
        ExcelUtil.exportExcel(list, "有道翻译", YoudaoapiVo.class, response);
    }

    /**
     * 获取有道翻译详细信息
     *
     * @param youdaoKey 主键
     */
    @SaCheckPermission("business:youdaoapi:query")
    @GetMapping("/{youdaoKey}")
    public R<YoudaoapiVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String youdaoKey) {
        return R.ok(iYoudaoapiService.queryById(youdaoKey));
    }

    /**
     * 新增有道翻译
     */
    @SaCheckPermission("business:youdaoapi:add")
    @Log(title = "有道翻译", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody YoudaoapiBo bo) {
        return toAjax(iYoudaoapiService.insertByBo(bo));
    }

    /**
     * 修改有道翻译
     */
    @SaCheckPermission("business:youdaoapi:edit")
    @Log(title = "有道翻译", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody YoudaoapiBo bo) {
        return toAjax(iYoudaoapiService.updateByBo(bo));
    }

    /**
     * 删除有道翻译
     *
     * @param youdaoKeys 主键串
     */
    @SaCheckPermission("business:youdaoapi:remove")
    @Log(title = "有道翻译", businessType = BusinessType.DELETE)
    @DeleteMapping("/{youdaoKeys}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] youdaoKeys) {
        return toAjax(iYoudaoapiService.deleteWithValidByIds(Arrays.asList(youdaoKeys), true));
    }

}
