package vip.wexiang.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class XxlJobDemoApplication {
    public static void main(String[] args) {
//        故意写的
        System.out.println(new Date() + ": Starting Xiaolang job demo...");
        SpringApplication.run(XxlJobDemoApplication.class, args);
    }
}
