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
import top.flobby.train.member.service.PassengerService;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-21 14:19
 **/


@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@RequestBody @Valid PassengerSaveReq req){
        passengerService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query")
    public CommonResp<PageResp<PassengerQueryResp>> query(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getMemberId());
        return CommonResp.success(passengerService.query(req));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        passengerService.delete(id);
        return CommonResp.success();
    }
}
