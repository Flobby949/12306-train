package top.flobby.train.business.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.ArrayList;
import java.util.List;

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

        // initFlowRules();
        // logger.info("限流规则初始化成功");

    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("doConfirm");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // set limit QPS to 20
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
