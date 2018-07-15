package com.library.book.history;

import com.library.security.SimpleBookLibraryUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 최근 검색 관련 Controller
 */
@Controller
public class SearchHistoryController {

	private final ModelMapper modelMapper;
	private final SearchHistoryService searchHistoryService;

	public SearchHistoryController(ModelMapper modelMapper, SearchHistoryService searchHistoryService) {
		this.modelMapper = modelMapper;
		this.searchHistoryService = searchHistoryService;
	}

	/**
	 * 최근 검색 히스토리 조회
	 */
	@ResponseBody
	@GetMapping(value = "/history")
	public List<SearchHistoryResponse> getHistory(Authentication authentication) {
		int acctNo = ((SimpleBookLibraryUser) authentication.getPrincipal()).getAcctNo();
		List<SearchHistory> searchHistories = searchHistoryService.getSearchHistories(acctNo);
		return modelMapper.map(searchHistories, new TypeToken<List<SearchHistoryResponse>>() {
		}.getType());
	}

}
