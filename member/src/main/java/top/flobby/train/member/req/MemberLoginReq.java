package top.flobby.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author : Flobby
 * @program : train
 * @description : 登录请求
 * @create : 2023-11-20 17:20
 **/

@Data
public class MemberLoginReq {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$",message = "手机号格式不正确")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    private String code;
}
