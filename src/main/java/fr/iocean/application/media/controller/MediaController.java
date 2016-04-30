package fr.iocean.application.media.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import fr.iocean.application.author.model.Author;
import fr.iocean.application.author.service.AuthorService;
import fr.iocean.application.controller.AbstractController;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.service.MediaService;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.service.AbstractService;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@RestController
@RequestMapping("/api/medias")
@Transactional
public class MediaController extends AbstractController<Media>{

	@Autowired
	MediaService mediaServiceImpl;
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Media> search(@RequestParam(value="page",required=false) Integer page, @RequestParam(value="title",required=false) String title, @RequestParam(value="author",required=false) String authorName,
			@RequestParam(value="mediaType",required=false) String mediaType ) {
		return mediaServiceImpl.search(page, title, authorName, mediaType);
	}

	@RequestMapping(value="/size",method=RequestMethod.GET)
	public String size(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="title",required=false) String title, @RequestParam(value="authorName",required=false) String authorName, @RequestParam(value="mediaType",required=false) String mediaType){
		
		return mediaServiceImpl.size(page, title, authorName, mediaType);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Media media) {
		
		Author author = authorService.findByFirstNameAndLastName(media.getAuthor().getFirstName(), media.getAuthor().getLastName());	
		
		if (author != null) {			
			media.setAuthor(author);
		}
		service.create(media);
	}

	@Override
	protected AbstractService<Media> getService() {
		return (AbstractService<Media>) mediaServiceImpl;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Media findOneById(@PathVariable Long id) {
		return super.findOneById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Media> findAll() {
		return super.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@PathVariable Long id, @RequestBody @Valid Media resource) {
		super.update(id, resource);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}
	
}
