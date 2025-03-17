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
                            <li><a href="#">HÀNH ĐỘNG</a></li>
                            <li><a href="#">PHIÊU LƯU</a></li>
                            <li><a href="#">KINH DỊ</a></li>
                            <li><a href="#">HÀI HƯỚC</a></li>
                            <li><a href="#">TÂM LÝ</a></li>
                            <li><a href="#">LÃNG MẠN</a></li>
                            <li><a href="#">KHOA HỌC VIỄN TƯỞNG</a></li>
                            <li><a href="#">HOẠT HÌNH</a></li>
                            <li><a href="#">TÀI LIỆU</a></li>
                            <li><a href="#">TỘI PHẠM</a></li>
                            <li><a href="#">CỔ ĐIỂN</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="search-cart-container">
                    <form action="MainController" method="get" class="search-container">
                        <input type="text" name="keyword" class="search-input" placeholder="Tìm kiếm...">
                        <input type="hidden" name="action" value="search">
                        <button type="submit" class="search-icon"><i class="fas fa-search"></i></button>
                    </form>
                    <a href="cart.jsp" class="cart-icon">
                        <i class="fas fa-shopping-cart"></i>
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
                                <button class="select-package" onclick="selectPackage(1)">Chọn gói</button>
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
                                <button class="select-package" onclick="selectPackage(2)">Chọn gói</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Form ẩn để gửi dữ liệu -->
                <form id="packageForm" action="MainController" method="post">
                    <input type="hidden" name="action" value="SetType">
                    <input type="hidden" name="typeID" id="typeID">
                    <input type="hidden" name="userID" id="userID">


                </form>

                <script>
                    function selectPackage(typeID) {
                        let userID = "${sessionScope.User.userID}"; // Lấy userID từ session
                        document.getElementById("typeID").value = typeID;
                        document.getElementById("userID").value = userID;
                        document.getElementById("packageForm").submit();

                    }
                </script>


                <footer class="footer">
                    <p><a>Bạn có câu hỏi? Xin hãy liên hệ với chúng tôi.</a></p>
                    <p>SDT: 8429012025</p>
                    <p>Email: LazyMovie@fpt.vip.vn</p>
                    <p>&copy; LazyMovie. LazyMovie.com.vn</p>
                </footer>
                <script src="app.js"></script>

                </body>  
                </html>
