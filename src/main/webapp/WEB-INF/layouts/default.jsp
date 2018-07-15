<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<jsp:directive.include file="/WEB-INF/system/taglibs.jsp"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <title><sitemesh:write property="title"/></title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/bootstrap-reboot.min.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/grid.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/util.js"></script>
    <script type="text/javascript" src="/resources/js/modal.js"></script>
    <script type="text/javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(function() {
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });
    </script>
    <style type="text/css">
        .modal-lg {
            max-width: 900px !important;
        }
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <span class="navbar-brand" href="#">Simple Book Library</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <span class="nav-link">
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.username"/>님 반갑습니다.
                        <a class="btn btn-info btn-sm" href="<c:url value="/search"/>" role="button">북검색</a>
                        <a class="btn btn-success btn-sm" href="<c:url value="/bookmark"/>" role="button">북마크</a>
                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModal">검색 기록</button>
                    </sec:authorize>
                </span>
            </ul>
            <c:url var="logoutUrl" value="/logout"/>
            <form class="form-inline mt-2 mt-md-0" action="${logoutUrl}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">logout</button>
            </form>
        </div>
    </nav>
</header>
<main role="main" style="margin-top: 56px;">
    <sitemesh:write property="body"/>
</main>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">최근 검색 히스토리</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">검색어</th>
                        <th scope="col">url</th>
                        <th scope="col">시간</th>
                    </tr>
                    </thead>
                    <tbody id="searchHistoryBody">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#exampleModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var recipient = button.data('whatever');
            var modal = $(this);
            $.ajax({
                dataType: "json",
                url: "/history",
                success: function(data) {
                    var searchHistoryBody = $('#searchHistoryBody');
                    searchHistoryBody.find('tr').remove();
                    for (var i = 0; i < data.length; i++) {
                        var item = data[i];
                        var url = item['url'];
                        searchHistoryBody.append('<tr>' +
                                '<th scope="row">' + (i + 1) + '</th>' +
                                '<td>' + item['query'] +  '</td>' +
                                '<td><a href="' + url + '">' + url + '</a></td>' +
                                '<td>' + item['createDate'] +  '</td><' +
                                '/tr>');
                    }
                }
            });
        })
    });
</script>
</body>
</html>
