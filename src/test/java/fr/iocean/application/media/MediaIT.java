package fr.iocean.application.media;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.author.model.Author;
import fr.iocean.application.author.service.AuthorService;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.service.MediaService;

public class MediaIT extends IntegrationTest {
	
	@Autowired
	MediaService mediaServiceImpl;
	
	@Autowired
	AuthorService authorService;
	
	@Test
	public void testCreateMedia() throws Exception {
		Author author = new Author();
		author.setFirstName("Jack");
		author.setLastName("Bauer");

		Media media = new Media();		
		media.setTitle("Media 2");
		media.setType(fr.iocean.application.media.model.MediaType.DVD);
		media.setAuthor(author);
		
		this.mockMvc.perform(post("/api/medias").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
					.content(jsonHelper.serialize(media))).andExpect(status().isCreated());
		this.mockMvc.perform(get("/api/medias").contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$", hasSize(3))).andExpect(status().isOk());
	}
	
	@Test
	public void testCreateMediaAuthorUnknown() throws Exception {
		String firstName = "Jack";
		String lastName = "Bauer";
		
		Assert.assertNull(authorService.findByFirstNameAndLastName(firstName, lastName));		
		Author author = new Author();
		author.setFirstName(firstName);
		author.setLastName(lastName);

		Media media = new Media();		
		media.setTitle("Media 2");
		media.setType(fr.iocean.application.media.model.MediaType.DVD);
		media.setAuthor(author);
		
		this.mockMvc.perform(post("/api/medias").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
					.content(jsonHelper.serialize(media))).andExpect(status().isCreated());
		Assert.assertNotNull(authorService.findByFirstNameAndLastName(firstName, lastName));
	}
	
	
	@Test
	@WithMockUser
	public void testGetMediaByPage() throws Exception {
		this.mockMvc.perform(get("/api/medias/search?page=0")).andExpect(jsonPath("$", hasSize(2))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void testGetMediaByPage2() throws Exception {
		this.mockMvc.perform(get("/api/medias/search?page=1")).andExpect(jsonPath("$", hasSize(0))).andExpect(status().isOk());
	}
	
	@Test
	public void testGetMediaSearch() throws Exception{
		this.mockMvc.perform(get("/api/medias/search?page=0&author=rtz"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(0))) ;
	}
	
	@Test
	public void testGetMediaSearch2() throws Exception{
		this.mockMvc.perform(get("/api/medias/search?page=0&author=dia"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(0))) ;
	}
	
	@Test
	public void testGetMediaSearch3() throws Exception{
		this.mockMvc.perform(get("/api/medias/search?page=0&title=1"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	
	@Test
	public void testGetMembersSearch4() throws Exception{
		this.mockMvc.perform(get("/api/medias/search?page=0&=mediaType=CD"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	@Test
	public void testGetMembersSearch5() throws Exception{
		this.mockMvc.perform(get("/api/medias/search?page=0&mediaType=DV"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	

}
