
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=8,IE=9,IE=10,IE=11" >
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<%
	String basePath = request.getContextPath();
	String httpPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ basePath + "/";
%>
<%
request.setAttribute("basePath", request.getContextPath());
request.setAttribute("httpPath", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ basePath + "/");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%-- <%@ taglib prefix="hc" uri="html-component.tld" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>
 
<script type="text/javascript">
    var basePath = "<%=request.getContextPath()%>";
    var httpPath = "<%=httpPath%>";
</script>
 
<!--Css -->
<link href="<%=basePath%>/resources/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/resources/easyui/themes/icon.css" rel="stylesheet" type="text/css" />

<!-- ** Javascript ** -->
<script type="text/javascript" src="<%=httpPath%>resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=httpPath%>resources/easyui/jquery.easyui.min.js"></script>



