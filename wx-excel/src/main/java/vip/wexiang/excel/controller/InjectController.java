package vip.wexiang.excel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.wexiang.excel.service.YoudaoService;

import javax.annotation.Resource;

@Slf4j
@Controller
public class InjectController {
    @Resource(name = "youdaoServiceTwoImpl")
    private YoudaoService youdaoService;
    @Resource
    private YoudaoService youdaoServiceTwoImpl;
    //    error
//    No qualifying bean of type 'vip.wexiang.excel.service.YoudaoService' available: expected single matching bean but found 3: youdaoServiceImpl,three,youdaoServiceTwoImpl
//    @Resource
//    private YoudaoService youdaoServiceThreeImpl;
    @Resource
    private YoudaoService three;
    @Resource(name = "three")
    private YoudaoService t;
    //    error
//    @Resource(name = "YoudaoServiceTwoImpl")
//    private YoudaoService youdaoServicexx;
//    @Resource(name = "${resource.youdao}")
//    private YoudaoService youdaoService;
    @Qualifier(value = "youdaoServiceTwoImpl")
    @Autowired
    private YoudaoService aaa;
    //    error
//    @Qualifier(value = "vip.wexiang.excel.service.impl.YoudaoServiceTwoImpl")
//    @Autowired
//    private YoudaoService a;
//    error
//    @Qualifier(value = "YoudaoServiceTwoImpl")
//    @Autowired
//    private YoudaoService aa;
    @Qualifier(value = "three")
    @Autowired
    private YoudaoService aaaa;

    //    error
//    @Serivce("three") 改了bean 名字了
//    @Qualifier(value = "youdaoServiceThreeImpl")
//    @Autowired
//    private YoudaoService aaaaa;
    @PostMapping("/xx")
    public @ResponseBody String resource() {
        System.out.println(youdaoService.toString());
        System.out.println(youdaoServiceTwoImpl.toString());
        System.out.println(aaaa.toString());
        System.out.println(three.toString());
        System.out.println(t.toString());
        System.out.println(aaa.toString());

        return "resource";
    }

}
