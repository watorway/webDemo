<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/import.jsp"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>

<link href="${basePath }/css/demo1/login.css" rel="stylesheet" type="text.css">
<style>
BODY {
	TEXT-ALIGN: center;
	PADDING-BOTTOM: 0px;
	BACKGROUND-COLOR: #ddeef2;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	PADDING-TOP: 0px
}

A:link {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #ff0000;
	TEXT-DECORATION: underline
}

A:active {
	TEXT-DECORATION: none
}

.input {
	BORDER-BOTTOM: #ccc 1px solid;
	BORDER-LEFT: #ccc 1px solid;
	LINE-HEIGHT: 20px;
	WIDTH: 182px;
	HEIGHT: 20px;
	BORDER-TOP: #ccc 1px solid;
	BORDER-RIGHT: #ccc 1px solid
}

.input1 {
	BORDER-BOTTOM: #ccc 1px solid;
	BORDER-LEFT: #ccc 1px solid;
	LINE-HEIGHT: 20px;
	WIDTH: 120px;
	HEIGHT: 20px;
	BORDER-TOP: #ccc 1px solid;
	BORDER-RIGHT: #ccc 1px solid
}
</style>

</HEAD>

<BODY>
	<FORM id="loginForm" onsubmit="return checklogin();" method="post"
		name="loginForm" action="${basePath}/sys/login.html">
		<input value=1 type=hidden name=do_submit>
		
		<TABLE style="MARGIN: auto; WIDTH: 100%; HEIGHT: 100%" border=0
			cellSpacing=0 cellPadding=0>
			<TBODY>
				<TR>
					<TD height=150>&nbsp;</TD>
				</TR>
				<TR style="HEIGHT: 254px">
					<TD>
						<DIV style="MARGIN: 0px auto; WIDTH: 936px">
							<img style="DISPLAY: block" src="${basePath }/images/demo1/body_01.jpg">
						</DIV>
						<DIV style="BACKGROUND-COLOR: #278296">
							<DIV style="MARGIN: 0px auto; WIDTH: 936px">
								<DIV
									style="BACKGROUND: url(${basePath }/images/demo1/body_02.jpg) no-repeat; HEIGHT: 155px">
									<div
										style="TEXT-ALIGN: left; WIDTH: 265px; FLOAT: right; HEIGHT: 125px; _height: 95px">
										<table border=0 cellSpacing=0 cellPadding=0 width="100%">
											<tbody>
												<tr>
													<td style="HEIGHT: 43px"><INPUT id="username"
														class="input" type="text" name="username"></td>
												</tr>
												<tr>
													<td><input id="password" class="input" type="password"
														name="password"></td>
												</tr>
												<tr>
													<td style="HEIGHT: 50px">
														<INPUT id="checkcode" class="yzm" size="8" type="text" name="checkcode"> 
														<IMG style="CURSOR: pointer" onclick="this.src='${basePath}/checkcode.html?id='+Math.random();"
														alt=验证码,看不清楚?请点击刷新验证码 align="bottom"
														src="${basePath }/checkcode.html" ></td>
												</tr>
												<tr>
													<td>
														<font color="red">${LOGIN_ERROR}${CODE_ERROR}</font>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<DIV style="HEIGHT: 1px; CLEAR: both"></DIV>
									<DIV style="WIDTH: 380px; FLOAT: right; CLEAR: both">
										<TABLE border="0" cellSpacing="0" cellPadding="0" width="300">
											<TBODY>
												<TR>
													<TD width="100" align="right"><INPUT
														style="BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px"
														id="btnLogin" src="${basePath }/images/demo1/btn1.jpg" type="image" name="btnLogin"></TD>
													<TD width=100 align="middle"><INPUT
														style="BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px"
														id="btnReset" src="${basePath }/images/demo1/btn2.jpg" type="image" name="btnReset"></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
							</DIV>
						</DIV>
						<DIV style="MARGIN: 0px auto; WIDTH: 936px">
							<IMG src="${basePath }/images/demo1/body_03.jpg">
						</DIV>
					</TD>
				</TR>
				<TR style="HEIGHT: 30%">
					<TD>&nbsp;</TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
<script type="text/javascript">

<SCRIPT language=javascript type=text/javascript>
	function killerror() {
		return true;
	}
	window.onerror = killerror;
	$(document).ready(function() {
		$('#username').focus();
		$('#adminlogin').submit(function() {
			if ($.trim($('#username').val()) == '') {
				$('#username').css("border-color", "#ff9900");
				$('#username').focus();
				return false;
			} else {
				$('#username').css("border-color", "");
			}

			if ($.trim($('#password').val()) == '') {
				$('#password').css("border-color", "#ff9900");
				$('#password').focus();
				return false;
			} else {
				$('#password').css("border-color", "");
			}

			if ($.trim($('#checkcode').val()).length != 4) {
				$('#checkcode').css("border-color", "#ff9900");
				$('#checkcode').focus();
				return false;
			} else {
				$('#checkcode').css("border-color", "");
			}
			return true;
		})
	});
</SCRIPT>

</script>
</HTML>