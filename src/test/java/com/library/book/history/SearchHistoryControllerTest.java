package com.library.book.history;

import com.library.WebAbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class SearchHistoryControllerTest extends WebAbstractTest {

	@Test
	@WithUserDetails("test_user")
	public void getHistory() throws Exception {
		this.mockMvc.perform(get("/history").secure(true)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}