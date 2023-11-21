package top.flobby.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.member.req.MemberLoginReq;
import top.flobby.train.member.req.MemberRegisterReq;
import top.flobby.train.member.req.MemberSendCodeReq;
import top.flobby.train.member.service.MemberService1;

/**
 * @author : Flobby
 * @program : train
 * @description : controller
 * @create : 2023-11-20 16:00
 **/

@RestController
@RequestMapping("/member")
public class MyMemberController {

    @Resource
    private MemberService1 memberService1;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        return CommonResp.success(memberService1.count());
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@RequestBody @Valid MemberRegisterReq req) {
        return CommonResp.success(memberService1.register(req));
    }

    @PostMapping("/code")
    public CommonResp<Object> sendCode(@RequestBody @Valid MemberSendCodeReq req) {
        memberService1.sendCode(req);
        return CommonResp.success();
    }

    @PostMapping("/login")
    public CommonResp<Object> login(@RequestBody @Valid MemberLoginReq req) {
        return CommonResp.success(memberService1.login(req));
    }
}
