package com.library.book.history;

import com.library.BookLibraryApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BookLibraryApplication.class })
public class SearchHistoryServiceTest {

	@Autowired SearchHistoryService searchHistoryService;

	@Test
	public void addSearchHistoryAsync() throws Exception {
		searchHistoryService.addSearchHistoryAsync(10, "test", "http://localhost:8080/search");
	}

	@Test
	public void getSearchHistories() throws Exception {
		searchHistoryService.getSearchHistories(10);
	}

}