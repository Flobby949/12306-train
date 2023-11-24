package top.flobby.train.business.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.flobby.train.common.req.PageReq;

import java.util.Date;


@Data
public class DailyTrainSeatQueryReq extends PageReq {

    private String trainCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Override
    public String toString() {
        return "DailyTrainSeatQueryReq{" +
        "} " + super.toString();
    }
}