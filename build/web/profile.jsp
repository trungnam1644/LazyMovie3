<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:if test="${User == null }">
    <c:redirect url="login.jsp" />
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="css/profile.css">
    </head>
    <body>
        <div class="profile-container"> <!-- Class container đã chuẩn -->

            <!-- Sidebar -->
            <div class="sidebar">
                <div class="sidebar-profile">
                    <img src="img/profile.jpg" alt="Avatar" class="sidebar-avatar">
                    <h2>${User.fullName}</h2>
                </div>
                <ul>
                    <li><a href="home.jsp">Trang Chủ</a></li>
                        <c:if test="${User.role == 'Admin'}">
                        <li><a href="admin1.jsp">Quản Lý Admin</a></li>
                        </c:if>
                    <li><a href="home.jsp" class="logout-btn">Trở lại</a></li>
                </ul>
            </div>

            <!-- Content -->
            <div class="content">
                <header>
                    <h2>Hồ Sơ Cá Nhân</h2>
                </header>

                <main class="profile-content">
                    <div class="profile-card">
                        <table class="profile-table">
                            <tr>
                                <td>Tên</td>
                                <td>${User.fullName}</td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td>${User.email}</td>
                            </tr>
                            <tr>
                                <td>Số điện thoại</td>
                                <td>${User.phone}</td>
                            </tr>
                            <tr>
                                <td>Ngày sinh</td>
                                <td>${User.dateOfBirth}</td>
                            </tr>
                            <tr>
                                <td>Giới tính</td>
                                <td>${User.gender}</td>
                            </tr>                                                                              
                            <tr>
                                <td>Trạng thái tài khoản</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${User.typeID == 1}">Basic</c:when>
                                        <c:when test="${User.typeID == 2}">Premium</c:when>
                                        <c:otherwise>Unknown</c:otherwise>
                                    </c:choose>
                                </td>

                            </tr>

                            <tr>
                                <td>Phương thức thanh toán</td>
                                <td>Thẻ Visa</td>
                            </tr>


                        </table>
                    </div>


                </main>
            </div>
        </div>
    </body>
</html>
