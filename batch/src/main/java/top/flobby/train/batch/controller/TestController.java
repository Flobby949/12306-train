package top.flobby.train.batch.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.batch.feign.BusinessFeign;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-23 13:17
 **/

@Slf4j
@RestController
public class TestController {

    @Resource
    private BusinessFeign businessFeign;

    @GetMapping("/test")
    public String test(){
        log.info("business hello : {}",businessFeign.getHello());
        return "hello";
    }
}
