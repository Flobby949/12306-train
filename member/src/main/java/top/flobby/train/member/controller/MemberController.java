package top.flobby.train.member.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.member.service.MemberService;

/**
 * @author : Flobby
 * @program : train
 * @description : controller
 * @create : 2023-11-20 16:00
 **/

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @RequestMapping("/count")
    public int count() {
        return memberService.count();
    }
}
