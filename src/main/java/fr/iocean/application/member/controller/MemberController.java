package fr.iocean.application.member.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.controller.AbstractController;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.repository.MemberRepository;
import fr.iocean.application.member.service.MemberService;
import fr.iocean.application.service.AbstractService;
import fr.iocean.application.user.model.User;
import lombok.Getter;
import lombok.Setter;

@RestController
@Getter
@Setter
@RequestMapping("/api/members")
public class MemberController extends AbstractController<Member>{
	
	
	@Autowired
	private MemberService memberServiceImpl ;
	
	@Autowired
	public  PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/search/",method=RequestMethod.GET)
	public  List<Member> search(@PathVariable int page, @PathVariable Long id, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String email){
		return memberServiceImpl.search(page, id, firstName, lastName, email);
		
	}
	
	@RequestMapping(value="/size/",method=RequestMethod.GET)
	public String size(@PathVariable int page, @PathVariable Long id, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String email){
		return memberServiceImpl.size(id, firstName, lastName, email);
		
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Member findOneById(@PathVariable Long id) {
		return super.findOneById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Member> findAll() {
		return super.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@PathVariable Long id, @RequestBody @Valid Member member) {
		super.update(id, member);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		super.delete(id);
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
