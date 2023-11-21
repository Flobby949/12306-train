package top.flobby.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.common.context.LoginMemberContext;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.member.req.PassengerQueryReq;
import top.flobby.train.member.req.PassengerSaveReq;
import top.flobby.train.member.resp.PassengerQueryResp;
import top.flobby.train.member.service.PassengerService1;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-21 14:19
 **/


@RestController
@RequestMapping("/passenger")
public class MyPassengerController {

    @Resource
    private PassengerService1 passengerService1;

    @PostMapping("/save")
    public CommonResp<Object> save(@RequestBody @Valid PassengerSaveReq req){
        passengerService1.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query")
    public CommonResp<PageResp<PassengerQueryResp>> query(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getMemberId());
        return CommonResp.success(passengerService1.query(req));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        passengerService1.delete(id);
        return CommonResp.success();
    }
}
