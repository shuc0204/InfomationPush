<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>用户修改信息</title>
    <link href="../css/admin_login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="admin_login_wrap">
    <h1>修改信息</h1>
    <div class="adming_login_border">
    <font color="red"><span>${msg}</span></font>
        <div class="admin_input">
            <form action="${pageContext.request.contextPath }/user/userUpdate.do" method="post">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="hidden" name="uid" value="${loginUser.uid}">
                        <input type="text" name="username" value="${loginUser.username}" id="username" size="40" class="admin_input_style" readonly="readonly"/>
                    </li>
                     <li>
                        <label for="pwd">原密码：</label>
                        <input type="password" name="password"  id="password" size="40" class="admin_input_style" placeholder="请输入原密码"/>
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="password1"  id="password1" size="40" class="admin_input_style" placeholder="请新设置密码"/>
                    </li>
                    <li>
                        <label for="pwd">确认密码：</label>
                        <input type="password" name="password2"  id="password2" size="40" class="admin_input_style" placeholder="请确认新密码"/>
                    </li>                    
                    <li>
                        <input type="submit" tabindex="3" value="提交修改" class="btn btn-primary" /><input type="reset" tabindex="3" value="取消" class="btn btn-primary"/>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>