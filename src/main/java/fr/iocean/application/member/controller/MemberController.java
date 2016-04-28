package fr.iocean.application.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.controller.AbstractController;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.service.MemberService;
import fr.iocean.application.service.AbstractService;
import lombok.Getter;
import lombok.Setter;

@RestController
@Getter
@Setter
@RequestMapping("/members")
public class MemberController extends AbstractController<Member>{
	
	
	@Autowired
	private MemberService memberServiceImpl ;
	
	@RequestMapping(method=RequestMethod.GET)
	public  List<Member> search(@PathVariable int page, @PathVariable Long id, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String email){
		return memberServiceImpl.search(page, id, firstName, lastName, email);
		
	}

	@Override
	protected Class<Member> getEntityClass() {
		return Member.class;
	}

	@Override
	protected AbstractService<Member> getService() {
		// TODO Auto-generated method stub
		return (AbstractService<Member>) memberServiceImpl;
	}

	
}
