package com.library.book.mark;

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
public class BookMarkRepositoryTest {

	@Autowired BookMarkRepository bookMarkRepository;

	@Test
	public void 북마크_카운트_조회() throws Exception {
		bookMarkRepository.countByAcctNo(10);
	}

}