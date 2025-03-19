<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:if test="${User == null}">
    <c:redirect url="login.jsp" />
</c:if>
<c:if test="${movies == null}">
    <c:redirect url="MainController?action=viewMovieInHome"/>
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Bungee+Shade&display=swap" rel="stylesheet">
        <title>Home</title>
    </head>
    <body>



        <div class="navbar">
            <div class="navbar-container">
                <div class="logo-container">
                    <img class="logo" src="img/logo.jpg" alt="">
                </div>
                <ul class="menu-list">
                    <li class="menu-list-item"><a href="home.jsp">TRANG CHỦ</a>
                    </li>
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
                    
                       <a href="packagesUser.jsp">
                        <i class="cart-icon fas fa-shopping-cart"></i>
                    </a>
                   
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
            <div class="content-container">
                <div class="featured-section">
                    <!-- Video trailer nền -->
                    <div class="video-background">
                        <video autoplay muted loop playsinline>
                            <source src="img/vip1.mp4" type="video/mp4">
                            Trình duyệt không hỗ trợ video.
                        </video>
                    </div>
                    <!-- Nội dung hiển thị trên nền video -->
                    <div class="featured-content">
                        <h1>Đào, Phở và Piano</h1>
                        <p class="featured-desc">
                            Phim lấy bối cảnh trận chiến đông xuân kéo dài 60 ngày đêm từ cuối năm 1946 đến đầu năm 1947 ở Hà Nội, 
                            câu chuyện theo chân chàng dân quân Văn Dân và chuyện tình với nàng tiểu thư đam mê dương cầm Thục Hương. 
                            Khi những người khác đã di tản lên chiến khu, họ quyết định cố thủ lại mảnh đất thủ đô đã tan hoang vì bom đạn, mặc cho những hiểm nguy đang chờ đợi trước mắt.
                        </p>
                        <a href="MainController?action=viewMovieVideo&movieID=3" class="watch-button">Watch</a>
                    </div>
                </div>
                <div class="movie-list-container">
                    <h1 class="movie-list-title">DANH SÁCH PHIM</h1>
                    <div class="movie-list-wrapper">
                        <div class="movie-list">
                            <c:forEach var="movie" items="${movies}">
                                <div class="movie-list-item">
                                    <img class="movie-list-item-img" src="${movie.thumbnailURL}" alt="${movie.title}">
                                    <span class="movie-rating"><i class="fas fa-star"></i> ${movie.rating}</span>
                                    <span class="movie-list-item-title">${movie.title}</span>
                                    <a href="MainController?action=viewMovieVideo&movieID=${movie.movieID}" class="movie-list-item-button">Watch</a>
                                </div>

                            </c:forEach>
                        </div>
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
