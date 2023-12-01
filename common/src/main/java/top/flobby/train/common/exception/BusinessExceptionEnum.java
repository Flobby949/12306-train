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

    MEMBER_PHONE_EXIST("手机号已存在"),
    MEMBER_PHONE_NOT_EXIST("手机号不存在"),
    MEMBER_CODE_ERROR("验证码错误"),
    TRAIN_CODE_EXIST("车次已存在"),
    TRAIN_CODE_NOT_EXIST("车次不存在"),
    TRAIN_CARRIAGE_IS_EMPTY("车厢为空"),
    TRAIN_STATION_EXIST("车站已存在"),
    BUSINESS_TRAIN_STATION_INDEX_UNIQUE_ERROR("同车次站序已存在"),
    BUSINESS_TRAIN_STATION_NAME_UNIQUE_ERROR("同车次站名已存在"),
    BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR("同车次厢号已存在"),
    CONFIRM_ORDER_TICKET_COUNT_ERROR("余票不足"),
    CONFIRM_ORDER_LOCK_ERROR("很遗憾，您未能抢到票"),
    CONFIRM_ORDER_ERROR("订单确认失败"),
    CONFIRM_ORDER_FLOW_EXCEPTION("当前抢票人数多，请稍候重试"),
    CONFIRM_ORDER_SK_TOKEN_FAIL("票已抢光");

    private final String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }
}
