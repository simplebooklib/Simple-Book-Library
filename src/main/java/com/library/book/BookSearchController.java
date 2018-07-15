package com.library.book;

import com.library.book.history.SearchHistoryService;
import com.library.book.mark.BookMark;
import com.library.book.mark.BookMarkService;
import com.library.config.CategoryProperty;
import com.library.search.KakaoBookSearcher;
import com.library.search.SearchRequest;
import com.library.search.SearchResponse;
import com.library.security.SimpleBookLibraryUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

/**
 * 도서 관련 Controller
 */
@Controller
public class BookSearchController {

	private final CategoryProperty categoryProperty;
	private final KakaoBookSearcher bookSearcher;
	private final BookMarkService bookMarkService;
	private final SearchHistoryService searchHistoryService;

	public BookSearchController(CategoryProperty categoryProperty, KakaoBookSearcher bookSearcher,
			BookMarkService bookMarkService, SearchHistoryService searchHistoryService) {
		this.categoryProperty = categoryProperty;
		this.bookSearcher = bookSearcher;
		this.bookMarkService = bookMarkService;
		this.searchHistoryService = searchHistoryService;
	}

	/**
	 * 책 검색
	 */
	@GetMapping(value = "/search")
	public String search(@RequestHeader("Host") String host,
			@ModelAttribute("bookSearchModel") BookSearchRequest bookSearchRequest,
			Authentication authentication, ModelMap modelMap) {
		int acctNo = ((SimpleBookLibraryUser) authentication.getPrincipal()).getAcctNo();

		modelMap.addAttribute("sorts", SearchRequest.Sort.values());
		modelMap.addAttribute("targets", SearchRequest.Target.values());
		modelMap.addAttribute("catNames", categoryProperty.getCategoryMap().keySet());
		modelMap.addAttribute("categories", categoryProperty.getCategoryMap());

		Optional.ofNullable(bookSearchRequest.getQuery())
				.ifPresent(query -> {
					SearchRequest searchRequest = bookSearchRequest.toSearchRequest(categoryProperty);
					SearchResponse search = bookSearcher.search(searchRequest);
					modelMap.addAttribute("result", search);

					String uri = bookSearchRequest.toUri("http://" + host + "/search");
					searchHistoryService.addSearchHistoryAsync(acctNo, query, uri);
				});
		return "search";
	}

	/**
	 * 책 상세조회
	 */
	@GetMapping(value = "/books/{isbn}")
	public String detail(@PathVariable(value = "isbn") String isbn,
			Authentication authentication, ModelMap modelMap) {
		int acctNo = ((SimpleBookLibraryUser) authentication.getPrincipal()).getAcctNo();

		Optional<BookMark> bookMark = bookMarkService.findByBookMark(acctNo, isbn);
		bookMark.ifPresent(bm -> modelMap.addAttribute("bookmark", bm));

		modelMap.addAttribute("result", bookSearcher.getBookInfo(isbn));
		return "detail";
	}

}
