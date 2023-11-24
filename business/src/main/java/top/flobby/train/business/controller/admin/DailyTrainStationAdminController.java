package top.flobby.train.business.controller.admin;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.business.req.DailyTrainStationQueryReq;
import top.flobby.train.business.req.DailyTrainStationSaveReq;
import top.flobby.train.business.resp.DailyTrainStationQueryResp;
import top.flobby.train.business.service.DailyTrainStationService;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;

import java.util.Date;

@RestController
@RequestMapping("/admin/daily-train-station")
public class DailyTrainStationAdminController {

    @Resource
    private DailyTrainStationService dailyTrainStationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainStationSaveReq req) {
        dailyTrainStationService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainStationQueryResp>> queryList(@Valid DailyTrainStationQueryReq req) {
        PageResp<DailyTrainStationQueryResp> list = dailyTrainStationService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        dailyTrainStationService.delete(id);
        return CommonResp.success();
    }

    @GetMapping("gen-daily")
    public CommonResp<Object> genDaily(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                       @RequestParam("trainCode") String trainCode) {
        dailyTrainStationService.genDaily(date, trainCode);
        return CommonResp.success();
    }

}