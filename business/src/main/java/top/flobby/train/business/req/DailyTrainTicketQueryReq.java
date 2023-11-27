package top.flobby.train.business.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.flobby.train.common.req.PageReq;

import java.util.Date;

@Data
public class DailyTrainTicketQueryReq extends PageReq {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String trainCode;

    private String start;

    private String end;

    @Override
    public String toString() {
        return "DailyTrainTicketQueryReq{" +
        "} " + super.toString();
    }
}