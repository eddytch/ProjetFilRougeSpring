package fr.iocean.application.member.repository;

import fr.iocean.application.member.model.Member;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Created by utilisateur on 27/04/2016.
 */
public interface MemberRepositoryCustom {

    public PageImpl<Member> search(Pageable pageable, Long id, String firstName, String lastName, String email);
}
