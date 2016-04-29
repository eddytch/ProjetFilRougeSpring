package fr.iocean.application.member;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.service.MemberServiceImpl;
import fr.iocean.application.user.model.User;

//@Sql("classpath:db/test-member-data.sql")
public class MemberIT extends IntegrationTest {
	
	@Autowired
	MemberServiceImpl memberServiceImpl;
	
	@Test
	public void testGetMembers() throws Exception{
		this.mockMvc.perform(get("/api/members"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(2))) ;
		/*
		.andExpect(jsonPath("$[0].country").value("France"))
		.andExpect(jsonPath("$[0].name_street").value("rue des coquelicots"))
		.andExpect(jsonPath("$[0].num_street").value("2"))
		.andExpect(jsonPath("$[0].cp_town").value("35000"))
		.andExpect(jsonPath("$[0].town").value("Rennes"))
		.andExpect(jsonPath("$[0].amount").value(3.24)) ;
		
		/*
		amount,birthday,email,lastname,name,payment_date
		'France',,'2','35000','Rennes',3.24,timestamp '1992-10-10','','jacquet','eddy',now()
		*/
	}
	
	@Test
	public void testGetMembersSearch() throws Exception{
		this.mockMvc.perform(get("/api/members/search?page=0&email=jacquet"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	@Test
	public void testGetMembersSearch2() throws Exception{
		this.mockMvc.perform(get("/api/members/search?page=0"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(2))) ;
	}
	
	@Test
	public void testGetMembersSearch3() throws Exception{
		this.mockMvc.perform(get("/api/members/search?id=1000"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	@Test
	public void testGetMembersSearch4() throws Exception{
		this.mockMvc.perform(get("/api/members/search?lastname=wall"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	@Test
	public void testGetMembersSearch5() throws Exception{
		this.mockMvc.perform(get("/api/members/search?firstname=jack"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	@Test
	public void testGetMembersSize() throws Exception{
		this.mockMvc.perform(get("/api/members/size"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.size").value(2))
					.andExpect(jsonPath("$.pages").value(1));
	}
	
	@Test
	@WithMockUser
	public void testGetMemberByPage() throws Exception {
		this.mockMvc.perform(get("/api/members/search?page=0")).andExpect(jsonPath("$", hasSize(2))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void testGetMemberByPage2() throws Exception {
		this.mockMvc.perform(get("/api/members/search?page=1")).andExpect(jsonPath("$", hasSize(0))).andExpect(status().isOk());
	}
	
	
	@Test
	@WithMockUser
	public void testFindOneById() throws Exception {
		Long id = 1000L;
		Member m = memberServiceImpl.findOneById(id);
		Assert.assertEquals(id, m.getId());

		this.mockMvc.perform(get("/api/members/1000")).andExpect(jsonPath("$.id").value(1000)).andExpect(status().isOk());
	}
	
	
	
	
	@Test
	@WithMockUser
	public void testCreate() throws Exception {
		Member m = new Member();
		m.setId(3000L);
		m.setFirstname("dimitri");
		m.setLastname("lanoe");
		m.setBirthday(new Date(1992, 10, 22));
		m.setEmail("jac@j.fr");

		this.mockMvc.perform(post("/api/members").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(m))).andExpect(status().isCreated());

		this.mockMvc.perform(get("/api/members"))
				// .andDo(MockMvcResultHandlers.print())//pr afficher la requete
				.andExpect(jsonPath("$", hasSize(3))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void testUpdate() throws Exception {
		Member m = memberServiceImpl.findOneById(new Long(1000L));
		m.setFirstname("dimitri");

		this.mockMvc.perform(put("/api/members/1000").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(m))).andExpect(status().isOk());

		this.mockMvc.perform(get("/api/members/1000")).andExpect(jsonPath("$.firstname").value("dimitri"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void testDelete() throws Exception {
		//Member m = memberServiceImpl.findOneById(1001L);

		this.mockMvc.perform(delete("/api/members/1001")).andExpect(status().isOk());

		this.mockMvc.perform(get("/api/members/1001"))
				// .andExpect(status().isOk());
				.andExpect(status().is4xxClientError());
	}
	
	
	
	/*
	@Test
	public void testGetMembers() throws Exception{
		this.mockMvc.perform(get("/members")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))) ;
	}
	*/

}
