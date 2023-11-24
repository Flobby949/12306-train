package top.flobby.train.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.flobby.train.common.resp.CommonResp;

import java.util.Date;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-24 10:19
 **/

@FeignClient(name = "business", url = "http://localhost:8002/business")
public interface BusinessFeign {

    @GetMapping("/test")
    String getHello();

    @GetMapping("/admin/daily-train/genDaily/{date}")
    CommonResp<Object> genDaily(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
}
