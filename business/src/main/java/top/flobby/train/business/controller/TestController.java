package top.flobby.train.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-22 08:31
 **/

@RestController
public class TestController {

    @GetMapping("/test")
    @SentinelResource("hello")
    public String test() {
        return "hello";
    }
}
