package studycafe.studycaferenewal;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import studycafe.studycaferenewal.argumentresolver.LoginMemberArgumentResolver;
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
//        registry.addFormatter(new DateFormatter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns
                        ("/", "/login", "/logout",
                                "/board", "/member/**", "/board/{boardId}", "board/add", "/product", "/product/{productId}",
                                "/css/**", "/*.ico", "/error", "/**/*.jpg", "/template/template/**");
    }

}
