package top.flobby.train.business.controller.admin;

import top.flobby.train.common.context.LoginMemberContext;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.business.req.TrainQueryReq;
import top.flobby.train.business.req.TrainSaveReq;
import top.flobby.train.business.resp.TrainQueryResp;
import top.flobby.train.business.service.TrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train")
public class TrainAdminController {

    @Resource
    private TrainService trainService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainSaveReq req) {
        trainService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainQueryResp>> queryList(@Valid TrainQueryReq req) {
        PageResp<TrainQueryResp> list = trainService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        trainService.delete(id);
        return CommonResp.success();
    }

}