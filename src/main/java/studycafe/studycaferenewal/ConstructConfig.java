package studycafe.studycaferenewal;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studycafe.studycaferenewal.repository.board.board.JpaBoardQueryRepository;
import studycafe.studycaferenewal.repository.member.JpaMemberRepository;
import studycafe.studycaferenewal.repository.member.JpaQueryMemberRepository;
import studycafe.studycaferenewal.repository.member.MemberRepository;
import studycafe.studycaferenewal.repository.product.JpaProductQueryRepository;
import studycafe.studycaferenewal.service.oauth.OAuthService;
import studycafe.studycaferenewal.service.login.LoginService;
import studycafe.studycaferenewal.service.login.SpringDataJpaLoginService;
import studycafe.studycaferenewal.service.member.MemberService;
import studycafe.studycaferenewal.service.member.SpringDataJpaMemberService;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class ConstructConfig {

    private final EntityManager em;
    private final JpaMemberRepository jpaMemberRepository;


    @Bean
    public MemberService memberService(){
        return new SpringDataJpaMemberService(jpaMemberRepository);
    }

    @Bean
    public OAuthService oAuthService(){
        return new OAuthService(jpaMemberRepository);
    }

    @Bean
    public LoginService loginService() {
        return new SpringDataJpaLoginService(jpaMemberRepository);
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaQueryMemberRepository(em);
    }

    @Bean
    public JpaBoardQueryRepository jpaBoardQueryRepository(){
        return new JpaBoardQueryRepository(em);
    }

    @Bean
    public JpaProductQueryRepository jpaProductQueryRepository(){
        return new JpaProductQueryRepository(em);
    }
}
