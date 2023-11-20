package top.flobby.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Flobby
 * @program : train
 * @description : 测试
 * @create : 2023-11-20 15:07
 **/

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }
}
