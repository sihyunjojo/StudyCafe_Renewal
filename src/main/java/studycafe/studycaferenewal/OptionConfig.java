package studycafe.studycaferenewal;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import studycafe.studycaferenewal.resolver.argumentresolver.LoginMemberArgumentResolver;
import studycafe.studycaferenewal.formatter.LocalDateTimeFormatter;
import studycafe.studycaferenewal.interceptor.LoginCheckInterceptor;

import java.util.List;

@Configuration
public class OptionConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateTimeFormatter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns
                        ("/", "/login", "/logout","/oauth/**",
                                "/board", "/board/{boardId}", "board/add",
                                "/product", "/product/{productId}","product/add",
//                                "/comment/**", "/reply/**",
                                "/popup/**","/css/**", "/*.ico", "/error", "/img/**", "/template/template/**",
                                "/member/**")
                .addPathPatterns("/member/info");

    }

}
