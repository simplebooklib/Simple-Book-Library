package com.library.search;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 카카오 책검색 API를 이용한 검색
 */
@Component
public class KakaoBookSearcher implements BookSearcher<SearchRequest, SearchResponse> {

	private final RestTemplate restTemplate;
	private final String url;
	private final HttpEntity<Void> httpEntity;

	public KakaoBookSearcher(RestTemplate restTemplate, @Value("${kakao.api.url}") String url,
			@Value("${kakao.api.rest-key}") String restKey) {
		this.restTemplate = restTemplate;
		this.url = url;

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + restKey);
		this.httpEntity = new HttpEntity<>(headers);
	}

	@Override
	public SearchResponse search(SearchRequest input) {
		URI uri = UriComponentsBuilder.fromUriString(url)
				.queryParam("query", input.getQuery())
				.queryParam("sort", input.getSort())
				.queryParam("page", input.getPage())
				.queryParam("size", input.getSize())
				.queryParam("target", input.getTarget())
				.queryParam("category", input.getCategory())
				.build().toUri();

		return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, SearchResponse.class).getBody();
	}

	@Override
	@Cacheable(cacheManager = "cacheManager", value = "bookInfoCache", key = "#id")
	public SearchResponse.Document getBookInfo(String id) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setQuery(id);
		searchRequest.setTarget(SearchRequest.Target.ISBN);
		return search(searchRequest).getDocuments().get(0);
	}

}
