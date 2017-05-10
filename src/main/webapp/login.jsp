<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>教学资源分析系统</title>
    <link href="${pageContext.request.contextPath }/css/admin_login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#access").click(function(){			
				location.href="${pageContext.request.contextPath }/meun/getPrimMenu.do?loginUser=";				
			});		
	});	
	</script>
</head>
<body>
<div class="admin_login_wrap">
    <h1>教学资源分析系统</h1>
    <div class="adming_login_border">
    	<font color="red"><span>${msg}</span></font>
        <div class="admin_input">
            <form action="${pageContext.request.contextPath }/user/userLogin.do" method="post">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="username" value="cc" id="username" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="password" value="cc" id="password" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="用户登录" class="btn btn-primary" /><input type="button" id="access" tabindex="3" value="无账号密码登录" class="btn btn-primary" title="将不会拥有推送功能"/>
                    </li>
                    <li><a href="${pageContext.request.contextPath }/jsp/userregist.jsp">还没有账号，点我注册一个吧！</a></li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>