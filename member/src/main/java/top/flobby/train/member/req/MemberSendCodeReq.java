package top.flobby.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author : Flobby
 * @program : train
 * @description : 发送短信
 * @create : 2023-11-20 17:08
 **/

@Data
public class MemberSendCodeReq {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$",message = "手机号格式不正确")
    private String mobile;
}
