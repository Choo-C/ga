package vip.wexiang.wxmaven.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MavenController {
    @Value("${my.maven}")
    private String maven;
    @Value("${my.qwe}")
    private String qwe;
    @PostMapping(value = "/maven")
    public String maven(){
        System.out.println(maven+">>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(qwe+">>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("maven<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return "maven";
    }
}
