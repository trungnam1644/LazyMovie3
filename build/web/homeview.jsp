<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <li class="menu-list-item"><a href="phimbo.jsp">PHIM BỘ</a></li>
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
                        src="https://mega.nz/embed/KphAGS4C#EACTYxLUhpRbZmwsvAZNg2QURWiUkLT2TVsUnskAS1Q" 
                        width="100%" 
                        height="585" 
                        frameborder="0" 
                        allowfullscreen>
                    </iframe>
                </div>

                <!-- Tên phim -->
                <h1 class="movie-title">Đào, Phở và Piano</h1>

                <!-- Đánh giá -->
                <div class="rating">⭐⭐⭐⭐☆ (4.5/5)</div>

                <!-- Thông tin phim -->
                <div class="details">
                    <div class="detail-item">
                        <h2> Diễn viên: </h2> 
                        <div>
                            Doãn Quốc Đam, Cao Thị Thùy Linh, Trần Lực, Tuấn Hưng,
                            Thiện Hùng, Trung Hiếu, Anh Tuấn, Nguyệt Hằng, Xuân Hồng, Văn Lượng, Phạm Minh Quang,
                            Tùng Lee, Hải Quân, Tiến Lợi, Ngọc Ánh, Thùy Trang, Bùi Hải Vy, Nguyễn Diệu Thúy .v.v..
                        </div>
                    </div>
                        <div class="detail-item">Quốc gia: Việt Nam</div>
                        <div class="detail-item">Thể loại: Tài liệu, Lãng mạn</div>
                        <div class="detail-item">Đạo diễn: Phi Tiến Sơn</div>
                        <div class="detail-item">Năm: 2024</div>
                        <div class="detail-item">Thời lượng: 10p34s</div>
                </div>

                <!-- Nội dung phim -->
                <div class="description">
                    <h2>Nội dung phim: </h2>
                    <div>
                        Phim lấy bối cảnh trận chiến đông xuân kéo dài 60 ngày đêm từ cuối năm 1946 đến đầu năm 1947 ở Hà Nội, 
                        câu chuyện theo chân chàng dân quân Văn Dân và chuyện tình với nàng tiểu thư đam mê dương cầm Thục Hương. 
                        Khi những người khác đã di tản lên chiến khu, họ quyết định cố thủ lại mảnh đất thủ đô đã tan hoang vì bom đạn, 
                        mặc cho những hiểm nguy đang chờ đợi trước mắt.
                    </div>
                </div>        
            </div>

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
