package top.flobby.train.business.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.flobby.train.common.req.PageReq;

import java.util.Date;

@Data
public class DailyTrainQueryReq extends PageReq {

    private String code;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Override
    public String toString() {
        return "DailyTrainQueryReq{" +
        "} " + super.toString();
    }
}