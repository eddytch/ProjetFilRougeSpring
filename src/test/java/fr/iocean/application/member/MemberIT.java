package fr.iocean.application.member;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.member.service.MemberServiceImpl;

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
		this.mockMvc.perform(get("/api/members/search?lastName=wall"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	@Test
	public void testGetMembersSearch5() throws Exception{
		this.mockMvc.perform(get("/api/members/search?name=jack"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1))) ;
	}
	
	
	
	/*
	@Test
	public void testGetMembers() throws Exception{
		this.mockMvc.perform(get("/members")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))) ;
	}
	*/

}
