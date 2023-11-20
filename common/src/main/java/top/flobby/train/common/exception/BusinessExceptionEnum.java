package top.flobby.train.common.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @author : Flobby
 * @program : train
 * @description : 业务异常枚举
 * @create : 2023-11-20 16:14
 **/

@Getter
@ToString
public enum BusinessExceptionEnum {

    MEMBER_PHONE_EXIST("手机号已存在");

    private final String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }
}
