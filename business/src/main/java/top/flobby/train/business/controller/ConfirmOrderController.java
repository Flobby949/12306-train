package top.flobby.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.flobby.train.business.req.ConfirmOrderDoReq;
import top.flobby.train.business.service.ConfirmOrderService;
import top.flobby.train.common.resp.CommonResp;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 14:45
 **/

@Slf4j
@RestController
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

    @Resource
    private ConfirmOrderService confirmOrderService;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @PostMapping("/do")
    public CommonResp<Object> doConfirm(@Valid @RequestBody ConfirmOrderDoReq req) {
        // 图形验证码校验
        String imageCodeToken = req.getImageCodeToken();
        String imageCode = req.getImageCode();
        String imageCodeRedis = redisTemplate.opsForValue().get(imageCodeToken);
        log.info("从redis中获取到的验证码：{}", imageCodeRedis);
        if (ObjectUtils.isEmpty(imageCodeRedis)) {
            return CommonResp.error("验证码已过期");
        }
        // 验证码校验，忽略大小写
        if (!imageCodeRedis.equalsIgnoreCase(imageCode)) {
            return CommonResp.error("验证码不正确");
        } else {
            // 验证通过后，移除验证码
            redisTemplate.delete(imageCodeToken);
        }
        confirmOrderService.doConfirm(req);
        return CommonResp.success();
    }
}
