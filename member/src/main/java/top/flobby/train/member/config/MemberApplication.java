package top.flobby.train.member.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : Flobby
 * @program : train
 * @description : 启动类
 * @create : 2023-11-20 15:06
 **/

@SpringBootApplication(scanBasePackages = "top.flobby")
public class MemberApplication {
    private static final Logger logger = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        logger.info("启动成功");
        logger.info("测试地址：http://localhost:{}/test", env.getProperty("server.port"));
    }
}
