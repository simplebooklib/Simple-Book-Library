<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<jsp:directive.include file="/WEB-INF/system/taglibs.jsp"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Simple Book Library</title>
    <style type="text/css">
        dl {
            width: 100%;
            overflow: hidden;
            padding: 0;
            margin: 0
        }

        dt {
            float: left;
            width: 10%;
            padding: 2px;
            margin: 0
        }

        dd {
            float: left;
            width: 90%;
            padding: 2px;
            margin: 0
        }
    </style>
</head>
<body>
<div class="container">

    <h1>검색</h1>

    <div class="container">
        <form:form action="/search" method="get" modelAttribute="bookSearchModel">
            <form:hidden path="page" />
            <form:hidden path="size" />
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="sort">정렬기준</label>
                </div>
                <form:select id="sort" path="sort" cssClass="custom-select">
                    <form:option label="선택" value=""/>
                    <form:options items="${sorts}" itemValue="code" itemLabel="desc"/>
                </form:select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="target">검색필드</label>
                </div>
                <form:select id="target" path="target" cssClass="custom-select">
                    <form:option label="선택" value=""/>
                    <form:options items="${targets}" itemValue="code" itemLabel="desc"/>
                </form:select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="catName">대분류</label>
                </div>
                <form:select id="catName" path="catName" cssClass="custom-select">
                    <form:option label="선택" value=""/>
                    <form:options items="${catNames}"/>
                </form:select>
                <div class="input-group-prepend">
                    <label class="input-group-text" for="catNo">중분류</label>
                </div>
                <form:select id="catNo" path="catNo" cssClass="custom-select">
                    <form:option label="선택" value=""/>
                </form:select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">검색어</span>
                </div>
                <form:input path="query" cssClass="form-control" aria-label="검색어" aria-describedby="inputGroup-sizing-default" required="required"/>
            </div>
            <small class="d-block text-center mt-3">
                <input class="btn btn-primary" type="submit" value="검색">
            </small>
        </form:form>
    </div>

    <c:if test="${not empty result.documents}">
        <div class="my-3 p-3 bg-white rounded box-shadow">
            <div class="d-flex justify-content-between border-bottom">
                <div>
                    <h6 class="border-gray pb-2 mb-0">Book List (<fmt:formatNumber value="${result.meta.pageableCount}" pattern=",###" />건)</h6>
                </div>
                <div>
                    <select class="form-control" id="sizeSelect" style="width:200px;">
                        <option value="10">페이지노출 수</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                        <option value="40">40</option>
                        <option value="50">50</option>
                    </select>
                </div>
            </div>
            <c:forEach var="book" items="${result.documents}">
                <div class="media text-muted pt-3" style="border-bottom: 1px solid #dee2e6!important;">
                    <div class="w-25">
                        <c:set var="isbn1" value="${fn:split(book.isbn, ' ')[0]}" />
                        <c:set var="isbn2" value="${fn:split(book.isbn, ' ')[1]}" />
                        <c:url var="detailUrl" value="/books/${empty isbn2 ? isbn1 : isbn2}?${params}"/>
                        <a href="${empty detailUrl}" target="_self">
                            <img data-src="holder.js/32x32?theme=thumb&amp;bg=007bff&amp;fg=007bff&amp;size=1" alt="32x32" class="mr-2 rounded"
                                 src="<c:url value="${not empty book.thumbnail ? book.thumbnail : 'data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2232%22%20height%3D%2232%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%2032%2032%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_1647f49f2e8%20text%20%7B%20fill%3A%23007bff%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A2pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_1647f49f2e8%22%3E%3Crect%20width%3D%2232%22%20height%3D%2232%22%20fill%3D%22%23007bff%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2212.296875%22%20y%3D%2216.9%22%3E32x32%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E'}"/>"
                                 data-holder-rendered="true" style="width: 72px; height: 100px; margin-right: 20px!important;">
                        </a>
                    </div>
                    <div class="w-50">
                        <div class="media-body pb-3 mb-0 small lh-125 border-gray">
                            <div class="container">
                                <div class="row">
                                    <div class="d-flex justify-content-between align-items-center w-100">
                                        <h6><strong class="text-gray-dark"><a href="${detailUrl}" target="_self"><c:out value="${book.title}"/></a></strong></h6>
                                    </div>
                                    <section class="landing">
                                        <div class="container">
                                            <dl class="row">
                                                <dt class="w-25">저자</dt>
                                                <dd class="w-75">
                                                    <c:forEach items="${book.authors}" var="author" varStatus="loop">
                                                        ${author}
                                                        <c:if test="${!loop.last}">,</c:if>
                                                    </c:forEach>
                                                </dd>
                                                <c:if test="${not empty book.translators}">
                                                    <dt class="w-25">역자</dt>
                                                    <dd class="w-75">
                                                        <c:forEach items="${book.translators}" var="translator" varStatus="loop">
                                                            ${translator}
                                                            <c:if test="${!loop.last}">,</c:if>
                                                        </c:forEach>
                                                    </dd>
                                                </c:if>
                                                <dt class="w-25">출판사</dt>
                                                <dd class="w-75">
                                                    <c:out value="${book.publisher}"/> | <fmt:formatDate value="${book.datetime}" type="date"
                                                                                                         pattern="yyyy.MM.dd"/>
                                                </dd>
                                                <dt class="w-25">정가</dt>
                                                <dd class="w-75">
                                                    <del><fmt:formatNumber value="${book.price}" pattern=",000"/>원</del>
                                                    <strong style="font-size: large;"><fmt:formatNumber value="${book.salePrice}" pattern=",000"/></strong>원
                                                </dd>
                                            </dl>
                                        </div>
                                    </section>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="w-50">
                        <span class="align-middle"><c:out value="${book.contents}"/></span>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="row h-100 justify-content-center align-items-center">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${bookSearchModel.page > 1}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/search?page=${bookSearchModel.page - 1}"/>&${params.replaceAll('page=[0-9]*\\&', '')}">Previous</a></li>
                    </c:if>
                    <c:if test="${not result.meta.end}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/search?page=${bookSearchModel.page + 1}"/>&${params.replaceAll('page=[0-9]*\\&', '')}">Next</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </c:if>
</div>

</div>
<script type="text/javascript">
    $(function () {
        // 카테고리 대분류, 중분류 이벤트
        $("#catName").on('change', function () {
            var catNameElem = $(this);
            $.get("/categories", function (data) {
                var catNoElem = $('#catNo');
                for (var catName in data) {
                    if (catNameElem.val() === catName) {
                        // 기존 옵션 삭제
                        catNoElem.find('option:not(:first)').remove();
                        // 신규 옵션 추가
                        $.each(data[catName], function (key, value) {
                            catNoElem.append($("<option></option>")
                                    .attr("value", value.catNo).text(value.subCatName));
                        });
                    }
                }
            });
        });

        // 카테고리 init
        $("#catName").trigger('change');

        // form submit
        $('#bookSearchModel').submit(function(){
            $('#page').val(1);
        });

        // 페이지 사이즈 selectbox
        $('#sizeSelect').on('change', function() {
            $('#size').val($(this).val());
            $('#bookSearchModel').submit();
        });
    });
</script>
</body>
</html>