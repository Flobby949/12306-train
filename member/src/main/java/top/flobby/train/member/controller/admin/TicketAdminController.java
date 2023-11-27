package top.flobby.train.member.controller.admin;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.common.req.MemberTicketReq;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.member.req.TicketQueryReq;
import top.flobby.train.member.resp.TicketQueryResp;
import top.flobby.train.member.service.TicketService;

@RestController
@RequestMapping("/admin/ticket")
public class TicketAdminController {

    @Resource
    private TicketService ticketService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody MemberTicketReq req) {
        ticketService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> queryList(@Valid TicketQueryReq req) {
        PageResp<TicketQueryResp> list = ticketService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return CommonResp.success();
    }

}