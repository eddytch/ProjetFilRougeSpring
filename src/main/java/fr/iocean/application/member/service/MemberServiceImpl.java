package fr.iocean.application.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.repository.MemberRepository;
import fr.iocean.application.member.repository.MemberRepositoryCustom;
import fr.iocean.application.repository.PageableImpl;
import fr.iocean.application.service.AbstractService ;
import lombok.Getter;
import lombok.Setter;

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
        PageImpl<Member> members = pageImpl(id, firstName, lastName, email) ;
        while (members.iterator().hasNext()) {
            Member next = members.iterator().next();
            listMembers.add(next);
        }
        return listMembers ;
    }
    
    public String size(Long id, String firstName, String lastName, String email){
    	String jsonSize = "{ size :" ;
    	String jsonPages = ", pages : " ;
    	PageImpl<Member> members = pageImpl(id, firstName, lastName, email) ;
    	jsonSize+=members.getTotalElements() ;
    	jsonSize+=(jsonPages+members.getTotalPages()+ " }") ;
		return jsonSize;
    }
    
    
    private PageImpl<Member> pageImpl(Long id, String firstName, String lastName, String email){
    	
        PageImpl<Member> members = memberRepositoryImpl.search(new PageableImpl(),id,firstName,lastName,email);
        return members ;
    }

	@Override
	protected JpaRepository<Member, Long> getJpaRepository() {
		return memberRepository;
	}
	
	public Member create(Member member) {
		return memberRepository.save(member);
	}

	public Member update(Long id, Member member) {
		findOneById(id);
		member.setId(id);
		return memberRepository.save(member);
	}

}
