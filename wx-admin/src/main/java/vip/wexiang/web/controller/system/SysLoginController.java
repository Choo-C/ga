package vip.wexiang.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckBasic;
import cn.dev33.satoken.annotation.SaIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import vip.wexiang.common.constant.Constants;
import vip.wexiang.common.core.domain.R;
import vip.wexiang.common.core.domain.entity.SysMenu;
import vip.wexiang.common.core.domain.model.LoginBody;
import vip.wexiang.common.helper.LoginHelper;
import vip.wexiang.system.domain.vo.RouterVo;
import vip.wexiang.system.service.ISysMenuService;
import vip.wexiang.system.service.SysLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录验证
 *
 * @author Lion Li
 */
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
public class SysLoginController {

    private final SysLoginService loginService;
    private final ISysMenuService menuService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @SaIgnore
    @PostMapping("/login")
    public R<Map<String, Object>> login(@Validated @RequestBody LoginBody loginBody) {
        Map<String, Object> ajax = new HashMap<>();
        log.error(loginBody.getPassword());
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
            loginBody.getUuid());
        log.error(token);
        ajax.put(Constants.TOKEN, token);
        return R.ok(ajax);
    }

    /**
     * 退出登录
     */
    //    @SaCheckBasic(account = "sa:123456")
    @SaIgnore
    @PostMapping("/logout")
    public R<Void> logout() {
        log.error(name);
        log.error(" @SaCheckBasic(account = \"sa:123456\")");
        loginService.logout();
        return R.ok("退出成功");
    }
    @Value("${system.version}")
    public String name;
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public R<Map<String, Object>> getInfo() {
        return R.ok(loginService.getInfo());
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public R<List<RouterVo>> getRouters() {
        Long userId = LoginHelper.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return R.ok(menuService.buildMenus(menus));
    }
}
