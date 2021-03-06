Simple Book Library
===================

Spring Boot 기반으로 개발되어 있습니다. 
BookLibraryApplication.java 파일을 실행한 후 http://localhost:8080 으로 접속해서 확인할 수 있습니다
메모리 DB 기반으로 실행되기 때문에 재시작시 북마크, 최근검색과 같은 기록들은 사라집니다.

최초 실행시 data.sql로 기본 사용자 정보가 등록되고, 로그인 페이지에서 미리 계정이 입력되어 있습니다.

주요 dependencies
 - Spring Boot
 - Spring Security
 - Spring Data JPA
 - Sitemesh
 - EhCache
 - Lombok
 
 로그인 페이지
 ![alt text](./intro/login.png)
 
 
 검색 페이지
 ![alt text](./intro/search.png)
 
 
 상세 페이지
 ![alt text](./intro/detail.png)
 
 
 북마크
 ![alt text](./intro/bookmark.png)
 
 
 최근 검색
 ![alt text](./intro/history.png)