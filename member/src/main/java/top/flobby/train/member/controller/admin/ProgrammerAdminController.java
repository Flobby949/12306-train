package top.flobby.train.member.controller.admin;

import top.flobby.train.common.context.LoginMemberContext;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.member.req.ProgrammerQueryReq;
import top.flobby.train.member.req.ProgrammerSaveReq;
import top.flobby.train.member.resp.ProgrammerQueryResp;
import top.flobby.train.member.service.ProgrammerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/programmer")
public class ProgrammerAdminController {

    @Resource
    private ProgrammerService programmerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody ProgrammerSaveReq req) {
        programmerService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<ProgrammerQueryResp>> queryList(@Valid ProgrammerQueryReq req) {
        PageResp<ProgrammerQueryResp> list = programmerService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        programmerService.delete(id);
        return CommonResp.success();
    }

}