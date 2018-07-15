package com.library;

import com.library.security.SimpleBookLibraryUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Slf4j
@RunWith(SpringRunner.class)
@WithMockUser(username = "test_user", password = "1234", roles = "USER")
@AutoConfigureMockMvc
@SpringBootTest(classes = { BookLibraryApplication.class })
public abstract class WebAbstractTest {

	@Autowired protected Filter springSecurityFilterChain;
	@Autowired protected WebApplicationContext webApplicationContext;
	protected MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilters(springSecurityFilterChain)
				.apply(springSecurity())
				.build();
	}

	public SimpleBookLibraryUser userDetails() {
		List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		return new SimpleBookLibraryUser(1, "test_user", "1234", authorities);
	}

}
