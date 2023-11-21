package top.flobby.train.common.context;

import lombok.extern.slf4j.Slf4j;
import top.flobby.train.common.resp.MemberLoginResp;

/**
 * @author : Flobby
 * @program : train
 * @description : 线程本地变量存储会员信息
 * @create : 2023-11-21 14:31
 **/


@Slf4j
public class LoginMemberContext {
    private static final ThreadLocal<MemberLoginResp> MEMBER = new ThreadLocal<>();

    public static void set(MemberLoginResp loginMember) {
        MEMBER.set(loginMember);
    }

    public static MemberLoginResp get() {
        return MEMBER.get();
    }

    public static Long getMemberId() {
        try {
            return MEMBER.get().getId();
        } catch (Exception e) {
            log.error("获取会员id失败", e);
            throw e;
        }
    }

    public static void remove() {
        MEMBER.remove();
    }
}
