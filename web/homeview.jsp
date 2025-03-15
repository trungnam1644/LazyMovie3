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
                    <img class="logo" src="img/logo.jpg" alt="">
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
                    <i class="cart-icon fas fa-shopping-cart"></i>
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
        <!-- Giao diện chọn gói xem phim -->
        <div class="package-overlay">
            <div class="package-container">
                <i class="close-icon fas fa-times"></i>
                <h2>Chọn gói xem phim</h2>
                <div class="packages">
                    <div class="package basic">
                        <h3>Gói Cơ Bản</h3>
                        <p class="package-price">50.000đ / tháng</p>
                        <ul>
                            <li>Độ phân giải 720p (HD)</li>
                            <li>Xem trên 1 thiết bị</li>
                            <li>Có quảng cáo</li>
                            <li>Không thể tải xuống</li>
                            <li>Giới hạn phim</li>
                        </ul>
                        <button class="select-package">Chọn gói</button>
                    </div>
                    <div class="package premium">
                        <h3>Gói Cao Cấp</h3>
                        <p class="package-price">250.000đ / tháng</p>
                        <ul>
                            <li>Độ phân giải 4K (Ultra HD) + HDR</li>
                            <li>Xem trên 4 thiết bị</li>
                            <li>Không quảng cáo</li>
                            <li>Có thể tải về để xem</li>
                            <li>Xem không giới hạn</li>
                        </ul>
                        <button class="select-package">Chọn gói</button>
                    </div>
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
    width="640" 
    height="360" 
    frameborder="0" 
    allowfullscreen>
</iframe>

                </div>

                <!-- Tên phim -->
                <h1 class="movie-title">Tên phim</h1>

                <!-- Đánh giá -->
                <div class="rating">⭐⭐⭐⭐☆ (4.5/5)</div>

                <!-- Thông tin phim -->
                <div class="details">
                    <div class="detail-item">Diễn viên: Gorden Ramsay</div>
                        <div class="detail-item">Quốc gia: Mỹ</div>
                        <div class="detail-item">Thể loại: Hành động</div>
                        <div class="detail-item">Đạo diễn: John Doe</div>
                        <div class="detail-item">Năm: 2024</div>
                        <div class="detail-item">Thời lượng: 120 phút</div>
                </div>

                <!-- Nội dung phim -->
                <div class="description">
                    Đây là nội dung mô tả phim, tóm tắt cốt truyện hoặc thông tin chi tiết khác...
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
