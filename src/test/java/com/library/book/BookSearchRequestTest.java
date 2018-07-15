package com.library.book;

import com.library.config.CategoryProperty;
import com.library.search.SearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class BookSearchRequestTest {

	@Test
	public void toUri() throws Exception {
		BookSearchRequest request = new BookSearchRequest();
		request.setQuery("docker");
		String url = request.toUri("http://localhost:8080/search");

		assertNotNull(url);
		assertEquals(url, "http://localhost:8080/search?query=docker&page=1&sort&size=10&target&catName&catNo");
	}

	@Test
	public void toSearchRequest() throws Exception {
		BookSearchRequest request = new BookSearchRequest();
		request.setQuery("docker");

		SearchRequest searchRequest = request.toSearchRequest(new CategoryProperty());
		assertNotNull(searchRequest);
		assertEquals(searchRequest.getQuery(), "docker");
	}

}