package com.library.book.history;

import com.library.BookLibraryApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BookLibraryApplication.class })
public class SearchHistoryRepositoryTest {

	@Autowired SearchHistoryRepository searchHistoryRepository;

	@Test
	public void findByAcctNoOrderByCreateDateDesc() throws Exception {
		searchHistoryRepository.findByAcctNoOrderBySearchHistoryNoDesc(10);
	}

}