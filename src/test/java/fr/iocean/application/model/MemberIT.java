package fr.iocean.application.model;

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
import fr.iocean.application.user.model.User;

@Sql("classpath:test-user-data.sql")
public class MemberIT extends IntegrationTest {
	
	
	@Test
	public void testGetMembers() throws Exception{
		this.mockMvc.perform(get("/members")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))) ;
	}

}
