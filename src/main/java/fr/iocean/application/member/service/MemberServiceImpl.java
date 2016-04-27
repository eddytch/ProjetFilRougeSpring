package fr.iocean.application.member.service;

import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.repository.MemberRepository;
import fr.iocean.application.member.repository.MemberRepositoryCustom;
import fr.iocean.application.repository.PageableImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

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


    public Member findOne(Long id){
        return memberRepository.findOne(id) ;
    }

    public List<Member> findAll(int pageNumber, Long id, String firstName, String lastName, String email){
        ArrayList<Member> listMembers = new ArrayList<>() ;
        PageImpl<Member> members = memberRepositoryImpl.search(new PageableImpl(),id,firstName,lastName,email);
        while (members.iterator().hasNext()) {
            Member next = members.iterator().next();
            listMembers.add(next);
        }
        return listMembers ;
    }





}
