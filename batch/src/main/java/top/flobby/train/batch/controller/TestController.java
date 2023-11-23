package top.flobby.train.batch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-23 13:17
 **/

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
