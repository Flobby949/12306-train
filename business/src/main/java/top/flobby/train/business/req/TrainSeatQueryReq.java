package top.flobby.train.business.req;

import lombok.Data;
import top.flobby.train.common.req.PageReq;

@Data
public class TrainSeatQueryReq extends PageReq {
    private String trainCode;
    @Override
    public String toString() {
        return "TrainSeatQueryReq{" +
        "} " + super.toString();
    }
}