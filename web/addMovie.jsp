<%-- 
    Document   : addMovie
    Created on : Mar 15, 2025, 6:26:00 PM
    Author     : trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm phim mới</title>
    <link rel="stylesheet" href="css/admin.css">
</head>
<body>
    <div class="admin-container">
        <nav class="sidebar">
            <h2>ADMIN</h2>
            <ul>
                <li><a href="admin1.jsp">Quản lý phim</a></li>
                <li><a href="admin2.jsp">Quản lý người dùng</a></li>
                <li><a href="home.jsp" class="logout-btn">Trở lại</a></li>
            </ul>
        </nav>

        <main class="content">
            <header>
                <h2>Thêm phim mới</h2>
            </header>

            <section class="add-movie-form">
                <form action="MovieController" method="post">
                    <input type="hidden" name="action" value="addMovie">

                    <label for="title">Tên phim:</label>
                    <input type="text" id="title" name="title" required>

                    <label for="description">Mô tả:</label>
                    <textarea id="description" name="description" required></textarea>

                    <label for="releaseYear">Năm phát hành:</label>
                    <input type="number" id="releaseYear" name="releaseYear" required>

                    <label for="country">Quốc gia:</label>
                    <input type="text" id="country" name="country" required>

                    <label for="rating">Đánh giá:</label>
                    <input type="number" step="0.1" id="rating" name="rating" required>

                    <label for="videoUrl">URL Phim:</label>
                    <input type="url" id="videoUrl" name="videoUrl" required>

                    <label for="trailerUrl">URL Trailer:</label>
                    <input type="url" id="trailerUrl" name="trailerUrl" required>

                    <label for="thumbnailUrl">URL Hình ảnh:</label>
                    <input type="url" id="thumbnailUrl" name="thumbnailUrl" required>

                    <label for="username">Người thêm:</label>
                    <input type="text" id="username" name="username" value="${User.username}" readonly>

                    <button type="submit">Thêm phim</button>
                </form>
            </section>
        </main>
    </div>
</body>
</html>

