package top.flobby.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.common.context.LoginMemberContext;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.member.req.TicketQueryReq;
import top.flobby.train.member.resp.TicketQueryResp;
import top.flobby.train.member.service.TicketService;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 16:38
 **/

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> query(@Valid TicketQueryReq req) {
        req.setMemberId(LoginMemberContext.getMemberId());
        PageResp<TicketQueryResp> pageResp = ticketService.queryList(req);
        return CommonResp.success(pageResp);
    }

}
