<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:if test="${User == null || User.role != 'Admin'}">
    <c:redirect url="login.jsp" />
</c:if>
<c:if test="${movies == null}">
    <c:redirect url="MainController?action=viewMovie"/>
</c:if>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Quản lý phim</title>
        <link rel="stylesheet" href="css/admin.css">
    </head>
    <body>
        
        <div class="admin-container">
            <nav class="sidebar">
                <h2>ADMIN</h2>
                <ul>
                    <li><a href="MainController?action=viewMovie" id="movie-link">Quản lý phim</a></li>
                    <li><a href="admin2.jsp" id="user-link">Quản lý người dùng</a></li>
                    <li><a href="home.jsp" class="logout-btn">Trở lại</a></li>
                </ul>
            </nav>

            <main class="content">
                <header>
                    <h2 id="page-title">Quản lý phim</h2>
                    <a href="MainController?action=createpage" class="add-movie-btn">Thêm phim</a>
                </header>

                <section class="movie-list content-section">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên phim</th>                        
                                <th>Năm phát hành</th>
                                <th>Quốc gia</th>
                                <th>Thể loại</th>
                                <th>Loại phim</th>
                                <th>Ảnh</th>
                                <th>Người thêm</th>                              
                                <th>Quản lý</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="movie" items="${movies}">
                                <tr>                          
                                    <td>${movie.movieID}</td>
                                    <td>${movie.title}</td>
                                    <td>${movie.releaseYear}</td> 
                                    <td>${movie.countryName}</td>
                                    <td>${movie.genres}</td>
                                    <td>${movie.movieTypeName}</td>
                                    <td><img class="movie-list-item-img" src="${movie.thumbnailURL}" alt="${movie.title}" style="width: 100px; height: auto; border-radius: 5px;"></td>

                                    <td>${movie.userName}</td>                                  
                                    <td>
                                        <a href="EditMovieController?movieID=${movie.movieID}" class="edit-btn">Sửa</a>
                                        <a href="MainController?action=deleteMovie&id=${movie.movieID}" class="delete-btn" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>          
                </section>




            </main>
    </body>
</html>