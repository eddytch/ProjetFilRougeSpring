package fr.iocean.application.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.controller.AbstractController;
import fr.iocean.application.service.AbstractService;
import fr.iocean.application.user.model.User;
import fr.iocean.application.user.service.UserService;
import lombok.Getter;
import lombok.Setter;

@RestController
@Getter
@Setter
@RequestMapping(value = "/api/user")
public class UserController extends AbstractController<User> {

	@Autowired
	private UserService userService;

	@Override
	protected AbstractService<User> getService() {
		return userService;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid User resource) {
		super.create(resource);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public User findOneById(@PathVariable Long id) {
		return super.findOneById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> findAll() {
		return super.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@PathVariable Long id, @RequestBody @Valid User resource) {
		super.update(id, resource);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}

}
