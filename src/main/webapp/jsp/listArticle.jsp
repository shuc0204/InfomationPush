<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
	<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
     <script src="../script/jquery.pagination.js"></script>
     <link rel="stylesheet" type="text/css" href="../style/pagination.css" media="screen">
    <script type="text/javascript">
    $(function(){  
    	var total=$('#total').val();
    	var pageSize=$('#pageSize').val();
    	var currentPage=$('#currentPage').val();
    	var pageCount=$('#pageCount').val();
    	var parentCode=$('#parentCode').val();
	    $('.M-box3').pagination({
	        pageCount:pageCount,
	        totalData:total,
	        showData:pageSize,
	        current:currentPage,
	        jump:true,
	        coping:true,
	        homePage:'首页',
	        endPage:'末页',
	        prevContent:'上页',
	        nextContent:'下页',
	        callback:function(index){//回调函数， 
	        	var count =index.getCurrent();
	        	//将分页数据重新 加载到 listArticle.jsp中
	        	location.href="${pageContext.request.contextPath }/article/getArticleList.do?code="+parentCode+"&current="+count+"&pageSize="+pageSize;	                		        	
	        }, 
	        
	    });	    
	 	
    });
    
    </script> 

</head>
<body>
	<div class="result-title">
		<div class="result-list"></div>
	</div>
	<div class="result-content">
	<span>搜索到${articleList.total}条结果，共${articleList.pageCount}页</span>
		<table class="result-tab" width="100%">
			<tr>
				<th width="80px">序号</th>
				<th align="left">标题</th>
			</tr>
			<c:forEach items="${articleList.data}" var="item" varStatus="status">
				<tr>
					<td width="80px" align="center">${(articleList.currentPage-1)*articleList.pageSize+status.index + 1}</td>
					<td><a href="javascript:void(0);" class="openArticleBtn" data-code="${item.code}" data-url = "${item.url}">${item.title}</a> …</td>
				</tr>
			</c:forEach>		
		</table>
	</div>
	<span>搜索到${articleList.total}条结果，共${articleList.pageCount}页</span>
	<div class="M-box3"></div>
	<input type="hidden" id="total" value="${articleList.total}"/>
	<input type="hidden" id="pageSize" value="${articleList.pageSize}"/>
	<input type="hidden" id="currentPage" value="${articleList.currentPage}"/>
	<input type="hidden" id="pageCount" value="${articleList.pageCount}"/>
	<input type="hidden" id="parentCode" value="${parentCode}"/>
</body>
<script>
	$(function () {
	    var isClicked = false;
		$('.openArticleBtn').click(function () {
		    if(isClicked){
		        return;
			}
			var  _this = $(this);
			var ajaxUrl = '${pageContext.request.contextPath }/api/analyse/submit.do';
			$.ajax({
                type: "POST",
                url: ajaxUrl,
                data: {
                    articleCode: _this.data('code')
				},
                async: false
			}).done(function (data) {
                location.href = _this.data('url');
                console.log('服务器分析后返回的数据:  \n'+ JSON.stringify(data,null,2));
			    setTimeout(function () {
					console.log('10s:  \n'+ JSON.stringify(data,null,2));
                },2000);
                isClicked = false;

            });



        })
    });

</script>
</html>