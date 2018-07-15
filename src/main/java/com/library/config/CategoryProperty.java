package com.library.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;

/**
 * 책 카테고리 정보 클래스
 */
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "book-category")
public class CategoryProperty implements InitializingBean {

	@Setter(value = AccessLevel.NONE)
	private final List<Category> categories = new ArrayList<>();

	@Setter(value = AccessLevel.NONE)
	private final Map<String, List<Category>> categoryMap = new LinkedHashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		categoryMap.putAll(categories.stream()
				.collect(Collectors.groupingBy(Category::getCatName, LinkedHashMap::new, Collectors.toList())));

		Collections.unmodifiableMap(categoryMap);
		Collections.unmodifiableList(categories);
	}

	@Data
	@EqualsAndHashCode(of = "catNo")
	public static class Category {
		private String catName;
		private String subCatName;
		private int catNo;
	}

	public Category valueOf(int catNo) {
		return categories.stream()
				.filter(c -> Objects.equals(c.getCatNo(), catNo))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException(catNo + "에 해당하는 카테고리 정보가 존재하지 않습니다."));
	}

}
