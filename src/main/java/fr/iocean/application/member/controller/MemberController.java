package fr.iocean.application.member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.controller.AbstractController;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.service.MemberServiceImpl;
import fr.iocean.application.service.AbstractService;
import lombok.Getter;
import lombok.Setter;

@RestController
@Getter
@Setter
@RequestMapping("/api/members")
public class MemberController extends AbstractController<Member> {

	@Autowired
	private MemberServiceImpl memberServiceImpl;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Page<Member> search(@RequestParam(value="page",required=false, defaultValue = "0") Integer page, @RequestParam(value="id",required=false) Long id, @RequestParam(value="firstname",required=false) String firstname,
			@RequestParam(value="lastname",required=false) String lastname, @RequestParam(value="email",required=false) String email) {
		Pageable pageable = new PageRequest(page, 10);
		return memberServiceImpl.search(pageable, id, firstname, lastname, email);

	}

//	@RequestMapping(value="/size",method=RequestMethod.GET)
//	public String size(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="id",required=false) Long id, @RequestParam(value="firstname",required=false) String firstname, @RequestParam(value="lastname",required=false) String lastname, @RequestParam(value="email",required=false) String email){
//		
//		return memberServiceImpl.size(id, firstname, lastname, email);
//	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Member findOneById(@PathVariable Long id) {
		return super.findOneById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Page<Member> findAll(@RequestParam(value="page",required=false, defaultValue = "0") Integer page,
			@RequestParam(value="size",required=false, defaultValue = "10") Integer size) {
		Pageable pageable = new PageRequest(page, size);
		return super.findAll(pageable);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Member resource) {
		super.create(resource);
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
	protected AbstractService<Member> getService() {
		return memberServiceImpl;
	}
	
}
