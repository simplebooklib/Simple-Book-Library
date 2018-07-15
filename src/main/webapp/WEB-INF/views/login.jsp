<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<jsp:directive.include file="/WEB-INF/system/taglibs.jsp"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Simple Book Library</title>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body class="text-center">
<form action="<c:url value='/login' />" method="post" class="form-signin">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <h1 class="text-center h3 mb-3">Simple Book Library</h1>
    </div>
    <label for="j_username" class="sr-only">Id</label>
    <input type="text" id="j_username" name="j_username" class="form-control" placeholder="아이디" required autofocus value="test_user">
    <label for="j_password" class="sr-only">Password</label>
    <input type="password" id="j_password" name="j_password" class="form-control" placeholder="비밀번호" required value="1234">
    <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
</form>
</body>
</html>