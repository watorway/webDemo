<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/import.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>

</head>
<body>
    <script type="text/javascript">
        //window.location = "${basePath}sys/login/index.action";
        var tagert_URL = "${basePath}/sys/toLogin.html";
        if (self == top) {
            window.location.href = tagert_URL;
        } else {
            top.location.href = tagert_URL;
        }
    </script>
</body>
</html>