package studycafe.studycaferenewal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studycafe.studycaferenewal.repository.JpaMemberRepository;
import studycafe.studycaferenewal.repository.MemberRepository;
import studycafe.studycaferenewal.service.MemberService;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository){
        return new MemberService(memberRepository);
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

}
