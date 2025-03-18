<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Quản lý người dùng</title>
        <link rel="stylesheet" href="css/admin2.css">
    </head>
    <body>
        <c:if test="${User == null || User.role != 'Admin'}">
            <c:redirect url="login.jsp" />
        </c:if>      
        <c:if test="${empty userList}">
            <c:redirect url="MainController?action=viewUsers"/>
        </c:if>

        <div class="admin-container">
            <!-- Sidebar -->
            <nav class="sidebar">
                <h2>ADMIN</h2>
                <ul>
                    <li><a href="admin1.jsp" id="movie-link">Quản lý phim</a></li>
                    <li><a href="MainController?action=viewUsers">Quản lý người dùng</a></li>
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
                            <c:choose>
                                <c:when test="${not empty userList}">
                                    <c:forEach var="user" items="${userList}">
                                        <tr>
                                            <td>${user.userID}</td>
                                            <td>${user.fullName}</td>
                                            <td>${user.email}</td>
                                            <td>${user.phone}</td>
                                            <td>                                               
                                                <a href="MainController?action=deleteUser&id=${user.userID}" class="delete-btn"
                                                   onclick="return confirm('Bạn có chắc muốn xóa người dùng này?');">Xóa</a>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5" style="text-align: center;">Không có người dùng nào.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </section>
            </main>    
        </div>
    </body>
</html>
