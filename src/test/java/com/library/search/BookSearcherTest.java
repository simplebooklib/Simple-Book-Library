package com.library.search;

import com.library.BookLibraryApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BookLibraryApplication.class })
public class BookSearcherTest {

	@Autowired List<BookSearcher> bookSearchers;

	@Test
	public void name() throws Exception {

		for (BookSearcher bookSearcher : bookSearchers) {
			//bookSearcher.search()
		}

	}
}