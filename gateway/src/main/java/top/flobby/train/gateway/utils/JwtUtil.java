package top.flobby.train.gateway.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Flobby
 * @program : train
 * @description : JWT
 * @create : 2023-11-20 17:59
 **/

@Slf4j
public class JwtUtil {
    /**
     * 盐值很重要，不能泄漏，且每个项目都应该不一样，可以放到配置文件中
     */
    private static final String key = "flobby12306";

    public static boolean validate(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        // validate包含了verify
        boolean validate = jwt.validate(0);
        log.info("JWT token校验结果：{}", validate);
        return validate;
    }

}

