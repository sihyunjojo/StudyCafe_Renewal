package studycafe.studycaferenewal;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studycafe.studycaferenewal.repository.JpaMemberRepository;
import studycafe.studycaferenewal.repository.JpaQueryMemberRepository;
import studycafe.studycaferenewal.repository.MemberRepository;
import studycafe.studycaferenewal.service.*;

import javax.persistence.EntityManager;
import javax.swing.*;

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
    public LoginService loginService() {
        return new SpringDataJpaLoginService(jpaMemberRepository);
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaQueryMemberRepository(em);
    }

}
