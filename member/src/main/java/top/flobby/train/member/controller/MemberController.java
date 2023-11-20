package top.flobby.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.member.req.MemberRegisterReq;
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

    @GetMapping("/count")
    public int count() {
        return memberService.count();
    }

    @PostMapping("/register")
    public long register(@RequestBody @Valid MemberRegisterReq req) {
        return memberService.register(req);
    }
}
