package top.flobby.train.business.controller.admin;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.business.req.StudentQueryReq;
import top.flobby.train.business.req.StudentSaveReq;
import top.flobby.train.business.resp.StudentQueryResp;
import top.flobby.train.business.service.StudentService;
import top.flobby.train.common.resp.CommonResp;
import top.flobby.train.common.resp.PageResp;

import java.util.List;

@RestController
@RequestMapping("/admin/student")
public class StudentAdminController {

    @Resource
    private StudentService studentService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody StudentSaveReq req) {
        studentService.save(req);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StudentQueryResp>> queryList(@Valid StudentQueryReq req) {
        PageResp<StudentQueryResp> list = studentService.queryList(req);
        return CommonResp.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        studentService.delete(id);
        return CommonResp.success();
    }

    @GetMapping("/query-key")
    public CommonResp<List<StudentQueryResp>> queryKey(@RequestParam String keyword) {
        List<StudentQueryResp> studentQueryResps = studentService.queryByKeyWord(keyword);
        return CommonResp.success(studentQueryResps);
    }

}