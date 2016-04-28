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
import org.springframework.test.context.jdbc.Sql;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.user.model.User;
import fr.iocean.application.user.service.UserService;

@Sql("classpath:db/test-user-data.sql")
public class UserIT extends IntegrationTest {

	@Autowired
	UserService userService;

	@Test
	public void testCreate() throws Exception {
		User u = new User();
		u.setId(3L);
		u.setLogin("user02");
		u.setPassword("user02");

		this.mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u))).andExpect(status().isCreated());

		this.mockMvc.perform(get("/api/users"))
				// .andDo(MockMvcResultHandlers.print())//pr afficher la requete
				.andExpect(jsonPath("$", hasSize(3))).andExpect(status().isOk());
	}

//	@Test
//	public void testCreatePreconditionFailed() throws Exception {
//		User u = new User();
//		u.setId(2L);
//		u.setLogin("test.fr");
//		u.setPassword("7894");
//
//		this.mockMvc
//				.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
//						.content(jsonHelper.serialize(u)))
//				.andDo(MockMvcResultHandlers.print())
//				// .andExpect(jsonPath("$[*].field",
//				// containsInAnyOrder("login","name","password")))
//				.andExpect(status().isPreconditionFailed());
//	}

	@Test
	public void testFindOneById() throws Exception {
		Long id = 1L;
		User u = userService.findOneById(id);
		Assert.assertEquals(id, u.getId());

		this.mockMvc.perform(get("/api/users/1")).andExpect(jsonPath("$.id").value(1)).andExpect(status().isOk());
	}

	@Test
	public void testFindAll() throws Exception {
		this.mockMvc.perform(get("/api/users")).andExpect(jsonPath("$", hasSize(2))).andExpect(status().isOk());
	}

	@Test
	public void testUpdate() throws Exception {
		User u = userService.findOneById(new Long(1));
		Assert.assertEquals(new Long(1), u.getId());
		u.setPassword("0147");

		this.mockMvc.perform(put("/api/users/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u))).andExpect(status().isOk());

		this.mockMvc.perform(get("/api/users/1")).andExpect(jsonPath("$.password").value(u.getPassword()))
				.andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception {
		User u = userService.findOneById(1L);
		Assert.assertEquals(new Long(1), u.getId());

		this.mockMvc.perform(delete("/api/users/1")).andExpect(status().isOk());

		this.mockMvc.perform(get("/api/users/1"))
				// .andExpect(status().isOk());
				.andExpect(status().is4xxClientError());
	}
}
