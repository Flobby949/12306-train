package top.flobby.train.member.req;

import lombok.Data;
import top.flobby.train.common.req.PageReq;

@Data
public class PassengerQueryReq extends PageReq {

    private Long memberId;
    @Override
    public String toString() {
        return "PassengerQueryReq{" +
        "} " + super.toString();
    }
}