package top.flobby.train.business.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.business.resp.StationQueryResp;
import top.flobby.train.business.service.StationService;
import top.flobby.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 09:58
 **/

@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    private StationService stationService;

    @GetMapping("/query-all")
    public CommonResp<List<StationQueryResp>> queryAll(){
        List<StationQueryResp> list = stationService.queryAll();
        return CommonResp.success(list);
    }
}
