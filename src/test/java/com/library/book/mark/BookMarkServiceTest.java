package com.library.book.mark;

import com.library.BookLibraryApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BookLibraryApplication.class })
public class BookMarkServiceTest {

	@Autowired BookMarkService bookMarkService;

	@Test
	public void findByAcctNo() throws Exception {
		bookMarkService.findByAcctNo(10, new PageRequest(1, 10));
	}

	@Test
	public void findByBookMark() throws Exception {
		bookMarkService.findByBookMark(10, "9781772451153");
	}

	@Test
	public void create() throws Exception {
		bookMarkService.create(10, "9781772451153");
	}

	@Test
	public void delete() throws Exception {
		bookMarkService.delete(10, "9781772451153");
	}

}