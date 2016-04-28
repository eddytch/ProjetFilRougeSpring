package fr.iocean.application.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;


import java.io.ObjectOutputStream.PutField;

import org.hibernate.event.spi.PostCollectionRecreateEvent;
import org.junit.Test;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.user.model.User;

@Sql("classpath:db/test-member-data.sql")
public class MemberIT extends IntegrationTest {
	
	
	@Test
	public void testGetMembers() throws Exception{
		this.mockMvc.perform(get("/api/members")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))) ;
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
		this.mockMvc.perform(get("api/members/search").param("page","0").param("email","jacquet@gmail.com")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1))) ;
		/*
		.andExpect(jsonPath("$[0].country").value("France"))
		.andExpect(jsonPath("$[0].name_street").value("rue des coquelicots"))
		.andExpect(jsonPath("$[0].num_street").value("2"))
		.andExpect(jsonPath("$[0].cp_town").value("35000"))
		.andExpect(jsonPath("$[0].town").value("Rennes"))
		.andExpect(jsonPath("$[0].amount").value(3.24)) ;
		*/
		
		/*
		amount,birthday,email,lastname,name,payment_date
		'France',,'2','35000','Rennes',3.24,timestamp '1992-10-10','','jacquet','eddy',now()
		*/
	}
	
	/*
	@Test
	public void testGetMembers() throws Exception{
		this.mockMvc.perform(get("/members")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))) ;
	}
	*/

}
