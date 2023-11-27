package top.flobby.train.business.mapper;

import java.util.Date;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-27 16:08
 **/

public interface DailyTrainTicketMapperCust {
    void updateCountBySell(Date date
            , String trainCode
            , String seatTypeCode
            , Integer minStartIndex
            , Integer maxStartIndex
            , Integer minEndIndex
            , Integer maxEndIndex);
}
