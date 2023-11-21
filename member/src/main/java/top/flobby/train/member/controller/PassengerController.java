package top.flobby.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.member.req.PassengerSaveReq;
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
}
