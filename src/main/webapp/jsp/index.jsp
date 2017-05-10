<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>教学资源分析系统</title>

    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
    
    <script type="text/javascript">
    function outwebSite(){
    	if(confirm("确定要退出本系统吗？"))
        {
    		location.href="${pageContext.request.contextPath }/login.jsp";
        }      
    	
    }
    function getArticles(code){
    	
    	var listurl = "${pageContext.request.contextPath }/article/getArticleList.do?code="+code+"&current=1&pageSize=20";
    	$('#iframe_article').attr('src',listurl);

    }
    
    $(document).ready(function() {
    	 	
    	var memuItemHandler = function(e){

    	    if(e.target.tagName.toUpperCase()=='SPAN'){
    	        return;
            }
    		function toggleMenu(){
        		if($(this).siblings('ul').css('display')=='none'){
        			$(this).parent('li').siblings('li').removeClass('inactives');
        			$(this).addClass('inactives');
        			$(this).siblings('ul').slideDown(100).children('li');
        			if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
        				$(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
        				$(this).parents('li').siblings('li').children('ul').slideUp(100);
        			}
        		}else{
        			//控制自身变成+号
        			$(this).removeClass('inactives');
        			//控制自身菜单下子菜单隐藏
        			$(this).siblings('ul').slideUp(100);
        			//控制自身子菜单变成+号
        			$(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
        			//控制自身菜单下子菜单隐藏
        			$(this).siblings('ul').children('li').children('ul').slideUp(100);

        			//控制同级菜单只保持一个是展开的（-号显示）
        			$(this).siblings('ul').children('li').children('a').removeClass('inactives');
        		}
        	};   		 		
    		
    		var parentMenu =$(this); 
    		var code = parentMenu.data('code');    		
    		var childsMenu =  parentMenu.next('ul');
    		var _this = this;
    		if(childsMenu.length==0){
    			
    			$.ajax({
  	      		  type: "POST",
  	      		  url: "getChildMenu.do",
  	      		  data: {"code":code},
  	      		  async: false
	  	      	}).done(function(data){
	  	      		childsMenu = $('<ul style="display: none"></ul>');
	  	      		$.each(data.childMenuList,function(index,item){
	  	      			var li = $('<li><a data-code="'+item.code+'" class="inactive" href="javascript:void(0);"><span onclick="getArticles(\''+item.code+'\')">'+item.title+'</span></a></li>');
	  	      			childsMenu.append(li);
	  	      		});
	  	      		
	  	      		parentMenu.after(childsMenu);
	  	      		childsMenu.find('.inactive').click(memuItemHandler);
	  	      		toggleMenu.call(_this);
	        	});
    		}else{
    			toggleMenu.call(_this);
    		}	  		
    		
    	};   	
   	    	  	
    	$('.inactive').click(memuItemHandler);      	 
    	
    });
    
    </script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">            
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.jsp">教学资源分析系统</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">欢迎：${loginUser.name}</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/userUpdate.jsp">修改密码</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/userregist.jsp">注册</a></li>
                <li><a href="javascript:outwebSite()">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix container-body">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="list">
            <ul class="yiji">
			<c:forEach items="${primMenuList}" var="menulist">
                <li><a href="javascript:void(0);"  data-code="${menulist.code}" class="menu-item inactive"> <span onclick="getArticles('${menulist.code}')" > ${menulist.title}  </span></a>
					</li>
				</c:forEach>
            </ul>
			</div>
        
    </div>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎使用教学资源分析系统。</span></div>
        </div>
        <div class="result-wrap" >
           <iframe id="iframe_article" style="overflow: auto;" width="100%" height="100%" src="${pageContext.request.contextPath }/jsp/welcome.jsp" frameborder="0"></iframe>
        </div>    
    </div>
</div>
</body>
</html>