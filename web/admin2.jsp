<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:if test="${User == null || User.role != 'Admin'}">
    <c:redirect url="login.jsp" />
    </c:if>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Quản lý người dùng</title>
        <link rel="stylesheet" href="css/admin.css">
    </head>
    <body>
        <div class="admin-container">
        <!-- Sidebar -->
        <nav class="sidebar">
            <h2>ADMIN</h2>
            <ul>
                <li><a href="admin1.jsp" id="movie-link">Quản lý phim</a></li>
                <li><a href="admin2.jsp" id="user-link">Quản lý người dùng</a></li>
                <li><a href="home.jsp" class="logout-btn">Trở lại</a></li>
            </ul>
        </nav>

        <!-- Nội dung chính -->
        <main class="content">
            <header>
                <h2 id="page-title">Quản lý người dùng</h2> 
            </header>
        
            <!-- Quản lý người dùng -->
            <section class="user-list content-section" id="users-section">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên người dùng</th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                            <th>Quản lý</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Nguyen Van A</td>
                            <td>a@gmail.com</td>
                            <td>05144465258</td>
                            <td>
                                <button class="edit-btn">Sửa</button>
                                <button class="delete-btn">Xóa</button>
                            </td>
                        </tr>
                        <!-- Các dòng khác -->
                    </tbody>
                </table>
            </section>
        </main>    
    </body>
</html>
