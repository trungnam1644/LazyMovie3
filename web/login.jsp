<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login Form</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="logo-lazymoive">
           <a href="index.jsp">
                <img src="img/logo.jpg" alt="Logo">
            </a>      
        </div>

        <div class="login-container">
            <h2>Đăng Nhập</h2>
            <form action="LoginController" value="login" method="post">
                <div class="form-group">
                    <label for="username">Tên Đăng Nhập:</label>
                    <input type="text" id="username" name="username" placeholder="Tên Đăng Nhập" required>
                </div>

                <div class="form-group">
                    <label for="password">Mật Khẩu:</label>
                    <input type="password" id="password" name="password" placeholder="Mật Khẩu" required>
                </div>
                <button type="submit">Đăng Nhập</button>
                <h1 style="color: red" > ${requestScope.Error_Login}
                    
                </h1>
                
            </form>
                <div class="register-link">
                <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a></p>
            </div>
        </div>
    </body>
</html>
