package com.library.search;

/**
 * 책 검색 interface
 */
public interface BookSearcher<I extends SearchRequest, O extends SearchResponse> {

	O search(I input);

	SearchResponse.Document getBookInfo(String id);

}
