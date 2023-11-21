package top.flobby.train.common.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.flobby.train.common.context.LoginMemberContext;
import top.flobby.train.common.resp.MemberLoginResp;
import top.flobby.train.common.utils.JwtUtil;

/**
 * @author : Flobby
 * @program : train
 * @description : 拦截器
 * @create : 2023-11-21 14:34
 **/

@Component
@Slf4j
public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (CharSequenceUtil.isNotBlank(token)) {
            log.info("当前token：{}", token);
            MemberLoginResp memberLoginResp = JwtUtil.getJSONObject(token).toBean(MemberLoginResp.class);
            LoginMemberContext.set(memberLoginResp);
        }
        return true;
    }
}
