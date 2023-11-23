package top.flobby.train.batch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : Flobby
 * @program : train
 * @description : 启动类
 * @create : 2023-11-23 13:15
 **/

@SpringBootApplication(scanBasePackages = "top.flobby")
@MapperScan("top.flobby.train.*.mapper")
public class BatchApplication {
    private static final Logger log = LoggerFactory.getLogger(BatchApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BatchApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        log.info("启动成功！！");
        log.info("测试地址: http://127.0.0.1:{}{}/test", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));

    }
}
