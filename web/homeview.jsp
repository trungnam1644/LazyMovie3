<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (request.getAttribute("movie") == null) { %>
<p style="color:red;">Không tìm thấy dữ liệu phim.</p>
<% }%>

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
                    <li class="menu-list-item"><a href="phimbo.jsp">PHIM CHIẾU RẠP</a></li>
                    <li class="menu-list-item"><a href="phimle.jsp">PHIM LẺ</a></li>
                    <li class="menu-list-item genre-dropdown">
                        <a href="#">THỂ LOẠI</a>
                        <ul class="submenu">
                            <li><a href="#">HÀNH ĐỘNG</a></li>
                            <li><a href="#">PHIÊU LƯU</a></li>
                            <li><a href="#">KINH DỊ</a></li>
                            <li><a href="#">HÀI HƯỚC</a></li>
                            <li><a href="#">TÂM LÝ</a></li>
                            <li><a href="#">LÃNG MẠN</a></li>
                            <li><a href="#">KHOA HỌC VIỄN TƯỞNG</a></li>
                            <li><a href="#">HOẠT HÌNH</a></li>
                            <li><a href="#">TÀI LIỆULIỆU</a></li>
                            <li><a href="#">TỘI PHẠM</a></li>
                            <li><a href="#">CỔ ĐIỂN</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="search-cart-container">
                    <div class="search-container">
                        <input type="text" class="search-input" placeholder="Tìm kiếm...">
                        <i class="search-icon fas fa-search"></i>
                    </div>
                </div>
                <div class="profile-dropdown">
                    <a href="#" class="profile-container">
                        <img class="profile-picture" src="img/profile.jpg" alt="">
                        <div class="profile-text-container">
                            <span class="profile-text">Profile</span>
                        </div>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="profile.jsp">Trang cá nhân</a></li>
                        <li><a href="admin1.jsp">Quản lý Admin</a></li> <!-- Nút Admin -->
                        <li><a href="index.jsp" class="logout-btn">Đăng xuất</a></li>
                    </ul>
                </div>          
            </div>
        </div>
        <div class="container">
            <!-- Bên trái để trống -->
            <div class="left-sidebar"></div>

            <!-- Nội dung chính -->
            <div class="main-content">
                <!-- Video player -->
                <div class="video-container">
                    <iframe 
                        src="${movie.videoURL}" 
                        frameborder="0" 
                        allowfullscreen>
                    </iframe>
                </div>


               <h1 class="movie-title" style="background: red; color: white; z-index: 999;">
    ${movie.title}
</h1>

                <p class="description">${movie.description}</p>
                <p><strong>Diễn viên:</strong> ${movie.actors}</p>
                <p><strong>Thể loại:</strong> ${movie.genres}</p>
                <p><strong>Năm:</strong> ${movie.releaseYear}</p>
                <p><strong>Quốc gia:</strong> ${movie.countryName}</p>

                <!-- Bên phải để trống -->
                <div class="right-sidebar"></div>
            </div>

            <footer class="footer">
                <p><a>Bạn có câu hỏi? Xin hãy liên hệ với chúng tôi.</a></p>
                <p>SDT: 8429012025</p>
                <p>Email: LazyMovie@fpt.vip.vn</p>
                <p>&copy; LazyMovie. LazyMovie.com.vn</p>
            </footer>


            <script src="app.js"></script>
    </body>
</html>
