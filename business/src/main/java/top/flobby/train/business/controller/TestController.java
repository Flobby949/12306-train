package top.flobby.train.business.controller;

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
    public String test(){
        return "hello";
    }
}
