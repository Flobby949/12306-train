package top.flobby.train.business.controller.admin;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.business.req.TrainQueryReq;
import top.flobby.train.business.req.TrainSaveReq;
import top.flobby.train.business.resp.TrainQueryResp;
import top.flobby.train.business.service.TrainSeatService;
import top.flobby.train.business.service.TrainService;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;

import java.util.List;

@RestController
@RequestMapping("/admin/train")
public class TrainAdminController {

    @Resource
    private TrainService trainService;
    @Resource
    private TrainSeatService trainSeatService;

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

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryAll() {
        return CommonResp.success(trainService.queryAll());
    }

    @GetMapping("gen-seat/{trainCode}")
    public CommonResp<Object> genSeat(@PathVariable String trainCode) {
        trainSeatService.genTrainSeat(trainCode);
        return CommonResp.success();
    }
}