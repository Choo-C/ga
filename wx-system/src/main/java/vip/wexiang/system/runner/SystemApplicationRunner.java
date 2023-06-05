package vip.wexiang.system.runner;

import vip.wexiang.common.config.SystemConfig;
import vip.wexiang.system.service.ISysConfigService;
import vip.wexiang.system.service.ISysDictTypeService;
import vip.wexiang.system.service.ISysOssConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SystemApplicationRunner implements ApplicationRunner {

    private final SystemConfig ruoyiConfig;
    private final ISysConfigService configService;
    private final ISysDictTypeService dictTypeService;
    private final ISysOssConfigService ossConfigService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ossConfigService.init();
        log.info("初始化OSS配置成功");
        if (ruoyiConfig.isCacheLazy()) {
            return;
        }
        configService.loadingConfigCache();
        log.info("加载参数缓存数据成功");
        dictTypeService.loadingDictCache();
        log.info("加载字典缓存数据成功");
    }

}
