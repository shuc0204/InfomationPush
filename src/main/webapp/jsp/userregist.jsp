<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link href="../css/admin_login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="admin_login_wrap">
    <h1>用户注册</h1>
    <div class="adming_login_border">
    <font color="red"><span>${msg}</span></font>
        <div class="admin_input">
            <form action="${pageContext.request.contextPath }/user/userRegist.do" method="post">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="username"  id="username" size="40" class="admin_input_style" placeholder="请输入您的账号"/>
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="password"  id="password" size="40" class="admin_input_style" placeholder="请设置密码"/>
                    </li>
                    <li>
                        <label for="pwd">确认密码：</label>
                        <input type="password" name="password2"  id="password2" size="40" class="admin_input_style" placeholder="请确认密码"/>
                    </li>
                    <li>
                        <label for="pwd">用户昵称：</label>
                        <input type="text" name="name" id="name" size="40" class="admin_input_style" placeholder="请输入您的昵称"/>
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="注册" class="btn btn-primary" /><input type="reset" tabindex="3" value="取消" class="btn btn-primary"/>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>