package top.flobby.train.business.req;

import lombok.Data;
import top.flobby.train.common.req.PageReq;

@Data
public class TrainStationQueryReq extends PageReq {

    private String trainCode;

    @Override
    public String toString() {
        return "TrainStationQueryReq{" +
        "} " + super.toString();
    }
}