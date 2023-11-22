package top.flobby.train.business.enums;

import lombok.Getter;

/**
 * @author : Flobby
 * @program : train
 * @description : 座位列枚举
 * @create : 2023-11-22 09:26
 **/

@Getter
public enum SeatColEnum {
    YDZ_A("A", "A", "1"),
    YDZ_C("C", "C", "1"),
    YDZ_D("D", "D", "1"),
    YDZ_F("F", "F", "1"),
    EDZ_A("A", "A", "2"),
    EDZ_B("B", "B", "2"),
    EDZ_C("C", "C", "2"),
    EDZ_D("D", "D", "2"),
    EDZ_F("F", "F", "2");

    private final String code;

    private final String desc;

    /**
     * 对应SeatTypeEnum.code
     */
    private final String type;

    SeatColEnum(String code, String desc, String type) {
        this.code = code;
        this.desc = desc;
        this.type = type;
    }
}
