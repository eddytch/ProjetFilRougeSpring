package fr.iocean.application.member.repository;

import fr.iocean.application.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by utilisateur on 27/04/2016.
 */
public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom{ }
