package top.flobby.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.business.req.DailyTrainTicketQueryReq;
import top.flobby.train.business.resp.DailyTrainTicketQueryResp;
import top.flobby.train.business.service.DailyTrainTicketService;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 09:58
 **/

@RestController
@RequestMapping("/daily-train-ticket")
public class DailyTrainTicketController {

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @GetMapping("/query-all")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryAll(@Valid DailyTrainTicketQueryReq req){
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
        return CommonResp.success(list);
    }
}
