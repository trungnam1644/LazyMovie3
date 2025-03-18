<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gói người dùng</title>
        <link rel="stylesheet" href="css/packagesUser.css">
    </head>
    <body>
        <div class="admin-container">          
            <main class="content">
                <header>
                    <h2 id="page-title">GÓI XEM PHIM</h2>
                    <a href="home.jsp" class="logout-btn">Trở lại</a>
                </header>
                <!-- Giao diện chọn gói xem phim -->
                    <div class="package-container">
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
            </main>
        </div>
    </body>
</html>
