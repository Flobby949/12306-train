package top.flobby.train.business.enums;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author : Flobby
 * @program : train
 * @description : 座位类型枚举
 * @create : 2023-11-22 09:17
 **/

@Getter
public enum SeatTypeEnum {

    YDZ("一等座","1", new BigDecimal("0.4")),
    EDZ("二等座","2", new BigDecimal("0.3")),
    YWS("硬卧","3", new BigDecimal("0.5")),
    RWS("软卧","4", new BigDecimal("0.6"));

    private final String desc;
    private final String code;
    /**
     * 基础票价 N元/公里
     */
    private final BigDecimal price;

    SeatTypeEnum(String desc, String code, BigDecimal price) {
        this.desc = desc;
        this.code = code;
        this.price = price;
    }
}
