package com.library.book.history;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 최근 검색 히스토리
 * 과거의 데이터부터 삭제하는 방식으로 구현.
 */
@Service
public class SearchHistoryService {

	private final SearchHistoryRepository searchHistoryRepository;

	public SearchHistoryService(SearchHistoryRepository searchHistoryRepository) {
		this.searchHistoryRepository = searchHistoryRepository;
	}

	/**
	 * 검색 히스토리 추가. 오래된 검색 히스토리는 삭제한다.
	 */
	@Async("threadPoolTaskExecutor")
	public void addSearchHistoryAsync(int acctNo, String query, String url) {
		searchHistoryRepository.findByAcctNoOrderBySearchHistoryNoDesc(acctNo).stream()
				.skip(5)
				.findAny()
				.ifPresent(searchHistoryRepository::delete);

		SearchHistory searchHistoryParam = new SearchHistory();
		searchHistoryParam.setAcctNo(acctNo);
		searchHistoryParam.setQuery(query);
		searchHistoryParam.setUrl(url);
		searchHistoryRepository.save(searchHistoryParam);
	}

	/**
	 * 최근 검색 기록 조회
	 *
	 * @param acctNo 계정번호
	 * @return 검색기록
	 */
	public List<SearchHistory> getSearchHistories(int acctNo) {
		return searchHistoryRepository.findByAcctNoOrderBySearchHistoryNoDesc(acctNo);
	}

}
