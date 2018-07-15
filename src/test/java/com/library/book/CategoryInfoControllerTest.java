package com.library.book;

import com.library.WebAbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class CategoryInfoControllerTest extends WebAbstractTest {

	@Test
	@WithUserDetails("test_user")
	public void getCategories() throws Exception {
		this.mockMvc.perform(get("/categories"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}