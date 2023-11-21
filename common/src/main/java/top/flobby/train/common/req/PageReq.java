package top.flobby.train.common.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author : Flobby
 * @program : train
 * @description : 分页
 * @create : 2023-11-21 15:11
 **/

@Data
public class PageReq {

    @NotNull(message = "页码不能为空")
    private int page;
    @NotNull(message = "每页条数不能为空")
    @Max(value = 100, message = "每页条数不能超过100")
    private int size;
}
