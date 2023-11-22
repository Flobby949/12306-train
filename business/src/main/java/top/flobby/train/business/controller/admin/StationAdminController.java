package top.flobby.train.business.controller.admin;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.business.req.StationQueryReq;
import top.flobby.train.business.req.StationSaveReq;
import top.flobby.train.business.resp.StationQueryResp;
import top.flobby.train.business.service.StationService;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;

@RestController
@RequestMapping("/admin/station")
public class StationAdminController {

    @Resource
    private StationService stationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody StationSaveReq req) {
        stationService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {
        PageResp<StationQueryResp> list = stationService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        stationService.delete(id);
        return CommonResp.success();
    }

    @GetMapping("/query-all")
    public CommonResp<Object> queryAll() {
        return CommonResp.success(stationService.queryAll());
    }

}