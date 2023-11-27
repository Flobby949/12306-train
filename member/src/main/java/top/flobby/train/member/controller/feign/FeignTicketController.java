package top.flobby.train.member.controller.feign;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.common.req.MemberTicketReq;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.member.service.TicketService;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 16:26
 **/

@RestController
@RequestMapping("/feign/ticket")
public class FeignTicketController {

    @Resource
    private TicketService ticketService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody MemberTicketReq req) throws Exception {
        ticketService.save(req);
        return new CommonResp<>();
    }

}
