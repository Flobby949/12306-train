package top.flobby.train.business.controller.admin;

import top.flobby.train.common.context.LoginMemberContext;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.business.req.DailyTrainTicketQueryReq;
import top.flobby.train.business.req.DailyTrainTicketSaveReq;
import top.flobby.train.business.resp.DailyTrainTicketQueryResp;
import top.flobby.train.business.service.DailyTrainTicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-ticket")
public class DailyTrainTicketAdminController {

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainTicketSaveReq req) {
        dailyTrainTicketService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        dailyTrainTicketService.delete(id);
        return CommonResp.success();
    }

}