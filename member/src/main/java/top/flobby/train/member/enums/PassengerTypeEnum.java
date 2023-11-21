package top.flobby.train.member.enums;

import lombok.Getter;

/**
 * @author : Flobby
 * @program : train
 * @description : 乘车人类型枚举
 * @create : 2023-11-21 14:13
 **/

@Getter
public enum PassengerTypeEnum {

    AUDIT("1","成人"),
    CHILD("2","儿童"),
    STUDENT("3","学生");

    private final String code;
    private final String desc;

    PassengerTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
