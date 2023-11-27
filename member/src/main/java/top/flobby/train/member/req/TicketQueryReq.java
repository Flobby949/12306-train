package top.flobby.train.member.req;

import lombok.Data;
import top.flobby.train.common.req.PageReq;

@Data
public class TicketQueryReq extends PageReq {

    private Long memberId;



    @Override
    public String toString() {
        return "TicketQueryReq{" +
        "} " + super.toString();
    }
}