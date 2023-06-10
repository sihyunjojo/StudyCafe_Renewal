package studycafe.studycaferenewal;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import studycafe.studycaferenewal.argumentresolver.LoginMemberArgumentResolver;
import studycafe.studycaferenewal.formatter.LocalDateTimeFormatter;
import studycafe.studycaferenewal.interceptor.LoginCheckInterceptor;
import studycafe.studycaferenewal.interceptor.SessionInterceptor;

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
                        ("/", "/login", "/logout","/popup/**",
                                "/board", "/member/**", "/board/{boardId}", "board/add", "/product", "/product/{productId}","product/add",
                                "/comment/**", "/reply/**","/comment",
                                "/css/**", "/*.ico", "/error", "/img/**", "/template/template/**")
                .addPathPatterns("/member/info");

        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/**") // 인터셉터를 적용할 URL 패턴 지정
                .excludePathPatterns("/login"); // 예외 처리할 URL 패턴 지정

    }

}
