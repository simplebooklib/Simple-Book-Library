package com.library.book;

import com.library.config.CategoryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.library.config.CategoryProperty.Category;

@RestController
public class CategoryInfoController {

	private final CategoryProperty categoryProperty;

	@Autowired
	public CategoryInfoController(CategoryProperty categoryProperty) {
		this.categoryProperty = categoryProperty;
	}

	/**
	 * 카테고리 정보 조회
	 */
	@GetMapping(value = "/categories")
	public Map<String, List<Category>> getCategories() {
		return categoryProperty.getCategoryMap();
	}

}
