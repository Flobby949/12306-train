package top.flobby.train.business.controller.admin;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.business.req.AppManageQueryReq;
import top.flobby.train.business.req.AppManageSaveReq;
import top.flobby.train.business.req.AppVersionCheckReq;
import top.flobby.train.business.resp.AppManageQueryResp;
import top.flobby.train.business.service.AppManageService;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;

@RestController
@RequestMapping("/admin/app-manage")
public class AppManageAdminController {

    @Resource
    private AppManageService appManageService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody AppManageSaveReq req) {
        appManageService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<AppManageQueryResp>> queryList(@Valid AppManageQueryReq req) {
        PageResp<AppManageQueryResp> list = appManageService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        appManageService.delete(id);
        return CommonResp.success();
    }

    // 根据id查询
    @GetMapping("/query/{id}")
    public CommonResp<AppManageQueryResp> query(@PathVariable Long id) {
        AppManageQueryResp appManageQueryResp = appManageService.query(id);
        return CommonResp.success(appManageQueryResp);
    }

    @PostMapping("/check-version")
    public CommonResp<Object> checkVersion(@Valid @RequestBody AppVersionCheckReq req) {
        AppManageQueryResp appManageQueryResp = appManageService.checkVersion(req);
        if (appManageQueryResp == null) {
            return CommonResp.success();
        }
        return CommonResp.error("请更新版本！", appManageQueryResp);
    }

}