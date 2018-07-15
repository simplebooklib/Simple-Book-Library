package com.library.book;

import com.library.WebAbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class BookSearchControllerTest extends WebAbstractTest {

	@Test
	@WithUserDetails("test_user")
	public void search() throws Exception {
		this.mockMvc.perform(get("/search")
				.header("host", "localhost:8080"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("test_user")
	public void detail() throws Exception {
		this.mockMvc.perform(get("/detail/9781772451153"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}