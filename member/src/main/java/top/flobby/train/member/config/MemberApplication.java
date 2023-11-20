package top.flobby.train.member.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Flobby
 * @program : train
 * @description : 启动类
 * @create : 2023-11-20 15:06
 **/

@SpringBootApplication(scanBasePackages = "top.flobby")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }
}
