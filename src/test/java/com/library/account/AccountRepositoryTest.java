package com.library.account;

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
public class AccountRepositoryTest {

	@Autowired AccountRepository accountRepository;

	@Test
	public void findByAcctId() throws Exception {
		accountRepository.findByAcctId("test_id");
	}

}