<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (request.getAttribute("movie") == null) { %>
<p style="color:red;">Không tìm thấy dữ liệu phim.</p>
<% }%>
<c:if test="${empty movies}">
    <jsp:forward page="MainController?action=viewMovieInHome&viewType=homeview"/>
</c:if>
<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/homeview.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <title>Home View</title>
    </head>
    <body>
        <div class="navbar">
            <div class="navbar-container">
                <div class="logo-container">
                    <a href="home.jsp">
                        <img src="img/logo.jpg" alt="Logo">
                    </a>
                </div>
                <ul class="menu-list">
                    <li class="menu-list-item"><a href="home.jsp">TRANG CHỦ</a></li>
                    <li class="menu-list-item"><a href="MovieController?action=phimchieurap">PHIM CHIẾU RẠP</a></li>
                    <li class="menu-list-item"><a href="MovieController?action=phimle">PHIM LẺ</a></li>
                    <li class="menu-list-item genre-dropdown">
                        <a href="#">THỂ LOẠI</a>
                        <ul class="submenu">
                            <c:forEach var="genre" items="${genres}">
                                <li><a href="MainController?action=viewByGenre&genreID=${genre.genreID}">${genre.genreName}</a></li>
                                </c:forEach>
                        </ul>
                    </li>
                </ul>
                <div class="search-cart-container">
                    <form action="MainController" method="get" class="search-container">
                        <input type="text" name="keyword" class="search-input" placeholder="Tìm kiếm...">
                        <input type="hidden" name="action" value="search">
                        <button type="submit" class="search-icon"><i class="fas fa-search"></i></button>
                    </form>
                </div>

                <div class="profile-dropdown">
                    <a href="#" class="profile-container">                      
                        <div class="profile-text-container">
                            <span class="profile-text">
                                ${User.fullName} <!-- Hiển thị tên người dùng -->
                            </span>
                        </div>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="profile.jsp">Trang cá nhân</a></li>
                            <c:if test="${User.role == 'Admin'}">
                            <li><a href="admin1.jsp">Quản lý Admin</a></li>
                            </c:if>
                        <li><a href="MainController?action=logout">Đăng xuất</a></li>
                    </ul>
                </div>          
            </div>
        </div>
        <div class="container">
            <!-- Nội dung chính -->
            <div class="main-content">
                <!-- Video player -->
                <div class="video-container">
                    <iframe 
                        src="${movie.videoURL}" 
                        width="100%" 
                        height="585"
                        frameborder="0" 
                        allowfullscreen>
                    </iframe>
                </div>

                <div class="details">
                    <h1 class="movie-title"">
                        ${movie.title}
                    </h1>

                    
                    <p><strong>Thể loại:</strong> ${movie.genres}</p>
                    <p><strong>Năm:</strong> ${movie.releaseYear}</p>
                    <p><strong>Quốc gia:</strong> ${movie.countryName}</p>                 
                </div>
                <p class="description">${movie.description}</p>
            </div>
        </div>

            <footer class="footer">
                <p><a>Bạn có câu hỏi? Xin hãy liên hệ với chúng tôi.</a></p>
                <p>SDT: 8429012025</p>
                <p>Email: LazyMovie@fpt.vip.vn</p>
                <p>&copy; LazyMovie. LazyMovie.com.vn</p>
            </footer>
    </body>
</html>