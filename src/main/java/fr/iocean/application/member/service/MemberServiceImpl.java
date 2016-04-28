package fr.iocean.application.member.service;

import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.repository.MemberRepository;
import fr.iocean.application.member.repository.MemberRepositoryCustom;
import fr.iocean.application.repository.PageableImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.iocean.application.service.AbstractService ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by utilisateur on 27/04/2016.
 */
@Getter
@Setter
@Service
public class MemberServiceImpl extends AbstractService<Member> implements MemberService {

    @Autowired
    private MemberRepository memberRepository ;

    @Autowired
    private MemberRepositoryCustom memberRepositoryImpl ;


    public Member findOne(Long id){
        return memberRepository.findOne(id) ;
    }

    public List<Member> search(int pageNumber, Long id, String firstName, String lastName, String email){
    	ArrayList<Member> listMembers = new ArrayList<>() ;
        PageImpl<Member> members = pageImpl(pageNumber, id, firstName, lastName, email) ;
        while (members.iterator().hasNext()) {
            Member next = members.iterator().next();
            listMembers.add(next);
        }
        return listMembers ;
    }
    
    public List<Member> taille(Long id, String firstName, String lastName, String email){
		return null;
    }
    
    
    private PageImpl<Member> pageImpl(int pageNumber, Long id, String firstName, String lastName, String email){
    	
        PageImpl<Member> members = memberRepositoryImpl.search(new PageableImpl(),id,firstName,lastName,email);
        return members ;
    }

	@Override
	protected Class<Member> getEntityClass() {
		return Member.class;
	}

	@Override
	protected JpaRepository<Member, Long> getJpaRepository() {
		return memberRepository;
	}

}
