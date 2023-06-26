package vip.wexiang.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
import vip.wexiang.business.domain.vo.InstructVo;
import vip.wexiang.business.domain.bo.InstructBo;
import vip.wexiang.business.service.IInstructService;
import vip.wexiang.common.core.page.TableDataInfo;

/**
 * 指令
 * Created by admin.
 */
@Validated
@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/business/instruct")
@Slf4j
public class InstructController extends BaseController {

    private final IInstructService iInstructService;

    /**
     * 查询指令列表
     */
    @SaCheckPermission("business:instruct:list")
    @GetMapping("/list")
    public TableDataInfo<InstructVo> list(InstructBo bo, PageQuery pageQuery) {
        return iInstructService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出指令列表
     */
    @SaCheckPermission("business:instruct:export")
    @Log(title = "指令", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(InstructBo bo, HttpServletResponse response) {
        List<InstructVo> list = iInstructService.queryList(bo);
        ExcelUtil.exportExcel(list, "指令", InstructVo.class, response);
    }

    /**
     * 获取指令详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:instruct:query")
    @GetMapping("/{id}")
    public R<InstructVo> getInfo(@NotNull(message = "主键不能为空")
                                 @PathVariable String id) {
        return R.ok(iInstructService.queryById(id));
    }

    /**
     * 新增指令
     */
    @SaCheckPermission("business:instruct:add")
    @Log(title = "指令", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("add")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody InstructBo bo) {
        log.info("xxxxx");
        return toAjax(iInstructService.insertByBo(bo));
    }

    @PostMapping("hierarchy")
    public Map<String, String> hierarchy() {
        log.info("hierarchy");
        // 获取：当前账号所拥有的权限集合
        log.error(StpUtil.getPermissionList() + "");


// 判断：当前账号是否含有指定权限, 返回 true 或 false
        log.error(StpUtil.hasPermission("user.add") + "hasPermission");
        ;

        // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
//        StpUtil.checkPermission("user.add");

// 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("business:instruct:add", "business:instruct:edit", "business:instruct:remove");

// 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");

        HashMap<String, String> map = new HashMap<>();
        map.put("xxx", "hierarchy");
        return map;
    }

    @SaIgnore
    @PostMapping("ignore")
    public Map<String, String> ignore(@Validated(AddGroup.class) @RequestBody InstructBo bo) {
        log.info("ignore");
        HashMap<String, String> map = new HashMap<>();
        map.put("xxx", "ignore");
        return map;
    }

    @SaIgnore
    @PostMapping("kickout")
    public Map<String, String> kickout() {
        log.info("kickout");
//        Object loginId = StpUtil.getLoginId();

//        StpUtil.kickout(loginId);
        StpUtil.kickout("sys_user:1666621131962789889");
        HashMap<String, String> map = new HashMap<>();
        map.put("xxx", "ignore");
        return map;
    }


    @SaIgnore
    @PostMapping("notIgnore")
    public Map<String, String> notIgnore() {
        log.info("ignore");
        HashMap<String, String> map = new HashMap<>();
        map.put("xxx", "notIgnore");
        log.info(StpUtil.isLogin() + "");
        log.info(StpUtil.getLoginId() + "");
        log.info(StpUtil.getLoginId() + "");
        log.info(StpUtil.getLoginId() + "123132321");
        log.info(StpUtil.getLoginId() + "sa ");
        // 获取当前会话账号id, 并转化为`String`类型
        log.info(StpUtil.getLoginIdAsString() + "sa ");
        log.info(StpUtil.getLoginIdAsString() + "sa123 ");
        log.info(StpUtil.getLoginIdAsString() + "sa123 ");
        // 获取当前会话账号id, 并转化为`int`类型
//        log.info(StpUtil.getLoginIdAsInt()+"sa ");
//        // 获取当前会话账号id, 并转化为`long`类型
//        log.info(StpUtil.getLoginIdAsLong()+"sa ");
        log.error(StpUtil.getTokenValue());
        log.error(StpUtil.getTokenName());
        log.error(StpUtil.getTokenTimeout() + "");
        log.error(StpUtil.getTokenInfo() + "");
//        {
//            "code": 200,
//            "msg": "ok",
//            "data": {
//            "tokenName": "satoken",           // token名称
//                "tokenValue": "e67b99f1-3d7a-4a8d-bb2f-e888a0805633",      // token值
//                "isLogin": true,                  // 此token是否已经登录
//                "loginId": "10001",               // 此token对应的LoginId，未登录时为null
//                "loginType": "login",              // 账号类型标识
//                "tokenTimeout": 2591977,          // token剩余有效期 (单位: 秒)
//                "sessionTimeout": 2591977,        // User-Session剩余有效时间 (单位: 秒)
//                "tokenSessionTimeout": -2,        // Token-Session剩余有效时间 (单位: 秒) (-2表示系统中不存在这个缓存)
//                "tokenActivityTimeout": -1,       // token剩余无操作有效时间 (单位: 秒)
//                "loginDevice": "default-device"   // 登录设备类型
//        },
//        }

        return map;
    }

    /**
     * 修改指令
     */
    @SaCheckPermission("business:instruct:edit")
    @Log(title = "指令", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody InstructBo bo) {
        return toAjax(iInstructService.updateByBo(bo));
    }

    /**
     * 删除指令
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:instruct:remove")
    @Log(title = "指令", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iInstructService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
    // 测试登录  ---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456

    @RequestMapping("doLogin")
    public SaResult doLogin(String name, String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }


    //-------------------------------
    // 登录校验：只有登录之后才能进入该方法
    @SaCheckLogin
    @RequestMapping("testSaCheckLogin")
    public String testSaCheckLogin() {
        return "testSaCheckLogin";
    }

    // 角色校验：必须具有指定角色才能进入该方法
    @SaCheckRole("admin")
    @ResponseBody
    @RequestMapping("testSaCheckRole")
    public String testSaCheckRole() {
        return "testSaCheckRole";
    }

    // 权限校验：必须具有指定权限才能进入该方法
    @SaCheckPermission("user:add:XXX")
    @RequestMapping(value = "testSaCheckPermission", method = RequestMethod.GET)
    public String testSaCheckPermission() {
        // 在当前会话 开启二级认证，时间为120秒
        StpUtil.openSafe(120);

        // 获取：当前会话是否处于二级认证时间内
        log.warn(StpUtil.isSafe() + ":StpUtil.isSafe()");


        // 检查当前会话是否已通过二级认证，如未通过则抛出异常
        StpUtil.checkSafe();

        // 获取当前会话的二级认证剩余有效时间 (单位: 秒, 返回-2代表尚未通过二级认证)
        log.warn(StpUtil.getSafeTime() + ":StpUtil.getSafeTime()");
        ;

        return "forward:/business/instruct/testSaCheckSafe";
    }

    // 二级认证校验：必须二级认证之后才能进入该方法
    @SaCheckSafe
    @ResponseBody
    @RequestMapping(value = "testSaCheckSafe", method = RequestMethod.GET)
    public String testSaCheckSafe() {
        log.error("testSaCheckSafe");
        // 在当前会话 结束二级认证
        StpUtil.closeSafe();

        return "forward:/business/instruct/testSaCheckBasic";
    }

    // Http Basic 校验：只有通过 Basic 认证后才能进入该方法
    @SaCheckBasic(account = "sa:123456")
//    @SaIgnore
    @RequestMapping("testSaCheckBasic")
    @ResponseBody
    public String testSaCheckBasic() {
        return "testSaCheckBasic";
    }

    // 校验当前账号是否被封禁 comment 服务，如果已被封禁会抛出异常，无法进入方法
    @SaCheckDisable("comment")
    @RequestMapping("testSaCheckDisable")
    public String testSaCheckDisable() {
        return "testSaCheckDisable";
    }

    //    -------------------------------------------
    @SaCheckRole("admin")
    @ResponseBody
    @RequestMapping("testSession")
    public String testSession() {
        // 获取当前账号id的Session (必须是登录后才能调用)

        log.error(StpUtil.getSession().getId());

        // 获取当前账号id的Session, 并决定在Session尚未创建时，是否新建并返回
//        StpUtil.getSession(true);

        // 获取账号id为10001的Session
        log.error(StpUtil.getSessionByLoginId("sys_user:1").getId());


        // 获取账号id为10001的Session, 并决定在Session尚未创建时，是否新建并返回
        log.error( StpUtil.getSessionByLoginId("sys_user:1", true).getId());
       ;

        // 获取SessionId为xxxx-xxxx的Session, 在Session尚未创建时, 返回null
//        StpUtil.getSessionBySessionId("xxxx-xxxx");
        // 获取当前 Token 的 Token-Session 对象
        log.error( StpUtil.getTokenSession().getId());
        ;

        // 获取指定 Token 的 Token-Session 对象
//        StpUtil.getTokenSessionByToken(token);

        return "testSession";
    }


}
