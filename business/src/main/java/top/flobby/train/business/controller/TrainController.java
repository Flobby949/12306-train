package top.flobby.train.business.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.business.resp.TrainQueryResp;
import top.flobby.train.business.service.TrainService;
import top.flobby.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 09:58
 **/

@RestController
@RequestMapping("/train")
public class TrainController {

    @Resource
    private TrainService trainService;

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryAll(){
        List<TrainQueryResp> list = trainService.queryAll();
        return CommonResp.success(list);
    }
}
