package top.flobby.train.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : Flobby
 * @program : train
 * @description : 启动类
 * @create : 2023-11-20 15:30
 **/

@SpringBootApplication(scanBasePackages = "top.flobby")
public class GatewayApplication {

    private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        logger.info("启动成功");
        logger.info("网关地址：http://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
