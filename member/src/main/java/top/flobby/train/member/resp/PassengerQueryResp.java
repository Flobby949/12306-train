package top.flobby.train.member.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-21 15:05
 **/

@Data
public class PassengerQueryResp {
    private Long id;

    private Long memberId;

    private String name;

    private String idCard;

    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
