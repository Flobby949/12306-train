package top.flobby.train.member.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.flobby.train.common.req.PageReq;

/**
 * @author : Flobby
 * @program : train
 * @description : 查询req
 * @create : 2023-11-21 15:04
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class PassengerQueryReq extends PageReq {
    private Long memberId;
}
