package fr.iocean.application.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.repository.MemberRepository;
import fr.iocean.application.service.AbstractService ;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by utilisateur on 27/04/2016.
 */
@Getter
@Setter
@Service
@Transactional
public class MemberServiceImpl extends AbstractService<Member> {

    @Autowired
    private MemberRepository memberRepository ;
   
    @Autowired
    private Pageable pageableImpl ;


    public Member findOne(Long id){
        return memberRepository.findOne(id) ;
    }

    public Page<Member> search(Pageable pageable, Long id, String firstName, String lastName, String email){
    	return memberRepository.search(pageable, id, firstName, lastName, email);
    }
    
//    public String size(Long id, String firstName, String lastName, String email){
//    	String jsonSize = "{ size :" ;
//    	String jsonPages = ", pages : " ;
//    	PageImpl<Member> members = pageImpl(null, id, firstName, lastName, email) ;
//    	jsonSize+=members.getTotalElements() ;
//    	jsonSize+=(jsonPages+members.getTotalPages()+ " }") ;
//		return jsonSize;
//    }
    
//    @Transactional
//    private PageImpl<Member> pageImpl(Integer pageNumber, Long id, String firstName, String lastName, String email){
//    	if(pageNumber != null)
//    		((PageableImpl) pageableImpl).setCurrentPage(pageNumber);
//        PageImpl<Member> members = memberRepositoryImpl.search(pageableImpl,id,firstName,lastName,email);
//        return members ;
//    }

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
