package top.flobby.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.business.req.ConfirmOrderDoReq;
import top.flobby.train.business.service.ConfirmOrderService;
import top.flobby.train.common.resp.CommonResp;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 14:45
 **/

@RestController
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

    @Resource
    private ConfirmOrderService confirmOrderService;

    @PostMapping("/do")
    public CommonResp<Object> doConfirm(@Valid @RequestBody ConfirmOrderDoReq req) {
        confirmOrderService.doConfirm(req);
        return CommonResp.success();
    }
}
