package com.library.config;

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
public class CategoryPropertyTest {

	@Autowired CategoryProperty categoryProperty;

	@Test
	public void name() throws Exception {
		log.info("categoryProperty: {}", categoryProperty);
		Assert.assertNotNull(categoryProperty);
		Assert.assertNotNull(categoryProperty.getCategories());
		Assert.assertTrue(categoryProperty.getCategories().size() > 0);

	}
}