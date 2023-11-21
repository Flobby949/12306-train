package top.flobby.train.common.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @author : Flobby
 * @program : train
 * @description : 返回
 * @create : 2023-11-20 17:20
 **/

@Data
@Builder
public class MemberLoginResp {

    private Long id;
    private String mobile;

    private String token;
}
