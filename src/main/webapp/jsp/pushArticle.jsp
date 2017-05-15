<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet" type="text/css" href="../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/main.css"/>
    <script type="text/javascript" src="../../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
	<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
     <script src="../../script/jquery.pagination.js"></script>
     <link rel="stylesheet" type="text/css" href="../../style/pagination.css" media="screen">
</head>
<body>
	<div class="result-title">
		<div class="result-list"><p align="center" style="font-size: 30px;color: red;">经过我深思熟虑为您推送了以下高频文章！</p></div>
	</div>
	<div class="result-content">
		
		<table class="result-tab" width="100%">
			<tr>
				<th width="80px">序号</th>
				<th align="left">标题</th>
			</tr>
			<c:forEach items="${pushArticleList}" var="item" varStatus="status">
				<tr>
					<td width="80px" align="center">${status.index + 1}</td>
					<td><a href="#" data-dbcode="${item.dbCode}" data-dbname="${item.dbName}"data-filename="${item.fileName}" data-url="${item.url}">${item.title}</a>
					…</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>