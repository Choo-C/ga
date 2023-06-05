package vip.wexiang.web.controller.business;

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
import vip.wexiang.business.domain.vo.DemoVo;
import vip.wexiang.business.domain.bo.DemoBo;
import vip.wexiang.business.service.IDemoService;
import vip.wexiang.common.core.page.TableDataInfo;

/**
 * 演示
 * Created by admin.
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/demo")
public class DemoController extends BaseController {

    private final IDemoService iDemoService;

    /**
     * 查询演示列表
     */
    @SaCheckPermission("business:demo:list")
    @GetMapping("/list")
    public TableDataInfo<DemoVo> list(DemoBo bo, PageQuery pageQuery) {
        return iDemoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出演示列表
     */
    @SaCheckPermission("business:demo:export")
    @Log(title = "演示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DemoBo bo, HttpServletResponse response) {
        List<DemoVo> list = iDemoService.queryList(bo);
        ExcelUtil.exportExcel(list, "演示", DemoVo.class, response);
    }

    /**
     * 获取演示详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:demo:query")
    @GetMapping("/{id}")
    public R<DemoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iDemoService.queryById(id));
    }

    /**
     * 新增演示
     */
    @SaCheckPermission("business:demo:add")
    @Log(title = "演示", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DemoBo bo) {
        return toAjax(iDemoService.insertByBo(bo));
    }

    /**
     * 修改演示
     */
    @SaCheckPermission("business:demo:edit")
    @Log(title = "演示", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DemoBo bo) {
        return toAjax(iDemoService.updateByBo(bo));
    }

    /**
     * 删除演示
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:demo:remove")
    @Log(title = "演示", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iDemoService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

}
