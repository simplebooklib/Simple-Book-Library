package com.library.book.mark;

import com.library.search.SearchResponse.Document;
import com.library.security.SimpleBookLibraryUser;
import com.library.support.PageResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 북마크 관련 Controller
 */
@Controller
public class BookMarkController {

	private final BookMarkService bookMarkService;

	public BookMarkController(BookMarkService bookMarkService) {
		this.bookMarkService = bookMarkService;
	}

	/**
	 * 북마크 조회.
	 */
	@GetMapping(value = "/bookmark")
	public String getBookmarkInfo(Authentication authentication,
			@ModelAttribute("bookMarkRequest") BookMarkRequest bookMarkRequest, ModelMap modelMap) {
		int acctNo = ((SimpleBookLibraryUser) authentication.getPrincipal()).getAcctNo();

		PageRequest pageRequest = new PageRequest(bookMarkRequest.getPage() - 1, bookMarkRequest.getSize(), bookMarkRequest.toSort());
		Page<Document> documents = bookMarkService.findByAcctNo(acctNo, pageRequest);

		PageResultResponse<Document> response = new PageResultResponse<>();
		response.setPage(pageRequest.getPageNumber() + 1);
		response.setSize(pageRequest.getPageSize());
		response.setCount(documents.getTotalElements());
		response.setElements(documents.getContent());
		modelMap.addAttribute("result", response);
		return "bookmark";
	}

	/**
	 * 북마크 설정/삭제
	 */
	@ResponseBody
	@PostMapping(value = "/bookmark/{isbn}")
	public void setBookmark(Authentication authentication, @PathVariable(value = "isbn") String isbn,
			@RequestParam(value = "mark") boolean mark) {
		int acctNo = ((SimpleBookLibraryUser) authentication.getPrincipal()).getAcctNo();
		if (mark) {
			bookMarkService.create(acctNo, isbn);
		} else {
			bookMarkService.delete(acctNo, isbn);
		}
	}

}
