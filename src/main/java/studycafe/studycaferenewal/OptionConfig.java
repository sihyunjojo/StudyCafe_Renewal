package studycafe.studycaferenewal;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import studycafe.studycaferenewal.argumentresolver.LoginMemberArgumentResolver;

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
}
