<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<jsp:directive.include file="/WEB-INF/system/taglibs.jsp"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Simple Book Library</title>
    <style type="text/css">

    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md">
            <div class="card flex-md-row mb-4 box-shadow h-md-250">
                <img class="card-img-right flex-auto d-none d-md-block" data-src="holder.js/200x250?theme=thumb" alt="Thumbnail [200x250]"
                     src="<c:url value="${not empty result.thumbnail ? result.thumbnail : 'data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22200%22%20height%3D%22250%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20200%20250%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_16496aab950%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A13pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_16496aab950%22%3E%3Crect%20width%3D%22200%22%20height%3D%22250%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2256.203125%22%20y%3D%22130.7%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E'}"/>"
                     data-holder-rendered="true" style="width: 200px; height: 250px;">
                <div class="card-body d-flex flex-column align-items-start">
                    <div>
                        <strong class="d-inline-block mb-2 text-primary"><c:out value="${result.category}"/></strong>
                        <c:choose>
                            <c:when test="${empty bookmark}">
                                <button type="button" id="bookmarkBtn" mark="true" class="btn btn-success btn-sm">북마크</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" id="bookmarkBtn" mark="false" class="btn btn-secondary btn-sm">북마크해제</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <h3 class="mb-0">
                        <a class="text-dark" href="#"><c:out value="${result.title}"/> </a>
                    </h3>
                    <div class="container">
                        <dl class="row">
                            <dt class="w-25">저자</dt>
                            <dd class="w-75">
                                <c:forEach items="${result.authors}" var="author" varStatus="loop">
                                    ${author}
                                    <c:if test="${!loop.last}">,</c:if>
                                </c:forEach>
                            </dd>
                            <c:if test="${not empty result.translators}">
                                <dt class="w-25">역자</dt>
                                <dd class="w-75">
                                    <c:forEach items="${result.translators}" var="translator" varStatus="loop">
                                        ${translator}
                                        <c:if test="${!loop.last}">,</c:if>
                                    </c:forEach>
                                </dd>
                            </c:if>
                            <dt class="w-25">출판사</dt>
                            <dd class="w-75">
                                <c:out value="${result.publisher}"/> | <fmt:formatDate value="${result.datetime}" type="date" pattern="yyyy.MM.dd"/>
                            </dd>
                            <dt class="w-25">정가</dt>
                            <dd class="w-75">
                                <del><fmt:formatNumber value="${result.price}" pattern=",000"/>원</del>
                                <strong style="font-size: large;"><fmt:formatNumber value="${result.salePrice}" pattern=",000"/></strong>원
                            </dd>
                            <dt class="w-25">상태</dt>
                            <dd class="w-75">
                                <c:out value="${result.status}"/>
                            </dd>
                        </dl>
                    </div>
                    <p class="card-text mb-auto"><c:out value="${result.contents}"/></p>
                    <a href="<c:url value="${result.url}"/>">링크</a>
                </div>
            </div>
        </div>
    </div>
    <div>
        <div class="text-right mt-3">
            <a class="btn btn-primary" href="<c:url value="/search?${params}"/>" role="button">목록</a>
        </div>
    </div>
</div>
<c:set var="isbn1" value="${fn:split(result.isbn, ' ')[0]}"/>
<c:set var="isbn2" value="${fn:split(result.isbn, ' ')[1]}"/>
<c:url var="bookmarkUrl" value="/bookmark/${empty isbn2 ? isbn1 : isbn2}"/>
</div>
<script type="text/javascript">
    $(function () {
        $('#bookmarkBtn').on('click', function () {
            var self = $(this);
            var mark = self.attr('mark');
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                url: "${bookmarkUrl}",
                data: {"mark": mark},
                success: function (data) {
                    if (mark == "true") {
                        self.removeClass('btn-success').addClass('btn-secondary').attr('mark', false);
                        self.text('북마크해제');
                    } else {
                        self.removeClass('btn-secondary').addClass('btn-success').attr('mark', true);
                        self.text('북마크');
                    }
                }
            });
        });
    });
</script>
</body>
</html>