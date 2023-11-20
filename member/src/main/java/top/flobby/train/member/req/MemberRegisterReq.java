package top.flobby.train.member.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author : Flobby
 * @program : train
 * @description : 请求参数
 * @create : 2023-11-20 16:08
 **/

@Data
public class MemberRegisterReq {

    @NotNull(message = "手机号不能为空")
    private String mobile;

}
