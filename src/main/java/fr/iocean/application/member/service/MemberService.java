package fr.iocean.application.member.service;

import java.util.List;

import fr.iocean.application.member.model.Member;

public interface MemberService {

	List<Member> search(Integer pageNumber, Long id, String firstName, String lastName, String email) ;
	String size(Long id, String firstName, String lastName, String email) ;
}
