package top.flobby.train.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.flobby.train.common.req.MemberTicketReq;
import top.flobby.train.common.resp.CommonResp;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 16:28
 **/

@FeignClient(name = "train-member", path = "/member")
public interface MemberFeign {

    @GetMapping("/member/feign/ticket/save")
    CommonResp<Object> save(@RequestBody MemberTicketReq req);

}
