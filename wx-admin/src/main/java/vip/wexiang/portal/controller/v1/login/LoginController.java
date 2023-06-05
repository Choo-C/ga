package vip.wexiang.portal.controller.v1.login;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.wexiang.common.constant.CacheConstants;
import vip.wexiang.common.constant.Constants;
import vip.wexiang.common.core.controller.BaseController;
import vip.wexiang.common.core.domain.R;
import vip.wexiang.common.core.domain.entity.SysUser;
import vip.wexiang.common.core.domain.model.SmsLoginBody;
import vip.wexiang.common.utils.RegexUtils;
import vip.wexiang.common.utils.redis.RedisUtils;
import vip.wexiang.common.utils.spring.SpringUtils;
import vip.wexiang.sms.config.properties.SmsProperties;
import vip.wexiang.sms.core.SmsTemplate;
import vip.wexiang.sms.entity.SmsResult;
import vip.wexiang.system.service.ISysUserService;
import vip.wexiang.system.service.SysLoginService;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * Created by wu.
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LoginController extends BaseController {

    private final SmsProperties smsProperties;
    private final SysLoginService loginService;
    private final ISysUserService userService;

    /**
     * 短信验证码
     *
     * @param phonenumber 用户手机号
     */
    @GetMapping("/captchaSms")
    public R<Void> smsCaptcha(@NotBlank(message = "{user.phonenumber.not.blank}") String phonenumber) {
        if (!RegexUtils.isPhone(phonenumber)) {
            return R.fail("手机号码不合法！");
        }
        if (!smsProperties.getEnabled()) {
            return R.fail("当前系统没有开启短信功能！");
        }
        String key = CacheConstants.CAPTCHA_CODE_KEY + phonenumber;
        String code = RandomUtil.randomNumbers(4);
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Constants.CAPTCHA_EXPIRATION));
        // 验证码模板id 自行处理 (查数据库或写死均可)
        String templateId = "";
        Map<String, String> map = new HashMap<>(1);
        map.put("code", code);
        SmsTemplate smsTemplate = SpringUtils.getBean(SmsTemplate.class);
        SmsResult result = smsTemplate.send(phonenumber, templateId, map);
        if (!result.isSuccess()) {
            log.error("验证码短信发送异常 => {}", result);
            return R.fail(result.getMessage());
        }
        return R.ok();
    }

    /**
     * 短信登录
     *
     * @param smsLoginBody 登录信息
     * @return 结果
     */
    @SaIgnore
    @PostMapping("/smsLogin")
    public R<Map<String, Object>> smsLogin(@Validated @RequestBody SmsLoginBody smsLoginBody) {
        Map<String, Object> ajax = new HashMap<>();
        // 生成令牌
        String token = loginService.smsLogin(smsLoginBody.getPhonenumber(), smsLoginBody.getSmsCode());
        ajax.put(Constants.TOKEN, token);
        return R.ok(ajax);
    }

    /**
     * 小程序登录
     *
     * @param xcxCode 小程序code（小程序调用 wx.login 授权后获取）
     * @return 结果
     */
    @SaIgnore
    @PostMapping("/xcxLogin")
    public R<Map<String, Object>> xcxLogin(@NotBlank(message = "{xcx.code.not.blank}") String xcxCode) {
        Map<String, Object> ajax = new HashMap<>();
        // 生成令牌
        String token = loginService.xcxLogin(xcxCode);
        ajax.put(Constants.TOKEN, token);
        return R.ok(ajax);
    }

    /**
     * 退出登录
     */
    @SaIgnore
    @PostMapping("/logout")
    public R<Void> logout() {
        loginService.logout();
        return R.ok("退出成功");
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getUserInfo")
    public R<SysUser> getInfo() {
        SysUser user = userService.selectUserById(getUserId());
        return R.ok(user);
    }

}
