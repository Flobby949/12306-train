package top.flobby.train.business.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.flobby.train.common.interceptor.MemberInterceptor;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-11-21 14:38
 **/

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    private MemberInterceptor memberInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/business/test"
                );
    }
}
