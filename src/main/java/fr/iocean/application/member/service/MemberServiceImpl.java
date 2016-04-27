package fr.iocean.application.member.service;

import fr.iocean.application.member.repository.MemberRepository;
import fr.iocean.application.member.repository.MemberRepositoryCustom;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by utilisateur on 27/04/2016.
 */
@Getter
@Setter
public class MemberServiceImpl {

    @Autowired
    private MemberRepository memberRepository ;

    @Autowired
    private MemberRepositoryCustom memberRepositoryImpl ;





}
