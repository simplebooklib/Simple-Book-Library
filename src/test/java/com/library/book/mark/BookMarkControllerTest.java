package com.library.book.mark;

import com.library.WebAbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class BookMarkControllerTest extends WebAbstractTest {

	@Test
	@WithUserDetails("test_user")
	public void getBookmarkInfo() throws Exception {
		this.mockMvc.perform(get("/bookmark"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("test_user")
	public void setBookmark() throws Exception {
		this.mockMvc.perform(post("/bookmark/9781772451153")
				.with(csrf())
				.param("mark", "true")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}