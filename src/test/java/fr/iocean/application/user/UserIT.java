package fr.iocean.application.user;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.user.model.User;
import fr.iocean.application.user.service.UserService;

public class UserIT extends IntegrationTest {

	@Autowired
	UserService userService;

	@Test
	@WithMockUser
	public void testCreate() throws Exception {
		User u = new User();
		u.setId(3L);
		u.setLogin("user02");
		u.setPassword("user02");

		this.mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u))).andExpect(status().isCreated());

		this.mockMvc.perform(get("/api/user"))
				// .andDo(MockMvcResultHandlers.print())//pr afficher la requete
				.andExpect(jsonPath("$", hasSize(3))).andExpect(status().isOk());
	}

//	@Test
//	@WithMockUser
//	public void testCreatePreconditionFailed() throws Exception {
//		User u = new User();
//		u.setId(2L);
//		u.setLogin("1234");
//		u.setPassword("1234");
//
//		this.mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//					.content(jsonHelper.serialize(u)))
//					.andDo(MockMvcResultHandlers.print())
//				 	.andExpect(jsonPath("$[*].field",containsInAnyOrder("login","password")))
//					.andExpect(status().isPreconditionFailed());
//	}

	@Test
	@WithMockUser
	public void testFindOneById() throws Exception {
		Long id = 1L;
		User u = userService.findOneById(id);
		Assert.assertEquals(id, u.getId());

		this.mockMvc.perform(get("/api/user/1")).andExpect(jsonPath("$.id").value(1)).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void testFindAll() throws Exception {
		this.mockMvc.perform(get("/api/user")).andExpect(jsonPath("$", hasSize(2))).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void testUpdate() throws Exception {
		User u = userService.findOneById(new Long(1));
		Assert.assertEquals(new Long(1), u.getId());
		u.setPassword("0147");

		this.mockMvc.perform(put("/api/user/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u))).andExpect(status().isOk());

		this.mockMvc.perform(get("/api/user/1")).andExpect(jsonPath("$.password").value(u.getPassword()))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void testDelete() throws Exception {
		User u = userService.findOneById(1L);
		Assert.assertEquals(new Long(1), u.getId());

		this.mockMvc.perform(delete("/api/user/1")).andExpect(status().isOk());

		this.mockMvc.perform(get("/api/user/1"))
				// .andExpect(status().isOk());
				.andExpect(status().is4xxClientError());
	}
}
