package top.flobby.train.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Flobby
 * @program : train
 * @description : 乘车人参数
 * @create : 2023-11-21 14:14
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSaveReq {
    private Long id;

    private Long memberId;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "身份证号不能为空")
    private String idCard;
    @NotBlank(message = "旅客类型不能为空")
    private String type;

    private Date createTime;
}
