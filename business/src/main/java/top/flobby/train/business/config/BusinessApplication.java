package top.flobby.train.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : Flobby
 * @program : train
 * @description : 启动类
 * @create : 2023-11-22 08:21
 **/

@EnableFeignClients(basePackages = "top.flobby.train.*.feign")
@SpringBootApplication(scanBasePackages = "top.flobby")
@MapperScan("top.flobby.train.*.mapper")
@EnableCaching
public class BusinessApplication {
    private static final Logger logger = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        logger.info("启动成功");
        logger.info("测试地址：\thttp://localhost:{}{}/test", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}
