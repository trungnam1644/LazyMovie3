<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration Form</title>
        <link rel="stylesheet" href="css/register.css">
    </head>
    <body>
        <!-- Logo trên đầu form -->
        <div class="logo-lazymoive">
            <a href="index.jsp">
                <img src="img/logo.jpg" alt="Logo">
            </a>    
        </div>

        <div class="form-container">
            <h2>Thông Tin Người Dùng</h2>
            <form action="MainController" method="post">
            <input type="hidden" name="action" value="createAccount">

                <div class="form-group">
                    <label for="full-name">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" placeholder="Nhập Họ Và Tên" required>
                </div>
                    <div class="form-group">
                        <label for="password">DateOfBirth:</label>
                    <input type="Date" id="dateOfBirth" name="dateOfBirth" placeholder="yyyy-mm-dd" required>
                </div>
                
                <div class="form-group">
                    <label for="username">Tên Đăng Nhập:</label>
                    <input type="text" id="userName" name="userName" placeholder="Tên Đăng Nhập"required>
                </div>

                <div class="form-group">
                    <label for="password">Mật Khẩu:</label>
                    <input type="password" id="password" name="password" placeholder="Mật Khẩu" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Email" required>
                </div>

                <div class="form-group">
                    <label for="phone">Số Điện Thoại:</label>
                    <input type="tel" id="phone" name="phone" pattern="(\+84|0)[0-9]{9}" placeholder="Nhập số điện thoại" required>
                </div>
            
                
                
                <div class="form-group">
                    <label for="gender">Giới Tính:</label>
                    <select id="gender" name="gender" required>
                        <option value="">-- Chọn giới tính --</option>
                        <option value="Male">Nam</option>
                        <option value="Female">Nữ</option>                     
                    </select>
                </div>
                <button type="submit">Đăng Ký</button>
            </form>
            <p>Bạn đã có tài khoản? <a href="login.jsp">Đăng nhập ngay.</a></p>
        </div>
    </body>
</html>