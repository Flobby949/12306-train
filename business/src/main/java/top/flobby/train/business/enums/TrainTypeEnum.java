package top.flobby.train.business.enums;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author : Flobby
 * @program : train
 * @description : 火车类型
 * @create : 2023-11-22 08:56
 **/

@Getter
public enum TrainTypeEnum {

    G("G","高铁", new BigDecimal("1.2")),
    D("D","动车", new BigDecimal("1")),
    K("K","快车", new BigDecimal("0.8"));

    private final String code;
    private final String desc;
    private final BigDecimal priceRate;

    TrainTypeEnum(String code, String desc, BigDecimal priceRate) {
        this.code = code;
        this.desc = desc;
        this.priceRate = priceRate;
    }
}
