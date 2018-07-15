package com.library.search;

import com.library.BookLibraryApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BookLibraryApplication.class })
public class KakaoBookSearcherTest {

	@Autowired KakaoBookSearcher kakaoBookSearcher;

	@Test
	public void search() throws Exception {
		SearchRequest request = new SearchRequest();
		request.setQuery("java");

		SearchResponse response = kakaoBookSearcher.search(request);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getMeta());
		Assert.assertNotNull(response.getDocuments());
		Assert.assertTrue(response.getDocuments().size() > 0);
	}

}